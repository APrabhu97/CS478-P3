package com.anish.a2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttractionActivity extends FragmentActivity{

    public static List<Attraction> attractionList;
    private FragmentContainerView nameFragmentContainerView, websiteFragmentContainerView;
    private FragmentManager mFragmentManager;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private final WebViewFragment mWebViewFragment = new WebViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        attractionList = setRestaurantList();
        Log.i("AttractionActivity", "created");
        nameFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionNames);
        websiteFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionWebsite);
        mFragmentManager = getSupportFragmentManager();
        ListViewModel model = new ViewModelProvider(this).get(ListViewModel.class);
        model.getSelectedItem().observe(this, item -> {
            Log.i("AttractionActivity", item.toString());
            onItemClicked(item);
        });
        mFragmentManager.addOnBackStackChangedListener(() -> setLayout());
    }

    private void setLayout(){
        if (!mWebViewFragment.isAdded()) {
            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 0));
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT, 1));
        }
    }

    private void onItemClicked(int index){
        if (!mWebViewFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.attractionWebsite,
                    mWebViewFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
    }

    private List<Attraction> setRestaurantList(){
        List<Attraction> list = new ArrayList<Attraction>();
        list.add(new Attraction("Alinea", "https://www.alinearestaurant.com/"));
        list.add(new Attraction("Smyth + The Loyalist", "https://www.smythandtheloyalist.com/"));
        list.add(new Attraction("Ever Restaurant", "https://www.ever-restaurant.com/"));
        list.add(new Attraction("Oriole", "https://www.oriolechicago.com/"));
        list.add(new Attraction("River Roast", "https://www.riverroastchicago.com/"));
        list.add(new Attraction("Paddlefish", "https://www.paddlefishrestaurant.com/"));
        return list;
    }
}

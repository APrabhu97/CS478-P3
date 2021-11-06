package com.anish.a2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttractionActivity extends FragmentActivity{

    public static List<Attraction> attractionList = new ArrayList<Attraction>();
    private FragmentContainerView nameFragmentContainerView, websiteFragmentContainerView;
    private FragmentManager mFragmentManager;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private final WebViewFragment mWebViewFragment = new WebViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setRestaurantList(attractionList);

        nameFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionNames);
        websiteFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionWebsite);
        mFragmentManager = getSupportFragmentManager();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
/*        mFragmentManager.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });*/
    }

//    private void setLayout() {
//        if(mWebViewFragment.model == null) return;
//        if (mWebViewFragment.model.getSelectedItem().getValue() < 0 || mWebViewFragment.model.getSelectedItem().getValue() >= attractionList.size()) {
//            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    LinearLayout.LayoutParams.MATCH_PARENT, 0));
//            return;
//        }
//        websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
//                LinearLayout.LayoutParams.MATCH_PARENT, 2));
        // Determine whether the QuoteFragment has been added
//        if (!mWebViewFragment.isAdded()) {
//
//            // Make the TitleFragment occupy the entire layout
//            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(
//                    MATCH_PARENT, MATCH_PARENT));
//            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    MATCH_PARENT));
//        } else {
//
//            // Make the TitleLayout take 1/3 of the layout's width
//            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    MATCH_PARENT, 1f));
//
//            // Make the QuoteLayout take 2/3's of the layout's width
//            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    MATCH_PARENT, 2f));
//        }
//    }

    private void setRestaurantList(List<Attraction> list){
        list.add(new Attraction("Alinea", "https://www.alinearestaurant.com/"));
        list.add(new Attraction("Smyth + The Loyalist", "https://www.smythandtheloyalist.com/"));
        list.add(new Attraction("Ever Restaurant", "https://www.ever-restaurant.com/"));
        list.add(new Attraction("Oriole", "https://www.oriolechicago.com/"));
        list.add(new Attraction("River Roast", "https://www.riverroastchicago.com/"));
        list.add(new Attraction("Paddlefish", "https://www.paddlefishrestaurant.com/"));


    }
}

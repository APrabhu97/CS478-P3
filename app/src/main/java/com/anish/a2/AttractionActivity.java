package com.anish.a2;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttractionActivity extends FragmentActivity{

    public static List<Attraction> attractionList;
    private FragmentContainerView nameFragmentContainerView, websiteFragmentContainerView;
    private FragmentManager mFragmentManager;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    ListViewModel model;
    private WebViewFragment mWebViewFragment = new WebViewFragment();
    private AttractionNameFragment mAttractionNameFragment = new AttractionNameFragment();
    //TODO: show action bar and handle that
    //TODO: check permissions according to TA and show A2 as per A1 input
    //TODO: handle horizontal weight 1/3 and 2/3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        attractionList = setRestaurantList();
        Log.i("AttractionActivity", "created");
        nameFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionNames);
        websiteFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionWebsite);
        mFragmentManager = getSupportFragmentManager();
        model = new ViewModelProvider(this).get(ListViewModel.class);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction =mFragmentManager.beginTransaction();

        fragmentTransaction.replace(
                R.id.attractionNames,
                mAttractionNameFragment);
        fragmentTransaction.commit();

        model.getSelectedItem().observe(this, item -> {
            Log.i("AttractionActivity", item.toString());
            if(item >= 0 && item < attractionList.size()) {
                mAttractionNameFragment.getListView().setItemChecked(item, true);
                if(!model.isItemClicked)return;
                onItemClicked(item);
            }
        });
        mFragmentManager.addOnBackStackChangedListener(() -> setLayout());
    }

    private void setLayout(){
        if (!mWebViewFragment.isAdded()) {
            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT, 1));
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 0));
        } else {
            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 0));
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT, 1));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.om2:

                return true;
            case R.id.om1:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT, 1));
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT, 2));
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT, 1));
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT, 0));
        }
    }

    @Override
    public void onBackPressed() {
        model.isItemClicked = false;
        super.onBackPressed();
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

    private List<Attraction> setTouristList(){
        List<Attraction> list = new ArrayList<Attraction>();
        list.add(new Attraction("Art Institute of Chicago", "https://www.artic.edu/"));
        list.add(new Attraction("Millennium Park", "https://www.chicago.gov/city/en/depts/dca/supp_info/millennium_park.html"));
        list.add(new Attraction("Navy Pier", "https://navypier.org/"));
        list.add(new Attraction("Museum of Science and Industry", "https://www.msichicago.org/"));
        list.add(new Attraction("Willis Tower SkyDeck", "https://theskydeck.com/"));
        list.add(new Attraction("Field Museum of Natural History", "http://fieldmuseum.org/"));
        list.add(new Attraction("Magnificent Mile", "https://www.themagnificentmile.com/"));
        list.add(new Attraction("Chicago Riverwalk", "http://www.chicagoriverwalk.us/"));
        list.add(new Attraction("Oriental Institute Museum", "https://oi.uchicago.edu//museum-exhibits"));
        return list;
    }
}

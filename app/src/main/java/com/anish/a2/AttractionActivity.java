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

public class AttractionActivity extends AppCompatActivity{

    public static List<Attraction> attractionList;
    private FragmentContainerView nameFragmentContainerView, websiteFragmentContainerView;
    private FragmentManager mFragmentManager;
    private String attractionType;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;
    ListViewModel model;
    private WebViewFragment mWebViewFragment;
    private AttractionNameFragment mAttractionNameFragment;
    //TODO: check permissions according to TA and show A2 as per A1 input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        mFragmentManager = getSupportFragmentManager();
        nameFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionNames);
        websiteFragmentContainerView = (FragmentContainerView) findViewById(R.id.attractionWebsite);
        model = new ViewModelProvider(this).get(ListViewModel.class);
        if(savedInstanceState != null){
            attractionType = savedInstanceState.getString("attractionType");
        }
        else{
            Intent intent = getIntent();
            attractionType = intent.getStringExtra("activityType");
        }
        //showing the apt data
        updateView(attractionType);

        //ensuring web fragment is shown whenever item is selected
        model.getSelectedItem().observe(this, item -> {
            if(item >= 0 && item < attractionList.size()) {
                mAttractionNameFragment.getListView().setItemChecked(item, true);
                if(!model.isItemClicked)return;
                onItemClicked(item);
            }
        });
        //updating layout whenever back stack changes
        mFragmentManager.addOnBackStackChangedListener(() -> setLayout());
    }

    private void updateView(String attractionType){
        //setting the correct data
        switch (attractionType){
            case "restaurants":
                attractionList = setRestaurantList();
                break;
            case "tourists":
                attractionList = setTouristList();
                break;
        }
        mFragmentManager.popBackStack();
        //removing previous fragments if they exist
        if(mWebViewFragment != null)mFragmentManager.beginTransaction().remove(mWebViewFragment).commit();
        if(mAttractionNameFragment != null)mFragmentManager.beginTransaction().remove(mAttractionNameFragment).commit();
        mFragmentManager.executePendingTransactions();
        mWebViewFragment = new WebViewFragment();
        mAttractionNameFragment = new AttractionNameFragment();
        FragmentTransaction fragmentTransaction =mFragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.attractionNames,
                mAttractionNameFragment);
        fragmentTransaction.commit();
        setLayout();
    }

    private void setLayout(){
        //setting the correct layout as per orientation and user selection
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (!mWebViewFragment.isAdded()) {
                nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT, 1));
                websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                        WRAP_CONTENT, 0));
            } else {
                nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0));
                websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT, 1));
            }
        }
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (!mWebViewFragment.isAdded()) {
                nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT, 1));
                websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                        WRAP_CONTENT, 0));
            } else {
                nameFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT, 2));
                websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT, 1));
            }
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
        //saving the type of activity shown
        outState.putString("attractionType", attractionType);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.om2:
                if(attractionType == "restaurants") return true;
                attractionType = "restaurants";
                updateView(attractionType);
                model.selectItem(-1);
                return true;
            case R.id.om1:
                if(attractionType == "tourists") return true;
                attractionType = "tourists";
                updateView(attractionType);
                model.selectItem(-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayout();
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
        list.add(new Attraction("Avli Taverna", "https://avli.us/"));
        return list;
    }

    private List<Attraction> setTouristList(){
        List<Attraction> list = new ArrayList<Attraction>();
        list.add(new Attraction("Art Institute of Chicago", "https://www.artic.edu/"));
        list.add(new Attraction("Millennium Park", "https://www.chicago.gov/city/en/depts/dca/supp_info/millennium_park.html"));
        list.add(new Attraction("Navy Pier", "https://navypier.org/"));
        list.add(new Attraction("Museum of Science and Industry", "https://www.msichicago.org/"));
        list.add(new Attraction("Willis Tower SkyDeck", "https://theskydeck.com/"));
        list.add(new Attraction("The Rookery Building", "https://www.therookerybuilding.com/"));
        list.add(new Attraction("Music Box Theatre", "https://musicboxtheatre.com/"));
        list.add(new Attraction("Chicago History Museum", "https://www.chicagohistory.org/"));
        list.add(new Attraction("Oriental Institute Museum", "https://oi.uchicago.edu//museum-exhibits"));
        return list;
    }
}

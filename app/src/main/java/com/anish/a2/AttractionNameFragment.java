package com.anish.a2;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.stream.Collectors;

public class AttractionNameFragment extends ListFragment {

    private static final String TAG = "AttractionNameFragment";
    private ListViewModel model;


    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        model.selectItem(pos);
    }



    //replaced anActivityCreated
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.attrraction_name,
                AttractionActivity.attractionList.stream()
                        .map(attraction -> attraction.getName()).collect(Collectors.toList())));
    }
}
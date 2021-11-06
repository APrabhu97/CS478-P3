package com.anish.a2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

public class WebViewFragment extends Fragment {
    private WebView mWebView = null;
    private int mCurrIdx = -1;
    private int mAttractionArrayLen;
    public ListViewModel model;
    private static final String TAG = "WebViewFragment";


    @Override
    public void onAttach(Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.web_fragment, container, false);
    }

    /* this callback is deprecated. Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mQuoteView = (TextView) getActivity().findViewById(R.id.quoteView);
        mQuoteArrayLen = QuoteViewerActivity.mQuoteArray.length;
    }
*/
    //replaces onActivityCreated
    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){

        Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
        super.onViewCreated(view,savedInstanceState);
        FragmentContainerView nameFragmentContainerView;
        final FragmentContainerView  websiteFragmentContainerView = (FragmentContainerView)getActivity().findViewById(R.id.attractionWebsite);;
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
/*        nameFragmentContainerView =
        websiteFragmentContainerView = (FragmentContainerView)view.findViewById(R.id.attractionWebsite);*/
        // retains last quote shown on config change
  //      final FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
/*        mFragmentManager.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        if (model.getSelectedItem().getValue() < 0 || model.getSelectedItem().getValue() >= mAttractionArrayLen) {
                            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                                    LinearLayout.LayoutParams.MATCH_PARENT, 0));
                            return;
                        }
                        websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                                LinearLayout.LayoutParams.MATCH_PARENT, 0));
                    }
                });*/
        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            // Update the UI.
            if (item < 0 || item >= mAttractionArrayLen) {
                websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.MATCH_PARENT, 0));
                //mFragmentManager.beginTransaction().addToBackStack(null).commit();
                return;
            }
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 2));
           // mFragmentManager.beginTransaction().addToBackStack(null).commit();
            mCurrIdx = item;
            mWebView.loadUrl(AttractionActivity.attractionList.get(mCurrIdx).getUrl());

            //TODO: check if required
/*            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new WebViewClient());*/
        });

        mWebView = (WebView) getActivity().findViewById(R.id.webView);
        mAttractionArrayLen = AttractionActivity.attractionList.size();
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }
}

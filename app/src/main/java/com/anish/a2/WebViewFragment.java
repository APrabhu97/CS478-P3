package com.anish.a2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.web_fragment, container, false);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){
        Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
        super.onViewCreated(view,savedInstanceState);
        final FragmentContainerView  websiteFragmentContainerView = (FragmentContainerView)getActivity().findViewById(R.id.attractionWebsite);;
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            if (item < 0 || item >= mAttractionArrayLen) {
                websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.MATCH_PARENT, 0));
                return;
            }
            websiteFragmentContainerView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 2));
            mCurrIdx = item;
            mWebView.loadUrl(AttractionActivity.attractionList.get(mCurrIdx).getUrl());
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new WebViewClient());
        });

        mWebView = (WebView) getActivity().findViewById(R.id.webView);
        mAttractionArrayLen = AttractionActivity.attractionList.size();
    }
}

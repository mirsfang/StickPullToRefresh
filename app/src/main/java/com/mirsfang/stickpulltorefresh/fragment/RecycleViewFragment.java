package com.mirsfang.stickpulltorefresh.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import com.mirsfang.stickpulltorefresh.R;
import com.mirsfang.stickpulltorefresh.adapter.RecycleViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecycleViewFragment extends BaseFragment {


    @Bind(R.id.id_stickynavlayout_innerscrollview)
    ExpandableListView mRecycleView;

    public static RecycleViewFragment newInstance() {
        RecycleViewFragment fragment = new RecycleViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecycleViewAdapter adapter = new RecycleViewAdapter(getActivity());
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public String getTitle() {
        return "RecycleView";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

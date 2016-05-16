package com.mirsfang.stickpulltorefresh;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.mirsfang.stickpulltorefresh.adapter.FragmentsViewPagerAdapter;
import com.mirsfang.stickpulltorefresh.fragment.BaseFragment;
import com.mirsfang.stickpulltorefresh.fragment.ListViewFragment;
import com.mirsfang.stickpulltorefresh.fragment.RecycleViewFragment;
import com.mirsfang.stickpulltorefresh.layout.StickyNavLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.id_stickynavlayout_indicator)
    LinearLayout pagerSlidingTabStrip;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.id_stick)
    StickyNavLayout stickyNavLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(ListViewFragment.newInstance());
        //这个RecycleViewFragment被我修改成expandablelistview了，具体可以看作业本来的项目
        fragments.add(RecycleViewFragment.newInstance());

        FragmentsViewPagerAdapter adapter = new FragmentsViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Work().execute();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //设置下拉刷新的条件
                return stickyNavLayout.getScrollY() == 0;
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 150);



        /**
         * 点击悬浮到头部
         * */
        pagerSlidingTabStrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stickyNavLayout.smothScrollTo();
            }
        });
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    private class Work extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mPtrFrame.refreshComplete();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

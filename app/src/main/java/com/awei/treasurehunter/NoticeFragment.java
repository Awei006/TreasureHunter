package com.awei.treasurehunter;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoticeFragment extends Fragment {


    public NoticeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setAdapter(new NoticeAdapter(getChildFragmentManager(), getContext()));

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        Log.d("TEST", "PAGER");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }
        });


        return rootView;
    }

    ViewPager pager;
    TabLayout tabLayout;

    private class NoticeAdapter extends FragmentPagerAdapter {
        public NoticeAdapter(android.support.v4.app.FragmentManager fm, Context context) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NoticeImportantFragment();
                case 1:
                    return new NoticeGeneralFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return Resources.FRAG.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Resources.FRAG[position];
        }
    }

}

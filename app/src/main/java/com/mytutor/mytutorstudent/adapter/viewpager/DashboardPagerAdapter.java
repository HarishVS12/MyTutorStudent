package com.mytutor.mytutorstudent.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mytutor.mytutorstudent.ui.dashboard.appointments.AppointmentFragment;

import com.mytutor.mytutorstudent.ui.dashboard.home.HomeFragment;
import com.mytutor.mytutorstudent.ui.dashboard.ongoing.OnGoingFragment;

/*
@Author cr7
@CreatedOn 3/28/2020
*/
public class DashboardPagerAdapter extends FragmentStatePagerAdapter {
    private static final String[] FRAGMENT_TYPES = {"HOME", "ON GOING", "APPOINTMENTS"};

    public DashboardPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        String type=FRAGMENT_TYPES[position];
        switch (position)
        {
            case 0:
                fragment= HomeFragment.newInstance(type);
                break;

            case 1:
                fragment= OnGoingFragment.newInstance(type);
                break;

            case 2:
                fragment= AppointmentFragment.newInstance(type);
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FRAGMENT_TYPES[position];
    }

    @Override
    public int getCount() {
        return FRAGMENT_TYPES.length;
    }
}

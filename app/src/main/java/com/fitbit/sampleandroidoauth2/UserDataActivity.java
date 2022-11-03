package com.fitbit.sampleandroidoauth2;

import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.sampleandroidoauth2.databinding.ActivityUserDataBinding;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

public class UserDataActivity extends AppCompatActivity {

    private ActivityUserDataBinding binding;
    private UserDataPagerAdapter userDataPagerAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, UserDataActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_data);
        binding.setLoading(false);

        userDataPagerAdapter = new UserDataPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(userDataPagerAdapter);

        binding.viewPager.addOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });

        addTabs();
    }

    private void addTabs() {
        final ActionBar actionBar = getActionBar();
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        int numberOfTabs = userDataPagerAdapter.getCount();
        for (int i = 0; i < numberOfTabs; i++) {
            final int index = i;
            String name = "";
            switch (i) {
                case 0: name = getString(R.string.user_info); break;
                case 1: name = getString(R.string.devices); break;
                case 2: name = getString(R.string.activity_info); break;
                case 3: name = getString(R.string.weight); break;
            }
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(name)
                            .setTabListener(new ActionBar.TabListener() {
                                @Override
                                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                                    binding.viewPager.setCurrentItem(index);
                                }

                                @Override
                                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

                                }

                                @Override
                                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

                                }
                            }));
        }
    }


    public void onLogoutClick(View view) {
        binding.setLoading(true);
        AuthenticationManager.logout(this);
    }
}

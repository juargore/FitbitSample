package com.fitbit.sampleandroidoauth2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;
import com.fitbit.sampleandroidoauth2.fragments.ActivitiesFragment;
import com.fitbit.sampleandroidoauth2.fragments.DeviceFragment;
import com.fitbit.sampleandroidoauth2.fragments.ProfileFragment;
import com.fitbit.sampleandroidoauth2.fragments.WeightLogFragment;
import java.util.ArrayList;
import java.util.List;


public class UserDataPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    public UserDataPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments.clear();
        if (containsScope(Scope.profile)) {
            fragments.add(new ProfileFragment());
        }
        if (containsScope(Scope.settings)) {
            fragments.add(new DeviceFragment());
        }
        if (containsScope(Scope.activity)) {
            fragments.add(new ActivitiesFragment());
        }
        if (containsScope(Scope.weight)) {
            fragments.add(new WeightLogFragment());
        }
    }

    private boolean containsScope(Scope scope) {
        return AuthenticationManager.getCurrentAccessToken().getScopes().contains(scope);
    }

    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        if (position >= fragments.size()) {
            return null;
        }
        return (androidx.fragment.app.Fragment) fragments.get(position) ;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getTitleResourceId(int index) {
        //Fragment fr = fragments.get(index);
        return 0;
    }
}

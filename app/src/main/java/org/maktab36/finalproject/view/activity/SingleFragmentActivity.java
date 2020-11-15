package org.maktab36.finalproject.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.ActivitySingleFragmentBinding;
import org.maktab36.finalproject.databinding.DrawerLayoutBinding;

public abstract class SingleFragmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public abstract Fragment createFragment();

    private DrawerLayoutBinding mBinding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.drawer_layout);
        toggle=new ActionBarDrawerToggle(
                this,mBinding.drawerLayout,R.string.drawer_open,R.string.drawer_close);

        mBinding.drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, createFragment())
                    .addToBackStack("MainPageFragment")
                    .commit();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if(mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        }else if(getSupportFragmentManager().getBackStackEntryCount()>0){
//            getSupportFragmentManager().popBackStackImmediate("MainPageFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            getSupportFragmentManager().popBackStackImmediate();
        }else{
            super.onBackPressed();
        }
    }
}
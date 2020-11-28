package org.maktab36.finalproject.view.activity;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.ActivitySingleFragmentBinding;
import org.maktab36.finalproject.databinding.DrawerLayoutBinding;
import org.maktab36.finalproject.view.fragment.CartFragment;

public abstract class SingleFragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public abstract Fragment createFragment();

    private DrawerLayoutBinding mBinding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, R.string.drawer_open, R.string.drawer_close);

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

        createNavigationMenu();
    }

    private void createNavigationMenu() {
        Menu menu = mBinding.navView.getMenu();
        MenuItem item = menu.add("1");
//        item.setIcon(R.drawable.ic_image);

        Picasso.get()
                .load("https://woocommerce.maktabsharif.ir/wp-content/uploads/2019/12/suit.png")
                .placeholder(R.drawable.ic_image)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Log.d("reza", "onBitmapLoaded: ");
                        item.setIcon(new BitmapDrawable(getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.d("reza", e.toString(), e);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Log.d("reza", "onPrepareLoad: ");
                        item.setIcon(placeHolderDrawable);
                    }
                });


        /*Glide.with(this)
                .asBitmap()
                .load("https://woocommerce.maktabsharif.ir/wp-content/uploads/2019/12/suit.png")
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Log.d("reza", "onResourceReady: ");
                        menu.getItem(0).setIcon(new BitmapDrawable(getResources(),resource));
                    }
                });*/
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_item_account:
                return true;
            case R.id.menu_item_cart:
                Log.d("reza", "onOptionsItemSelected: ");
                Fragment fragment= CartFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,fragment)
                        .commit();
                return true;
            case R.id.menu_item_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            getSupportFragmentManager().popBackStackImmediate("MainPageFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}
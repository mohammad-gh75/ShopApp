package org.maktab36.finalproject.view.activity;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

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
import org.maktab36.finalproject.data.model.Categories;
import org.maktab36.finalproject.databinding.ActivitySingleFragmentBinding;
import org.maktab36.finalproject.databinding.DrawerLayoutBinding;
import org.maktab36.finalproject.view.fragment.CartFragment;
import org.maktab36.finalproject.view.fragment.CategoryProductsFragment;
import org.maktab36.finalproject.viewmodel.NavMenuViewModel;

public abstract class SingleFragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public abstract Fragment createFragment();

    private DrawerLayoutBinding mBinding;
    private ActionBarDrawerToggle toggle;
    private NavMenuViewModel mMenuViewModel;

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
        mMenuViewModel=new ViewModelProvider(this).get(NavMenuViewModel.class);
        mMenuViewModel.getProductCategoriesLiveData().observe(this,categories -> {
            createNavigationMenu();
        });
        mMenuViewModel.fetchProductCategoriesLiveData();
    }

    private void createNavigationMenu() {
        Menu menu = mBinding.navView.getMenu();
        for (Categories category:mMenuViewModel.getHeadingCategories()) {
            SubMenu subMenu =menu.addSubMenu(category.getName());
            MenuItem item = subMenu.add(getString(R.string.all_category_product));
            setMenuItemIcon(item,category.getImageUrl());
            setMenuItemListener(item,category);

            for (Categories subCategory:mMenuViewModel.getSubCategories(category.getId())) {
                MenuItem subItem = subMenu.add(subCategory.getName());
                setMenuItemIcon(subItem,subCategory.getImageUrl());
                setMenuItemListener(subItem,subCategory);
            }
        }
    }
    private void setMenuItemListener(MenuItem item,Categories category){
        item.setOnMenuItemClickListener(item1 -> {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            CategoryProductsFragment fragment=
                    CategoryProductsFragment.newInstance(category);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .addToBackStack("CategoryProductFragment")
                    .commit();
            return true;
        });
    }
    private void setMenuItemIcon(MenuItem item,String url){
        Picasso.get()
                .load(url)
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
                        .addToBackStack("CartFragment")
                        .commit();
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
        FragmentManager fragmentManager=getSupportFragmentManager();
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() > 0) {

//            fragmentManager.popBackStackImmediate("MainPageFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}
package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListLastProductAdapter;
import org.maktab36.finalproject.adapters.ListMostPointsProductAdapter;
import org.maktab36.finalproject.adapters.ListMostViewProductAdapter;
import org.maktab36.finalproject.adapters.ListSpecialProductAdapter;
import org.maktab36.finalproject.databinding.FragmentMainPageBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;

public class MainPageFragment extends Fragment {

    private FragmentMainPageBinding mFragmentMainPageBinding;
    private MainViewModel mMainViewModel;
    private ListLastProductAdapter mLastProductAdapter;
    private ListMostViewProductAdapter mMostViewProductAdapter;
    private ListMostPointsProductAdapter mMostPointsProductAdapter;
    private ListSpecialProductAdapter mSpecialProductAdapter;

    public MainPageFragment() {
        // Required empty public constructor
    }

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        registerObservers();

        mMainViewModel.fetchProductsLiveData();
    }

    private void registerObservers() {
        mMainViewModel.getLastProductsLiveData().observe(this, products ->
                setLastProductsAdapter());

        mMainViewModel.getMostViewProductsLiveData().observe(this, products ->
                setMostViewProductsAdapter());

        mMainViewModel.getMostPointsProductsLiveData().observe(this, products ->
                setMostPointsProductsAdapter());

        mMainViewModel.getSelectedProductLiveData().observe(this, product -> {
            ProductFragment fragment = ProductFragment.newInstance(product.getId());
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        });

        mMainViewModel.getSpecialProductsLiveData().observe(this, products ->
                setSpecialProductAdapter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentMainPageBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_main_page, container, false);

        initProductSlider();

        setRecyclerViewLayoutManager();

        return mFragmentMainPageBinding.getRoot();
    }

    private void initProductSlider() {
        mFragmentMainPageBinding.specialProductSlider
                .setIndicatorAnimation(IndicatorAnimationType.WORM);
        mFragmentMainPageBinding.specialProductSlider
                .setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mFragmentMainPageBinding.specialProductSlider.startAutoCycle();
    }

    private void setRecyclerViewLayoutManager() {
        mFragmentMainPageBinding.listLastProduct.setLayoutManager(
                new GridLayoutManager(
                        requireContext(),
                        3,
                        RecyclerView.HORIZONTAL,
                        true));
        mFragmentMainPageBinding.listMostViewProduct.setLayoutManager(
                new GridLayoutManager(
                        requireContext(),
                        3,
                        RecyclerView.HORIZONTAL,
                        true));
        mFragmentMainPageBinding.listMostPointsProduct.setLayoutManager(
                new LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        true));
    }

    private void setLastProductsAdapter() {
        if (mLastProductAdapter == null) {
            mLastProductAdapter = new ListLastProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.listLastProduct.setAdapter(mLastProductAdapter);
        } else {
            mLastProductAdapter.notifyDataSetChanged();
        }
    }

    private void setMostPointsProductsAdapter() {
        if (mMostPointsProductAdapter == null) {
            mMostPointsProductAdapter = new ListMostPointsProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.listMostPointsProduct.setAdapter(mMostPointsProductAdapter);
        } else {
            mMostPointsProductAdapter.notifyDataSetChanged();
        }
    }

    private void setMostViewProductsAdapter() {
        if (mMostViewProductAdapter == null) {
            mMostViewProductAdapter = new ListMostViewProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.listMostViewProduct.setAdapter(mMostViewProductAdapter);
        } else {
            mMostViewProductAdapter.notifyDataSetChanged();
        }
    }

    private void setSpecialProductAdapter() {
        if (mSpecialProductAdapter == null) {
            mSpecialProductAdapter = new ListSpecialProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.specialProductSlider
                    .setSliderAdapter(mSpecialProductAdapter);
        } else {
            mSpecialProductAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        SearchFragment fragment=SearchFragment.newInstance(query);
                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container,fragment)
                                .commit();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
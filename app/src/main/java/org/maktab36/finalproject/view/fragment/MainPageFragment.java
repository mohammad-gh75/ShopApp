package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListLastProductAdapter;
import org.maktab36.finalproject.adapters.ListMostPointsProductAdapter;
import org.maktab36.finalproject.adapters.ListMostViewProductAdapter;
import org.maktab36.finalproject.databinding.FragmentMainPageBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;

public class MainPageFragment extends Fragment {

    private FragmentMainPageBinding mFragmentMainPageBinding;
    private MainViewModel mMainViewModel;
    private ListLastProductAdapter mLastProductAdapter;
    private ListMostViewProductAdapter mMostViewProductAdapter;
    private ListMostPointsProductAdapter mMostPointsProductAdapter;

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

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        registerObservers();

        mMainViewModel.fetchProductsLiveData();
    }

    private void registerObservers() {
        mMainViewModel.getLastProductsLiveData().observe(this, products -> {
            setLastProductsAdapter();
        });

        mMainViewModel.getMostViewProductsLiveData().observe(this, products -> {
            setMostViewProductsAdapter();
        });

        mMainViewModel.getMostPointsProductsLiveData().observe(this, products -> {
            setMostPointsProductsAdapter();
        });

        mMainViewModel.getSelectedProductLiveData().observe(this, product -> {
            ProductFragment fragment=ProductFragment.newInstance(product.getId());
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentMainPageBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_main_page, container, false);

        setRecyclerViewLayoutManager();

        return mFragmentMainPageBinding.getRoot();
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
        /*mFragmentMainPageBinding.listMostPointsProduct.setLayoutManager(
                new GridLayoutManager(
                        requireContext(),
                        3,
                        RecyclerView.HORIZONTAL,
                        true));*/
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
}
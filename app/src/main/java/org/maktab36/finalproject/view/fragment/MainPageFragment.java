package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentMainPageBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_main_page, container, false);

        setRecyclerViewLayoutManager();
        setAdapters();

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
                new GridLayoutManager(
                        requireContext(),
                        3,
                        RecyclerView.HORIZONTAL,
                        true));
    }

    private void setAdapters() {
        if (mLastProductAdapter == null) {
            mLastProductAdapter = new ListLastProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.listLastProduct.setAdapter(mLastProductAdapter);
        } else {
            mLastProductAdapter.notifyDataSetChanged();
        }

        if (mMostViewProductAdapter == null) {
            mMostViewProductAdapter = new ListMostViewProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.listMostViewProduct.setAdapter(mLastProductAdapter);
        } else {
            mMostViewProductAdapter.notifyDataSetChanged();
        }

        if (mMostPointsProductAdapter == null) {
            mMostPointsProductAdapter = new ListMostPointsProductAdapter(mMainViewModel);
            mFragmentMainPageBinding.listMostPointsProduct.setAdapter(mLastProductAdapter);
        } else {
            mMostPointsProductAdapter.notifyDataSetChanged();
        }
    }
}
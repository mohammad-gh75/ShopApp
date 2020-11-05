package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.FragmentMainPageBinding;

public class MainPageFragment extends Fragment {

    private FragmentMainPageBinding mFragmentMainPageBinding;

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
        mFragmentMainPageBinding.listLastProduct
                .setLayoutManager(new GridLayoutManager(requireContext(), 3));
        mFragmentMainPageBinding.listMostViewProduct
                .setLayoutManager(new GridLayoutManager(requireContext(), 3));
        mFragmentMainPageBinding.listMostPointsProduct
                .setLayoutManager(new GridLayoutManager(requireContext(), 3));
    }
}
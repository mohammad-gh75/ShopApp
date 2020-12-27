package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListCategoryProductsAdapter;
import org.maktab36.finalproject.data.model.Categories;
import org.maktab36.finalproject.databinding.FragmentCategoryProductsBinding;
import org.maktab36.finalproject.viewmodel.CategoryProductViewModel;


public class CategoryProductsFragment extends Fragment {
    public static final String ARG_CATEGORY_ID = "category_id";
    public static final String ARG_CATEGORY_COUNT = "category_count";
    public static final String ARG_CATEGORY_NAME = "category_name";
    private FragmentCategoryProductsBinding mCategoryProductsBinding;
    private CategoryProductViewModel mCategoryProductViewModel;
    private int mCategoryId;
    private int mCategoryCount;
    private String mCategoryName;
    private ListCategoryProductsAdapter mAdapter;

    public CategoryProductsFragment() {
        // Required empty public constructor
    }

    public static CategoryProductsFragment newInstance(Categories category) {
        CategoryProductsFragment fragment = new CategoryProductsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, category.getId());
        args.putInt(ARG_CATEGORY_COUNT, category.getCount());
        args.putString(ARG_CATEGORY_NAME, category.getName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getInt(ARG_CATEGORY_ID);
            mCategoryCount = getArguments().getInt(ARG_CATEGORY_COUNT);
            mCategoryName = getArguments().getString(ARG_CATEGORY_NAME);
        }
        mCategoryProductViewModel = new ViewModelProvider(this)
                .get(CategoryProductViewModel.class);

        mCategoryProductViewModel.getCategoryProductsLiveData().observe(this, products -> {
            setAdapter();
            setListeners();
        });
        mCategoryProductViewModel.getSelectedProductLiveData().observe(this, product -> {
            ProductFragment fragment = ProductFragment.newInstance(product.getId());
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        });
        mCategoryProductViewModel.fetchCategoryProductsLiveData(
                mCategoryId,"popularity","desc");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCategoryProductsBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_category_products, container, false);

        mCategoryProductsBinding
                .listCategoryProduct.setLayoutManager(new LinearLayoutManager(requireContext()));

        initUI();


        return mCategoryProductsBinding.getRoot();
    }

    private void initUI() {
        mCategoryProductsBinding.categoryName.setText(mCategoryName);

        mCategoryProductsBinding.categoryProductsNumber.setText(
                getString(R.string.number_of_products, mCategoryCount));
    }

    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new ListCategoryProductsAdapter(mCategoryProductViewModel);
            mCategoryProductsBinding.listCategoryProduct.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setListeners() {
        mCategoryProductsBinding
                .productSort.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mCategoryProductViewModel.fetchCategoryProductsLiveData(
                                mCategoryId,
                                "popularity",
                                "desc");
                        break;
                    case 1:
                        mCategoryProductViewModel.fetchCategoryProductsLiveData(
                                mCategoryId,
                                "price",
                                "desc");
                        break;
                    case 2:
                        mCategoryProductViewModel.fetchCategoryProductsLiveData(
                                mCategoryId,
                                "price",
                                "asc");
                        break;
                    case 3:
                        mCategoryProductViewModel.fetchCategoryProductsLiveData(
                                mCategoryId,
                                "date",
                                "desc");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
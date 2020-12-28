package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListNewProductsAdapter;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.databinding.FragmentNewProductBinding;
import org.maktab36.finalproject.viewmodel.NewProductViewModel;

import java.util.ArrayList;
import java.util.List;


public class NewProductFragment extends Fragment {
    public static final String ARG_NEW_PRODUCTS = "argNewProducts";
    private List<Product> mProducts;
    private FragmentNewProductBinding mProductBinding;
    private NewProductViewModel mNewProductViewModel;
    private ListNewProductsAdapter mAdapter;


    public NewProductFragment() {
        // Required empty public constructor
    }

    public static NewProductFragment newInstance(ArrayList<Product> newProducts) {
        NewProductFragment fragment = new NewProductFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEW_PRODUCTS, newProducts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProducts = (List<Product>) getArguments().getSerializable(ARG_NEW_PRODUCTS);
        }
        mNewProductViewModel = new ViewModelProvider(this).get(NewProductViewModel.class);
        mNewProductViewModel.setProductList(mProducts);
        mNewProductViewModel.getSelectedProductLiveData().observe(this, product -> {
            ProductFragment fragment = ProductFragment.newInstance(product.getId());
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack("productFragment")
                    .commit();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_new_product, container, false);

        mProductBinding.newProductsList
                .setLayoutManager(new LinearLayoutManager(requireActivity()));

        setAdapter();

        return mProductBinding.getRoot();
    }

    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new ListNewProductsAdapter(mNewProductViewModel);
            mProductBinding.newProductsList.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
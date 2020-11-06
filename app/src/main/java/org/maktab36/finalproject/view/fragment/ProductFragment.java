package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.adapter.StatefulAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListProductImageAdapter;
import org.maktab36.finalproject.databinding.FragmentProductBinding;
import org.maktab36.finalproject.viewmodel.ProductViewModel;


public class ProductFragment extends Fragment {

    private FragmentProductBinding mProductBinding;
    private ListProductImageAdapter mImageAdapter;
    private ProductViewModel mProductViewModel;
    public static final String ARG_PRODUCT_ID = "productId";

    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance(int id) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id=getArguments().getInt(ARG_PRODUCT_ID);
        mProductViewModel=new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.getSelectedProductLiveData().observe(this,product -> {
            setAdapter();
        });
        mProductViewModel.fetchSelectedProductLiveData(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductBinding= DataBindingUtil
                .inflate(inflater,R.layout.fragment_product,container,false);

        initUI();

        return mProductBinding.getRoot();
    }

    private void initUI() {
        mProductBinding.textViewProductName
                .setText(mProductViewModel.getSelectedProduct().getName());
        mProductBinding.textViewProductPrice
                .setText(mProductViewModel.getSelectedProduct().getPrice());
    }

    private void setAdapter() {
        if(mImageAdapter == null){
            mImageAdapter=new ListProductImageAdapter(mProductViewModel);
            mProductBinding.productImageSlider.setSliderAdapter(mImageAdapter);
        }else{
            mImageAdapter.notifyDataSetChanged();
        }
    }

}
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

    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProductViewModel=new ViewModelProvider(this).get(ProductViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductBinding= DataBindingUtil
                .inflate(inflater,R.layout.fragment_product,container,false);



//        setAdapter();
        return mProductBinding.getRoot();
    }

    /*private void setAdapter() {
        if(mImageAdapter == null){
            mImageAdapter=new ListProductImageAdapter(mProductViewModel);
            mProductBinding.listProductImages.setAdapter(mImageAdapter);
        }else{
            mImageAdapter.notifyDataSetChanged();
        }
    }*/

}
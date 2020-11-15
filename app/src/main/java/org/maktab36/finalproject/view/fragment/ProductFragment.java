package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListProductImageAdapter;
import org.maktab36.finalproject.databinding.FragmentProductBinding;
import org.maktab36.finalproject.viewmodel.ProductViewModel;

import java.util.Locale;


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
                .setText(getString(
                        R.string.product_price,
                        getFormattedPrice()));
        mProductBinding.ratingBar.setRating(mProductViewModel.getSelectedProduct().getRate());
        mProductBinding.textViewProductDescription.setText(getProductDescription());
    }

    private String getFormattedPrice(){
        String price = mProductViewModel.getSelectedProduct().getPrice();
        return String.format(new Locale("fa"),"%,d",Long.parseLong(price));
    }

    private String getProductDescription() {
        String description=
                Html.fromHtml(mProductViewModel.getSelectedProduct().getDescription()).toString();
        return description.replace(":"," :");
    }

    private void setAdapter() {
        if(mImageAdapter == null){
            mImageAdapter=new ListProductImageAdapter(mProductViewModel);
            mProductBinding.productImageSlider.setSliderAdapter(mImageAdapter);
            mProductBinding.productImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
            mProductBinding.productImageSlider
                    .setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        }else{
            mImageAdapter.notifyDataSetChanged();
        }
    }

}
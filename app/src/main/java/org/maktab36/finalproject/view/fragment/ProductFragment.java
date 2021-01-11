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
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListProductImageAdapter;
import org.maktab36.finalproject.databinding.FragmentProductBinding;
import org.maktab36.finalproject.utils.StringUtils;
import org.maktab36.finalproject.viewmodel.ProductViewModel;

import java.util.Locale;


public class ProductFragment extends Fragment {

    private FragmentProductBinding mProductBinding;
    private ListProductImageAdapter mImageAdapter;
    private ProductViewModel mProductViewModel;
    private int mProductId;
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
        mProductId=getArguments().getInt(ARG_PRODUCT_ID);
        mProductViewModel=new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.getSelectedProductLiveData().observe(this,product -> {
            setAdapter();
        });
        mProductViewModel.fetchSelectedProductLiveData(mProductId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductBinding= DataBindingUtil
                .inflate(inflater,R.layout.fragment_product,container,false);

        initUI();
        setListener();

        return mProductBinding.getRoot();
    }

    private void initUI() {
        mProductBinding.textViewProductName
                .setText(mProductViewModel.getSelectedProduct().getName());
        String price=mProductViewModel.getSelectedProduct().getPrice();
        if(!price.isEmpty()) {
            mProductBinding.textViewProductPrice
                    .setText(getString(
                            R.string.product_price,
                            StringUtils.getFormattedPrice(
                                    mProductViewModel.getSelectedProduct().getPrice())));
        }
        mProductBinding.ratingBar.setRating(mProductViewModel.getSelectedProduct().getRate());
        mProductBinding.textViewProductDescription.setText(
                StringUtils.getProductDescription(
                        mProductViewModel.getSelectedProduct().getDescription()));
    }

    private void setListener(){
        mProductBinding.buttonAddToCart.setOnClickListener(view -> {
            mProductViewModel.addToCart();
            Toast.makeText(
                    requireActivity(),
                    getString(R.string.toast_add_to_cart),
                    Toast.LENGTH_SHORT).show();
        });
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
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
import org.maktab36.finalproject.adapters.ListCartProductAdapter;
import org.maktab36.finalproject.databinding.FragmentCartBinding;
import org.maktab36.finalproject.utils.StringUtils;
import org.maktab36.finalproject.viewmodel.CartViewModel;

public class CartFragment extends Fragment {

    private FragmentCartBinding mCartBinding;
    private CartViewModel mCartViewModel;
    private ListCartProductAdapter mCartProductAdapter;


    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCartViewModel=new ViewModelProvider(this).get(CartViewModel.class);
        mCartViewModel.getCartProductsLiveData().observe(this, products -> {
            setAdapter();
            updateUI();
        });
        mCartViewModel.fetchCartProductsLiveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCartBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_cart, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mCartBinding.recyclerViewCartProducts
                .setLayoutManager(layoutManager);

        return mCartBinding.getRoot();
    }

    private void updateUI() {
        mCartBinding.cartProductsPrice.setText(getString(
                R.string.product_price,
                StringUtils.getFormattedPrice(mCartViewModel.getProductsSumPrice())
        ));
        mCartBinding.cartSumReduction.setText(getString(
                R.string.product_price,
                StringUtils.getFormattedPrice(mCartViewModel.getProductsSumReduction())
        ));
        mCartBinding.cartSumPrice.setText(getString(
                R.string.product_price,
                StringUtils.getFormattedPrice(mCartViewModel.getCartSumPrice())
        ));
        mCartBinding.textViewSumPrice.setText(getString(
                R.string.product_price,
                StringUtils.getFormattedPrice(mCartViewModel.getCartSumPrice())
        ));
        mCartBinding.textViewCartProductNumber.setText(
                getString(R.string.number_of_cart_product,
                        mCartViewModel.getCartProducts().size()));
    }

    private void setAdapter() {
        if(mCartProductAdapter==null){
            mCartProductAdapter=new ListCartProductAdapter(mCartViewModel);
            mCartBinding.recyclerViewCartProducts.setAdapter(mCartProductAdapter);
        }else{
            mCartProductAdapter.notifyDataSetChanged();
        }
    }
}
package org.maktab36.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.databinding.ListRowCartProductsBinding;
import org.maktab36.finalproject.utils.StringUtils;
import org.maktab36.finalproject.viewmodel.CartViewModel;

public class ListCartProductAdapter
        extends RecyclerView.Adapter<ListCartProductAdapter.CartViewHolder> {

    private CartViewModel mViewModel;

    public ListCartProductAdapter(CartViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ListRowCartProductsBinding rowBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_cart_products,
                        parent,
                        false);
        return new CartViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getCartProducts().size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ListRowCartProductsBinding mRowBinding;

        public CartViewHolder(ListRowCartProductsBinding rowBinding) {
            super(rowBinding.getRoot());
            mRowBinding = rowBinding;
            mRowBinding.buttonDeleteProduct.setImageDrawable(
                    mViewModel.getApplication().getResources().getDrawable(R.drawable.ic_delete));

            mRowBinding.buttonAddProduct.setImageDrawable(
                    mViewModel.getApplication().getResources().getDrawable(R.drawable.ic_add));
        }


        public void bindProduct(int position) {

            Product product = mViewModel.getCartProducts().get(position);
            mRowBinding.textViewProductName.setText(product.getName());
            mRowBinding.productPrice.setText(
                    mViewModel.getApplication().getString(
                            R.string.product_price,
                            StringUtils.getFormattedPrice(product.getPrice())));
            Picasso.get()
                    .load(product.getImagesUrl().get(0))
                    .placeholder(R.drawable.ic_image)
                    .into(mRowBinding.imageViewProductImage);
        }
    }
}

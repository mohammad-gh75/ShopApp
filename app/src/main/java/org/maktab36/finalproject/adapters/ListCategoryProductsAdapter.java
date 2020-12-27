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
import org.maktab36.finalproject.databinding.ListRowSearchAndCategoryProductBinding;
import org.maktab36.finalproject.utils.StringUtils;
import org.maktab36.finalproject.viewmodel.CategoryProductViewModel;

public class ListCategoryProductsAdapter
        extends RecyclerView.Adapter<ListCategoryProductsAdapter.ProductHolder> {

    CategoryProductViewModel mViewModel;

    public ListCategoryProductsAdapter(CategoryProductViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListRowSearchAndCategoryProductBinding rowCategoryProductBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_search_and_category_product,
                        parent,
                        false);
        return new ProductHolder(rowCategoryProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getCategoryProducts().size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        private ListRowSearchAndCategoryProductBinding mProductBinding;
        private Product mProduct;

        public ProductHolder(ListRowSearchAndCategoryProductBinding rowBinding) {
            super(rowBinding.getRoot());
            mProductBinding = rowBinding;

            mProductBinding.getRoot().setOnClickListener(view -> {
                mViewModel.onProductSelectedLiveData(mProduct);
            });
        }

        public void bindProduct(int position) {
            mProduct = mViewModel.getCategoryProducts().get(position);
            mProductBinding.textViewProductName.setText(mProduct.getName());
            mProductBinding.textViewProductRate.setText(String.valueOf(mProduct.getRate()));
            mProductBinding.productPrice.setText(
                    mViewModel.getApplication().getString(
                            R.string.product_price,
                            StringUtils.getFormattedPrice(mProduct.getPrice())));
            Picasso.get()
                    .load(mProduct.getImagesUrl().get(0))
                    .placeholder(R.drawable.ic_image)
                    .into(mProductBinding.imageViewProductImage);
        }
    }

}

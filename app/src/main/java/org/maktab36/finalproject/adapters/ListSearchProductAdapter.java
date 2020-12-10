package org.maktab36.finalproject.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.databinding.ListRowSearchProductBinding;
import org.maktab36.finalproject.utils.StringUtils;
import org.maktab36.finalproject.viewmodel.ProductViewModel;
import org.maktab36.finalproject.viewmodel.SearchViewModel;

public class ListSearchProductAdapter
        extends RecyclerView.Adapter<ListSearchProductAdapter.SearchViewHolder> {

    private SearchViewModel mViewModel;

    public ListSearchProductAdapter(SearchViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListRowSearchProductBinding rowSearchProductBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_search_product,
                        parent,
                        false);
        return new SearchViewHolder(rowSearchProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        Log.d("reza", "getItemCount: "+mViewModel.getSearchProducts().size());
        return mViewModel.getSearchProducts().size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private ListRowSearchProductBinding mProductBinding;
        private Product mProduct;

        public SearchViewHolder(ListRowSearchProductBinding rowBinding) {
            super(rowBinding.getRoot());
            mProductBinding = rowBinding;

            mProductBinding.getRoot().setOnClickListener(view -> {
                mViewModel.onProductSelectedLiveData(mProduct);
            });
        }

        public void bindProduct(int position) {
            mProduct = mViewModel.getSearchProducts().get(position);
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

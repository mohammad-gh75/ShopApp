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
import org.maktab36.finalproject.databinding.ListRowMostPointsProductBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;

import java.util.Locale;

public class ListMostPointsProductAdapter extends
        RecyclerView.Adapter<ListMostPointsProductAdapter.MostPointsProductViewHolder> {

    private MainViewModel mViewModel;

    public ListMostPointsProductAdapter(MainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public MostPointsProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ListRowMostPointsProductBinding rowProductBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_most_points_product,
                        parent,
                        false);

        return new MostPointsProductViewHolder(rowProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MostPointsProductViewHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getMostPointsProducts().size();
    }

    public class MostPointsProductViewHolder extends RecyclerView.ViewHolder {
        private ListRowMostPointsProductBinding mRowProductBinding;
        private Product mProduct;

        public MostPointsProductViewHolder(ListRowMostPointsProductBinding rowProductBinding) {
            super(rowProductBinding.getRoot());
            mRowProductBinding = rowProductBinding;

            mRowProductBinding.getRoot().setOnClickListener(view -> {
                mViewModel.onProductSelectedLiveData(mProduct);
            });

        }

        public void bindProduct(int position) {
            if(position==mViewModel.getMostPointsProducts().size()-1){
                mRowProductBinding.divider.setVisibility(View.GONE);
            }
            mProduct = mViewModel.getMostPointsProducts().get(position);
            mRowProductBinding.textViewProductName.setText(mProduct.getName());
            String price= String.format(new Locale("fa"),
                    "%,d",
                    Long.parseLong(mProduct.getPrice()));
            mRowProductBinding.textViewProductPrice.setText(mViewModel.getApplication()
                    .getString(
                            R.string.product_price,
                            price));
            Picasso.get()
                    .load(mProduct.getImagesUrl().get(0))
                    .placeholder(R.drawable.ic_image)
                    .into(mRowProductBinding.imageViewProductImage);
        }
    }
}

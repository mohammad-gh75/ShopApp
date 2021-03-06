package org.maktab36.finalproject.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.databinding.ListRowMostViewAndLastProductBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;

import java.util.List;

public class ListLastProductAdapter extends
        RecyclerView.Adapter<ListLastProductAdapter.LastProductViewHolder> {

    private MainViewModel mViewModel;

    public ListLastProductAdapter(MainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public LastProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ListRowMostViewAndLastProductBinding rowProductBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_most_view_and_last_product,
                        parent,
                        false);

        return new LastProductViewHolder(rowProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LastProductViewHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getLastProducts().size();
    }

    public class LastProductViewHolder extends RecyclerView.ViewHolder {
        private ListRowMostViewAndLastProductBinding mRowProductBinding;
        private Product mProduct;

        public LastProductViewHolder(ListRowMostViewAndLastProductBinding rowProductBinding) {
            super(rowProductBinding.getRoot());
            mRowProductBinding = rowProductBinding;

            mRowProductBinding.getRoot().setOnClickListener(view ->
                    mViewModel.onProductSelectedLiveData(mProduct));

        }

        public void bindProduct(int position) {
            Log.d("tag", "bindProduct: ");
            mProduct = mViewModel.getLastProducts().get(position);
            mRowProductBinding.textViewProductNumber.setText(String.valueOf(position + 1));
            mRowProductBinding.textViewProductName.setText(mProduct.getName());
            List<String> imagesUrl=mProduct.getImagesUrl();
            if(imagesUrl.isEmpty()){
                mRowProductBinding.imageViewProductImage
                        .setImageDrawable(mViewModel
                                .getApplication()
                                .getResources()
                                .getDrawable(R.drawable.ic_image));
            }else{
                Picasso.get()
                        .load(imagesUrl.get(0))
                        .placeholder(R.drawable.ic_image)
                        .into(mRowProductBinding.imageViewProductImage);
            }

        }
    }
}

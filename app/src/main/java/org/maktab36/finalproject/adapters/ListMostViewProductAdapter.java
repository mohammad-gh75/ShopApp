package org.maktab36.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.databinding.ListRowProductBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;

public class ListMostViewProductAdapter extends
        RecyclerView.Adapter<ListMostViewProductAdapter.MostViewProductViewHolder> {

    private MainViewModel mViewModel;

    public ListMostViewProductAdapter(MainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public MostViewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ListRowProductBinding rowProductBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_product,
                        parent,
                        false);

        return new MostViewProductViewHolder(rowProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewProductViewHolder holder, int position) {
        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getMostViewProducts().size();
    }

    public class MostViewProductViewHolder extends RecyclerView.ViewHolder {
        private ListRowProductBinding mRowProductBinding;
        private int mPosition;

        public MostViewProductViewHolder(ListRowProductBinding rowProductBinding) {
            super(rowProductBinding.getRoot());
            mRowProductBinding = rowProductBinding;

            mRowProductBinding.getRoot().setOnClickListener(view -> {

            });

        }

        public void bindProduct(int position) {
            mPosition = position;
            Product product = mViewModel.getMostViewProducts().get(position);
            mRowProductBinding.textViewProductNumber.setText(String.valueOf(position + 1));
            mRowProductBinding.textViewProductName.setText(product.getName());
            Picasso.get()
                    .load(product.getImagesUrl().get(0))
                    .placeholder(R.drawable.ic_image)
                    .into(mRowProductBinding.imageViewProductImage);
        }
    }
}

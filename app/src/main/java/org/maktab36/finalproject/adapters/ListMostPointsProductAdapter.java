package org.maktab36.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.ListRowProductBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;

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
        ListRowProductBinding rowProductBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.list_row_product,
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
        return 7;
    }

    public class MostPointsProductViewHolder extends RecyclerView.ViewHolder {
        private ListRowProductBinding mRowProductBinding;
        private int mPosition;

        public MostPointsProductViewHolder(ListRowProductBinding rowProductBinding) {
            super(rowProductBinding.getRoot());
            mRowProductBinding = rowProductBinding;

            mRowProductBinding.getRoot().setOnClickListener(view -> {
                Toast.makeText(mViewModel.getApplication(), String.valueOf(mPosition+1), Toast.LENGTH_SHORT).show();
            });

        }

        public void bindProduct(int position) {
            mPosition=position;
            mRowProductBinding.imageViewProductImage.setImageDrawable(
                    mViewModel.getApplication().getResources().getDrawable(R.drawable.ic_image));
            mRowProductBinding.textViewProductNumber.setText(String.valueOf(position+1));
            mRowProductBinding.textViewProductName.setText("this is number "+position+" product");
        }
    }
}

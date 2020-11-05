package org.maktab36.finalproject.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListProductImageAdapter extends
        RecyclerView.Adapter<ListProductImageAdapter.ProductImageViewHolder> {


    @NonNull
    @Override
    public ProductImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProductImageViewHolder extends RecyclerView.ViewHolder{

        public ProductImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

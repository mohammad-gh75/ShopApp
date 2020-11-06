package org.maktab36.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.SliderViewAdapter;

import org.maktab36.finalproject.viewmodel.ProductViewModel;

public class ListProductImageAdapter extends
        SliderViewAdapter<ListProductImageAdapter.SliderViewHolder> {
    private ProductViewModel mViewModel;

    public ListProductImageAdapter(ProductViewModel viewModel) {
        mViewModel = viewModel;
    }


    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());

        return null;
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {

        public SliderViewHolder(View itemView) {
            super(itemView);
        }
    }
}

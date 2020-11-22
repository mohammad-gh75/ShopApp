package org.maktab36.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.databinding.SliderSpecialProductBinding;
import org.maktab36.finalproject.viewmodel.MainViewModel;
import org.maktab36.finalproject.viewmodel.ProductViewModel;

import java.util.List;

public class ListSpecialProductAdapter extends
        SliderViewAdapter<ListSpecialProductAdapter.SliderSpecialViewHolder> {

    private MainViewModel mViewModel;


    public ListSpecialProductAdapter(MainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public SliderSpecialViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SliderSpecialProductBinding productBinding=
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.slider_special_product,
                        parent,
                        false);
//        View view = inflater.inflate(R.layout.slider_special_product, null);
//        return new SliderSpecialViewHolder(view);
        return new SliderSpecialViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(SliderSpecialViewHolder viewHolder, int position) {
        viewHolder.bindProduct(position);
    }

    @Override
    public int getCount() {
        return mViewModel.getSpecialProducts().size();
    }

    public class SliderSpecialViewHolder extends SliderViewAdapter.ViewHolder {
        private Product mProduct;
        private SliderSpecialProductBinding mProductBinding;

        /*public SliderSpecialViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view ->
                    mViewModel.onProductSelectedLiveData(mProduct));
        }*/

        public SliderSpecialViewHolder(SliderSpecialProductBinding productBinding) {
            super(productBinding.getRoot());
            mProductBinding=productBinding;

            productBinding.getRoot().setOnClickListener(view ->
                    mViewModel.onProductSelectedLiveData(mProduct));
        }

        public void bindProduct(int position) {
            mProduct=mViewModel.getSpecialProducts().get(position);
            mProductBinding.textViewProductName.setText(mProduct.getName());
            Picasso.get()
                    .load(mProduct.getImagesUrl().get(0))
                    .placeholder(R.drawable.ic_image)
                    .into(mProductBinding.imageViewProductImage);
            /*Glide.with(mViewModel.getApplication())
                    .asBitmap()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(mImageView);*/
        }
    }
}

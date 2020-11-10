package org.maktab36.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.viewmodel.ProductViewModel;

import java.util.List;

public class ListProductImageAdapter extends
        SliderViewAdapter<ListProductImageAdapter.SliderViewHolder> {

    private ProductViewModel mViewModel;
    private List<String> paths;

    public ListProductImageAdapter(ProductViewModel viewModel) {
        mViewModel = viewModel;
        paths=mViewModel.getSelectedProduct().getImagesUrl();
    }


    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.slider_image_slide,null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        String imageUrl = paths.get(position);
        viewHolder.bindProduct(imageUrl);
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView mImageView;

        public SliderViewHolder(View view) {
            super(view);
            mImageView=view.findViewById(R.id.slide_image_product);
        }

        public void bindProduct(String imageUrl){
            Glide.with(mViewModel.getApplication())
                    .asBitmap()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_image)
                    .into(mImageView);
        }
    }
}

package org.maktab36.finalproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private MutableLiveData<Product> mSelectedProductLiveData;


    public LiveData<Product> getSelectedProductLiveData() {
        return mSelectedProductLiveData;
    }

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository=ProductRepository.getInstance();
        mSelectedProductLiveData=mRepository.getSelectedProductLiveData();
    }

    public void fetchSelectedProductLiveData(int id) {
        mRepository.fetchSelectedProductLiveData(id);
    }

    public Product getSelectedProduct(){
        Product product =mSelectedProductLiveData.getValue();
        return product == null ? new Product() : product;
    }

    public void addToCart(){
        mRepository.addToCart(getSelectedProduct().getId());
    }

}

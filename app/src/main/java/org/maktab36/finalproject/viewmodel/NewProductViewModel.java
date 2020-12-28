package org.maktab36.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class NewProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private MutableLiveData<Product> mSelectedProductLiveData;
    private List<Product> mNewProducts=new ArrayList<>();

    public NewProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mSelectedProductLiveData = mRepository.getSelectedProductLiveData();
    }

    public LiveData<Product> getSelectedProductLiveData() {
        return mSelectedProductLiveData;
    }

    public void onProductSelectedLiveData(Product product) {
        mSelectedProductLiveData.setValue(product);
    }

    public void setProductList(List<Product> products){
        mNewProducts=products;
    }

    public List<Product> getNewProducts() {
        return mNewProducts;
    }
}

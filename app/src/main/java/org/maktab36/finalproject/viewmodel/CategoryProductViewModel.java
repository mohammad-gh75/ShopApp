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

public class CategoryProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mCategoryProductsLiveData;
    private MutableLiveData<Product> mSelectedProductLiveData;

    public CategoryProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mCategoryProductsLiveData = mRepository.getCategoryProductsLiveData();
        mSelectedProductLiveData = mRepository.getSelectedProductLiveData();
    }

    public LiveData<List<Product>> getCategoryProductsLiveData() {
        return mCategoryProductsLiveData;
    }

    public MutableLiveData<Product> getSelectedProductLiveData() {
        return mSelectedProductLiveData;
    }

    public void fetchCategoryProductsLiveData(int categoryId,String orderBy,String order) {
        mRepository.fetchCategoryProductsLiveData(categoryId,orderBy,order);
    }

    public List<Product> getCategoryProducts() {
        List<Product> products = mCategoryProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

    public void onProductSelectedLiveData(Product product) {
        mSelectedProductLiveData.setValue(product);
    }
}

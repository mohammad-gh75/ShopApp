package org.maktab36.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mSearchProductsLiveData;
    private MutableLiveData<Product> mSelectedProductLiveData;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mSearchProductsLiveData = mRepository.getSearchProductsLiveData();
        mSelectedProductLiveData = mRepository.getSelectedProductLiveData();
    }

    public LiveData<List<Product>> getSearchProductsLiveData() {
        return mSearchProductsLiveData;
    }

    public MutableLiveData<Product> getSelectedProductLiveData() {
        return mSelectedProductLiveData;
    }

    public void fetchSearchProductsLiveData(String query) {
        mRepository.fetchSearchProductsLiveData(query);
    }

    public List<Product> getSearchProducts() {
        List<Product> products = mSearchProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

    public void onProductSelectedLiveData(Product product) {
        mSelectedProductLiveData.setValue(product);
    }
}

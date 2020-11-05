package org.maktab36.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mLastProductsLiveData;
    private LiveData<List<Product>> mMostViewProductsLiveData;
    private LiveData<List<Product>> mMostPointsProductsLiveData;

    public LiveData<List<Product>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public LiveData<List<Product>> getMostViewProductsLiveData() {
        return mMostViewProductsLiveData;
    }

    public LiveData<List<Product>> getMostPointsProductsLiveData() {
        return mMostPointsProductsLiveData;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mLastProductsLiveData = mRepository.getLastProductsLiveData();
        mMostViewProductsLiveData=mRepository.getMostViewProductsLiveData();
        mMostPointsProductsLiveData=mRepository.getMostPointsProductsLiveData();
    }

    public void fetchLastProductsLiveData() {
        mRepository.fetchLastProductsLiveData();
    }
    public void fetchMostViewProductsLiveData() {
        mRepository.fetchMostViewProductsLiveData();
    }
    public void fetchMostPointsProductsLiveData() {
        mRepository.fetchMostPointsProductsLiveData();
    }
    public void fetchProductsLiveData(){
        fetchLastProductsLiveData();
        fetchMostViewProductsLiveData();
        fetchMostPointsProductsLiveData();
    }


    public List<Product> getLastProducts() {
        List<Product> products = mLastProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

    public List<Product> getMostViewProducts() {
        List<Product> products = mMostViewProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

    public List<Product> getMostPointsProducts() {
        List<Product> products = mMostPointsProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

}

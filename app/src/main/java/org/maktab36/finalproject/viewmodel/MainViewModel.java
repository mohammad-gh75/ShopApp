package org.maktab36.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;
import org.maktab36.finalproject.worker.PollWorker;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mLastProductsLiveData;
    private LiveData<List<Product>> mMostViewProductsLiveData;
    private LiveData<List<Product>> mMostPointsProductsLiveData;
    private MutableLiveData<Product> mSelectedProductLiveData;
    private LiveData<List<Product>> mSpecialProductsLiveData;

    public LiveData<List<Product>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public LiveData<List<Product>> getMostViewProductsLiveData() {
        return mMostViewProductsLiveData;
    }

    public LiveData<List<Product>> getMostPointsProductsLiveData() {
        return mMostPointsProductsLiveData;
    }

    public LiveData<Product> getSelectedProductLiveData() {
        return mSelectedProductLiveData;
    }

    public LiveData<List<Product>> getSpecialProductsLiveData() {
        return mSpecialProductsLiveData;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mLastProductsLiveData = mRepository.getLastProductsLiveData();
        mMostViewProductsLiveData = mRepository.getMostViewProductsLiveData();
        mMostPointsProductsLiveData = mRepository.getMostPointsProductsLiveData();
        mSelectedProductLiveData = mRepository.getSelectedProductLiveData();
        mSpecialProductsLiveData = mRepository.getSpecialProductsLiveData();
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

    public void fetchSpecialProductsLiveData() {
        mRepository.fetchSpecialProductsLiveData();
    }

    public void fetchProductsLiveData() {
        fetchLastProductsLiveData();
        fetchMostViewProductsLiveData();
        fetchMostPointsProductsLiveData();
        fetchSpecialProductsLiveData();
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

    public List<Product> getSpecialProducts() {
        List<Product> products = mSpecialProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

    public void onProductSelectedLiveData(Product product) {
        mSelectedProductLiveData.setValue(product);
    }

    public void setPollWorker(int time) {
        PollWorker.scheduleWork(getApplication(),time);
    }
}

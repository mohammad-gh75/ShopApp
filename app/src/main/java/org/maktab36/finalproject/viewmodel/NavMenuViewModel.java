package org.maktab36.finalproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.maktab36.finalproject.data.model.Categories;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class NavMenuViewModel extends ViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Categories>> mProductCategoriesLiveData;

    public NavMenuViewModel() {
        mRepository = ProductRepository.getInstance();
        mProductCategoriesLiveData = mRepository.getProductCategoriesLiveData();
    }

    public LiveData<List<Categories>> getProductCategoriesLiveData() {
        return mProductCategoriesLiveData;
    }

    public void fetchProductCategoriesLiveData() {
        mRepository.fetchProductCategoriesLiveData();
    }

    public List<Categories> getCategories() {
        List<Categories> categories = mProductCategoriesLiveData.getValue();
        return categories == null ? new ArrayList<>() : categories;
    }

    public List<Categories> getHeadingCategories() {
        List<Categories> headCategories =new ArrayList<>();
        for (Categories category:getCategories()) {
            if(category.getParentId()==0){
                headCategories.add(category);
            }
        }
        return headCategories;
    }

    public List<Categories> getSubCategories(int parentId) {
        List<Categories> subCategories = new ArrayList<>();
        for (Categories category:getCategories()) {
            if(category.getParentId()==parentId){
                subCategories.add(category);
            }
        }
        return subCategories;
    }
}

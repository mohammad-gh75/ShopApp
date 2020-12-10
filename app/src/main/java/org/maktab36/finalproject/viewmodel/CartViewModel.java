package org.maktab36.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mCartProductsLiveData;

    public CartViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mCartProductsLiveData = mRepository.getCartProductsLiveData();
    }

    public LiveData<List<Product>> getCartProductsLiveData() {
        return mCartProductsLiveData;
    }

    public void fetchCartProductsLiveData() {
        mRepository.fetchCartProductLiveData();
    }

    public List<Product> getCartProducts() {
        List<Product> products = mCartProductsLiveData.getValue();
        return products == null ? new ArrayList<>() : products;
    }

    public String getProductsSumPrice() {
        int sumPrice = 0;
        for (Product product : getCartProducts()) {
            sumPrice += Integer.parseInt(product.getPrice());
        }
        return String.valueOf(sumPrice);
    }

    public String getProductsSumReduction() {
        return String.valueOf(0);
    }

    public String getCartSumPrice() {
        int cartSumPrice =
                Integer.parseInt(getProductsSumPrice())
                        - Integer.parseInt(getProductsSumReduction());
        return String.valueOf(cartSumPrice);
    }
}

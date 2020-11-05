package org.maktab36.finalproject.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.remote.NetworkParams;
import org.maktab36.finalproject.data.remote.retrofit.ProductDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.RetrofitInstance;
import org.maktab36.finalproject.data.remote.retrofit.WoocommerceService;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {

    public static ProductRepository sInstance;
    private WoocommerceService mWoocommerceService;
    private MutableLiveData<List<Product>> mProductsLiveData;

    public static ProductRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ProductRepository();
        }
        return sInstance;
    }

    private ProductRepository() {
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        Object typeAdapter = new ProductDeserializer();

        Retrofit retrofit = RetrofitInstance.getInstance(type, typeAdapter);
        mWoocommerceService = retrofit.create(WoocommerceService.class);
    }

    public MutableLiveData<List<Product>> getProductsLiveData() {
        return mProductsLiveData;
    }

    public void fetchProductLiveData() {
        Call<List<Product>> call = mWoocommerceService.listProducts(NetworkParams.getProducts());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }
}

package org.maktab36.finalproject.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.remote.NetworkParams;
import org.maktab36.finalproject.data.remote.retrofit.ProductDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.ProductListDeserializer;
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
    private MutableLiveData<List<Product>> mLastProductsLiveData =new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostViewProductsLiveData =new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostPointsProductsLiveData =new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSpecialProductsLiveData =new MutableLiveData<>();
    private MutableLiveData<Product> mSelectedProductLiveData =new MutableLiveData<>();

    public static ProductRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ProductRepository();
        }
        return sInstance;
    }

    private ProductRepository() {
        Type type1 = new TypeToken<List<Product>>() {}.getType();
        Object typeAdapter1 = new ProductListDeserializer();

        Type type2 = new TypeToken<Product>() {}.getType();
        Object typeAdapter2 = new ProductDeserializer();

        Retrofit retrofit = RetrofitInstance.getInstance(type1, typeAdapter1,type2,typeAdapter2);
        mWoocommerceService = retrofit.create(WoocommerceService.class);
    }

    public MutableLiveData<List<Product>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public MutableLiveData<List<Product>> getMostViewProductsLiveData() {
        return mMostViewProductsLiveData;
    }

    public MutableLiveData<List<Product>> getMostPointsProductsLiveData() {
        return mMostPointsProductsLiveData;
    }

    public MutableLiveData<Product> getSelectedProductLiveData() {
        return mSelectedProductLiveData;
    }

    public MutableLiveData<List<Product>> getSpecialProductsLiveData() {
        return mSpecialProductsLiveData;
    }

    public void fetchLastProductsLiveData() {
        Call<List<Product>> call =
                mWoocommerceService.listProducts(NetworkParams.getLastProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mLastProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(),t);
            }
        });
    }

    public void fetchMostViewProductsLiveData(){
        Call<List<Product>> call =
                mWoocommerceService.listProducts(NetworkParams.getMostViewProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mMostViewProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(),t);
            }
        });
    }

    public void fetchMostPointsProductsLiveData(){
        Call<List<Product>> call =
                mWoocommerceService.listProducts(NetworkParams.getMostPointsProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mMostPointsProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(),t);
            }
        });
    }

    public void fetchSpecialProductsLiveData(){
        Call<List<Product>> call =
                mWoocommerceService.listProducts(NetworkParams.getSpecialProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mSpecialProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(),t);
            }
        });
    }

    public void fetchSelectedProductLiveData(int id){
        Call<Product> call =mWoocommerceService.getProduct(
                id,
                NetworkParams.getBaseOptions()
        );
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Log.d("tag", call.request().url().toString());
                mSelectedProductLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("tag", t.toString(),t);
            }
        });
    }
}

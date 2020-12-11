package org.maktab36.finalproject.data.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.remote.NetworkParams;
import org.maktab36.finalproject.data.remote.retrofit.ProductDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.ProductListDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.RetrofitInstance;
import org.maktab36.finalproject.data.remote.retrofit.WoocommerceService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {

    public static ProductRepository sInstance;
    private WoocommerceService mWoocommerceServiceProductList;
    private WoocommerceService mWoocommerceServiceProduct;
    private MutableLiveData<List<Product>> mLastProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostViewProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostPointsProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSpecialProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> mSelectedProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mCartProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSearchProductsLiveData = new MutableLiveData<>();
    private List<Integer> mCartProductsId = new ArrayList<>();

    public static ProductRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ProductRepository();
        }
        return sInstance;
    }

    private ProductRepository() {
        Type typeProductList = new TypeToken<List<Product>>() {
        }.getType();
        Object typeAdapterProductList = new ProductListDeserializer();

        Type typeProduct = new TypeToken<Product>() {
        }.getType();
        Object typeAdapterProduct = new ProductDeserializer();

        Retrofit retrofitProductList = RetrofitInstance.getInstance(typeProductList, typeAdapterProductList);
        Retrofit retrofitProduct = RetrofitInstance.getInstance(typeProduct, typeAdapterProduct);
        mWoocommerceServiceProductList = retrofitProductList.create(WoocommerceService.class);
        mWoocommerceServiceProduct = retrofitProduct.create(WoocommerceService.class);
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

    public MutableLiveData<List<Product>> getCartProductsLiveData() {
        return mCartProductsLiveData;
    }

    public MutableLiveData<List<Product>> getSearchProductsLiveData() {
        return mSearchProductsLiveData;
    }

    public List<Integer> getCartProductsId() {
        return mCartProductsId;
    }

    public void addToCart(int id) {
        mCartProductsId.add(id);
    }

    public void fetchLastProductsLiveData() {
        Call<List<Product>> call =
                mWoocommerceServiceProductList
                        .listProducts(NetworkParams.getLastProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mLastProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(), t);
            }
        });
    }

    public void fetchMostViewProductsLiveData() {
        Call<List<Product>> call =
                mWoocommerceServiceProductList
                        .listProducts(NetworkParams.getMostViewProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mMostViewProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(), t);
            }
        });
    }

    public void fetchMostPointsProductsLiveData() {
        Call<List<Product>> call =
                mWoocommerceServiceProductList
                        .listProducts(NetworkParams.getMostPointsProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mMostPointsProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(), t);
            }
        });
    }

    public void fetchSpecialProductsLiveData() {
        Call<List<Product>> call =
                mWoocommerceServiceProductList
                        .listProducts(NetworkParams.getSpecialProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mSpecialProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(), t);
            }
        });
    }

    public void fetchSelectedProductLiveData(int id) {
        Call<Product> call = mWoocommerceServiceProduct.getProduct(
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
                Log.d("tag", t.toString(), t);
            }
        });
    }

    public void fetchCartProductLiveData() {
        List<Product> products = new ArrayList<>();
        for (int id : mCartProductsId) {
            Call<Product> call = mWoocommerceServiceProduct.getProduct(
                    id,
                    NetworkParams.getBaseOptions());
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    Log.d("reza", "onResponse: " + response.body().getName());
                    products.add(response.body());
                    mCartProductsLiveData.setValue(products);
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Log.d("reza", t.toString(), t);
                }
            });
        }
    }

    public void fetchSearchProductsLiveData(String query,String orderBy,String order) {
        Call<List<Product>> call =
                mWoocommerceServiceProductList
                        .listProducts(NetworkParams.getSearchProductsOptions(
                                query,
                                orderBy,
                                order));

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mSearchProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("reza", t.toString(), t);
            }
        });
    }
}

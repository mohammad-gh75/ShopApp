package org.maktab36.finalproject.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;

import org.maktab36.finalproject.data.model.Categories;
import org.maktab36.finalproject.data.model.Customer;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.remote.NetworkParams;
import org.maktab36.finalproject.data.remote.retrofit.CategoriesDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.CustomerDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.ProductDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.ProductListDeserializer;
import org.maktab36.finalproject.data.remote.retrofit.RetrofitInstance;
import org.maktab36.finalproject.data.remote.retrofit.WoocommerceService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {

    public static ProductRepository sInstance;
    private WoocommerceService mWoocommerceServiceProductList;
    private WoocommerceService mWoocommerceServiceProduct;
    private WoocommerceService mWoocommerceServiceCategories;
    private WoocommerceService mWoocommerceServiceCustomer;
    private MutableLiveData<List<Product>> mLastProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostViewProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostPointsProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSpecialProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> mSelectedProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mCartProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSearchProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Categories>> mProductCategoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mCategoryProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<Customer> mCustomerLiveData = new MutableLiveData<>();
    private MutableLiveData<Customer> mNewCustomerLiveData = new MutableLiveData<>();
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

        Type typeCategories = new TypeToken<List<Categories>>() {
        }.getType();
        Object typeAdapterCategories = new CategoriesDeserializer();

        Type typeCustomer=new TypeToken<Customer>(){
        }.getType();
        Object typeAdapterCustomer = new CustomerDeserializer();

        Retrofit retrofitProductList = RetrofitInstance
                .getInstance(typeProductList, typeAdapterProductList);
        Retrofit retrofitProduct = RetrofitInstance
                .getInstance(typeProduct, typeAdapterProduct);
        Retrofit retrofitCategories = RetrofitInstance
                .getInstance(typeCategories, typeAdapterCategories);
        Retrofit retrofitCustomers = RetrofitInstance
                .getInstance(typeCustomer, typeAdapterCustomer);

        mWoocommerceServiceProductList = retrofitProductList.create(WoocommerceService.class);
        mWoocommerceServiceProduct = retrofitProduct.create(WoocommerceService.class);
        mWoocommerceServiceCategories = retrofitCategories.create(WoocommerceService.class);
        mWoocommerceServiceCustomer=retrofitCustomers.create(WoocommerceService.class);
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

    public MutableLiveData<List<Categories>> getProductCategoriesLiveData() {
        return mProductCategoriesLiveData;
    }

    public MutableLiveData<List<Product>> getCategoryProductsLiveData() {
        return mCategoryProductsLiveData;
    }

    public MutableLiveData<Customer> getCustomerLiveData() {
        return mCustomerLiveData;
    }

    public MutableLiveData<Customer> getNewCustomerLiveData() {
        return mNewCustomerLiveData;
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

    public void fetchSearchProductsLiveData(String query, String orderBy, String order) {
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

    public void fetchProductCategoriesLiveData() {
        Call<List<Categories>> call =
                mWoocommerceServiceCategories
                        .getCategories(NetworkParams.getCategoriesOptions());
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                mProductCategoriesLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.d("reza", t.toString(), t);
            }
        });
    }

    public void fetchCategoryProductsLiveData(int categoryId, String orderBy, String order) {
        Call<List<Product>> call =
                mWoocommerceServiceProductList
                        .listProducts(NetworkParams.getCategoryProductsOptions(
                                categoryId,
                                orderBy,
                                order));

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("tag", call.request().url().toString());
                mCategoryProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("tag", t.toString(), t);
            }
        });
    }

    public List<Product> getLastProductsSync() {
        Call<List<Product>> call = mWoocommerceServiceProductList
                .listProducts(NetworkParams.getLastProductsOptions());
        List<Product> products = new ArrayList<>();

        try {
            products = call.execute().body();
        } catch (IOException e) {
            Log.e("reza2", e.getMessage(), e);
        }
        /*finally {
            return products;
        }*/
        return products;
    }

    public void fetchCustomerLiveData(String email){
        Call<Customer> call=mWoocommerceServiceCustomer
                .findCustomer(NetworkParams.getCustomerByEmailOptions(email));

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                mCustomerLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.d("reza3", t.getMessage(), t);
            }
        });
    }

    public void createCustomer(String email, String firstName,String lastName, String username){
        Call<Customer> call=mWoocommerceServiceCustomer
                .signUpCustomer(
                        NetworkParams
                        .getCustomerFields(email, firstName, lastName, username));

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                mNewCustomerLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.d("reza3", t.getMessage(),t);
            }
        });
    }
}

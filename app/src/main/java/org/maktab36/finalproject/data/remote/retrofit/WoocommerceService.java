package org.maktab36.finalproject.data.remote.retrofit;

import org.maktab36.finalproject.data.model.Categories;
import org.maktab36.finalproject.data.model.Customer;
import org.maktab36.finalproject.data.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface WoocommerceService {

    @GET("products")
    Call<List<Product>> listProducts(@QueryMap Map<String, String> options);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id,@QueryMap Map<String, String> options);

    @GET("products/categories")
    Call<List<Categories>> getCategories(@QueryMap Map<String, String> options);

    @GET("customers")
    Call<Customer> findCustomer(@QueryMap Map<String, String> options);

    @POST("customers")
    Call<Customer> signUpCustomer(@QueryMap Map<String, String> fields);
}

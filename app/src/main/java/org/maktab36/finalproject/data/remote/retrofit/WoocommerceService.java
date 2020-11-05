package org.maktab36.finalproject.data.remote.retrofit;

import org.maktab36.finalproject.data.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WoocommerceService {

    @GET("products")
    Call<List<Product>> listProducts(@QueryMap Map<String, String> options);
}

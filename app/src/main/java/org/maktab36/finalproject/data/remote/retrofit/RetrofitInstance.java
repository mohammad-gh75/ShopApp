package org.maktab36.finalproject.data.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.maktab36.finalproject.data.remote.NetworkParams;

import java.lang.reflect.Type;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit getInstance(Type type1, Object typeAdapter1,Type type2, Object typeAdapter2){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_PATH)
                .addConverterFactory(createGsonConverter(type1,typeAdapter1))
                .addConverterFactory(createGsonConverter(type2,typeAdapter2))
                .build();

        return retrofit;
    }

    public static Converter.Factory createGsonConverter(Type type,Object typeAdapter){
        GsonBuilder builder=new GsonBuilder()
                .registerTypeAdapter(type,typeAdapter);

        Gson gson=builder.create();
        return GsonConverterFactory.create(gson);
    }
}

package org.maktab36.finalproject.data.remote.retrofit;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.maktab36.finalproject.data.model.Product;

import java.lang.reflect.Type;
import java.util.List;

public class ProductDeserializer implements JsonDeserializer<List<Product>> {
    @Override
    public List<Product> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {



        return null;
    }
}

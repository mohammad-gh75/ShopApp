package org.maktab36.finalproject.data.remote.retrofit;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.maktab36.finalproject.data.model.Customer;
import org.maktab36.finalproject.data.model.Product;

import java.lang.reflect.Type;
import java.util.List;

public class CustomerDeserializer implements JsonDeserializer<Customer> {
    @Override
    public Customer deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonObject customerObject=json.getAsJsonObject();

        int id = customerObject.get("id").getAsInt();
        String username = customerObject.get("username").getAsString();
        String email = customerObject.get("email").getAsString();
        String role = customerObject.get("role").getAsString();

        return new Customer(id,username,email,role);
    }
}

package org.maktab36.finalproject.data.remote.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.maktab36.finalproject.data.model.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductDeserializer implements JsonDeserializer<Product> {
    @Override
    public Product deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonObject productObject = json.getAsJsonObject();

        int id = productObject.get("id").getAsInt();
        String name = productObject.get("name").getAsString();
        String description = productObject.get("description").getAsString();
        String price = productObject.get("price").getAsString();
//        String linkPath = getProductLink(product);
        List<String> imagesUrl = getProductImagesUrl(productObject);

        return new Product(id, name, description, price, imagesUrl);
    }

    private List<String> getProductImagesUrl(JsonObject productObject) {
        List<String> imagesUrl = new ArrayList<>();
        JsonArray images = productObject.get("images").getAsJsonArray();
        for (int i = 0; i < images.size(); i++) {
            JsonObject image = images.get(i).getAsJsonObject();
            String link = image.get("src").getAsString();
            imagesUrl.add(link);
        }
        return imagesUrl;
    }
}

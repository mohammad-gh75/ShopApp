package org.maktab36.finalproject.data.remote.retrofit;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.maktab36.finalproject.data.model.Categories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDeserializer implements JsonDeserializer<List<Categories>> {
    @Override
    public List<Categories> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        List<Categories> categoryList = new ArrayList<>();
        JsonArray categoryArray=json.getAsJsonArray();
        for (int i = 0; i < categoryArray.size(); i++) {
            JsonObject categoryObject = categoryArray.get(i).getAsJsonObject();

            int id = categoryObject.get("id").getAsInt();
            String name = categoryObject.get("name").getAsString();
            int parentId = categoryObject.get("parent").getAsInt();
            String imageUrl = getCategoryImageUrl(categoryObject);

            Categories categories=new Categories(id,name,parentId,imageUrl);
            categoryList.add(categories);
        }
        return categoryList;
    }

    public String getCategoryImageUrl(JsonObject categoryObject){
        JsonObject image = categoryObject.get("image").getAsJsonObject();
        return image.get("src").getAsString();
    }
}

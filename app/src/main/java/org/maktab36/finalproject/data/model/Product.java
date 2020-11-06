package org.maktab36.finalproject.data.model;

import java.util.List;

public class Product {
    private int mId;
    private String mName;
    private String mDescription;
    private String mPrice;
//    private String mLinkPath;
    private List<String> mImagesUrl;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    /*public String getLinkPath() {
        return mLinkPath;
    }

    public void setLinkPath(String linkPath) {
        mLinkPath = linkPath;
    }*/

    public List<String> getImagesUrl() {
        return mImagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.mImagesUrl = imagesUrl;
    }

    public Product() {
    }

    public Product(int id, String name, String description,
                   String price/*, String linkPath*/, List<String> imagesUrl) {
        mId = id;
        mName = name;
        mDescription = description;
        mPrice = price;
//        mLinkPath = linkPath;
        mImagesUrl = imagesUrl;
    }
}

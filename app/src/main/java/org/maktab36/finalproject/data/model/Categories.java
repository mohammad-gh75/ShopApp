package org.maktab36.finalproject.data.model;

public class Categories {
    private int id;
    private String name;
    private int parentId;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categories() {
    }

    public Categories(int id, String name, int parentId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.imageUrl = imageUrl;
    }
}

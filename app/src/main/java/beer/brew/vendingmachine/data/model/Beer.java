package beer.brew.vendingmachine.data.model;

import com.google.gson.annotations.SerializedName;

public class Beer {

    @SerializedName("name")
    private final String name;

    @SerializedName("price")
    private float price;

    @SerializedName("image")
    private final String image;

    public Beer(String name, float price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

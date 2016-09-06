package beer.brew.vendingmachine.data.model;

import com.google.gson.annotations.SerializedName;

public class Beer {

    @SerializedName("name")
    private final String name;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private final String image;

    public Beer(String name, String price, String image) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

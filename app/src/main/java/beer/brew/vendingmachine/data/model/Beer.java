package beer.brew.vendingmachine.data.model;

import com.google.gson.annotations.SerializedName;

public class Beer {

    @SerializedName("name")
    private final String name;

    @SerializedName("price")
    private float price;

    @SerializedName("image")
    private final String image;

    private int size;

    public static final int SIZE_SMALL = 0;
    public static final int SIZE_MIDDLE = 1;
    public static final int SIZE_BIG = 2;

    public Beer(String name, String image, int size) {
        this.name = name;
        this.image = image;
        this.size = size;
        this.setPrice(size);
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

    public int getSize() {
        return size;
    }

    private void setPrice(int size) {
        switch (size) {
            case SIZE_SMALL: price = (float) 3.0; break;
            case SIZE_MIDDLE: price = (float) 4.0; break;
            case SIZE_BIG: price = (float) 5.0; break;
            default: break;
        }
    }
}

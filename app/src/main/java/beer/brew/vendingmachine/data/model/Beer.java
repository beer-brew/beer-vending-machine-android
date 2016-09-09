package beer.brew.vendingmachine.data.model;

import java.io.Serializable;

public class Beer implements Serializable {

    private static final String DESCRIPTION = "Beer";

    private final Size size;
    private final float price;

    public Beer(Size size) {
        this.size = size;
        this.price = size.getPrice();
    }

    public String getDescription() {
        return size.getDescription() + ", " + DESCRIPTION;
    }

    public float cost() {
        return this.price;
    }

    public enum Size implements Serializable {
        SMALL((float) 3, "Small Cup"),
        MIDDLE((float) 4, "Middle Cup"),
        BIG((float) 5, "Big Cup");

        private final String description;
        private final float price;

        Size(float price, String description) {
            this.price = price;
            this.description = description;
        }

        public float getPrice() {
            return this.price;
        }

        public String getDescription() {
            return this.description;
        }
    }
}

package beer.brew.vendingmachine.data.model.beer;

import java.io.Serializable;
import java.util.List;

public class Beer implements Serializable {

    private BeerSize beerSize;
    private String description = "Beer";
    private List<String> imageUrls;
    private int price;
    private BeerType type;

    private Beer(Builder builder) {
        this.beerSize = builder.beerSize;
        this.description = builder.description;
        this.imageUrls = builder.imageUrls;
        this.price = builder.price;
        this.type = builder.type;
    }

    public static class Builder {
        private BeerSize beerSize;
        private String description;
        private List<String> imageUrls;
        private int price;
        private BeerType type;

        public Builder() {
        }

        public Builder setBeerSize(BeerSize beerSize) {
            this.beerSize = beerSize;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setType(BeerType type) {
            this.type = type;
            return this;
        }

        public Beer build() {
            return new Beer(this);
        }
    }

    public BeerSize getBeerSize() {
        return beerSize;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public int getPrice() {
        return price;
    }

    public BeerType getType() {
        return type;
    }
}

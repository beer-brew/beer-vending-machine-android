package beer.brew.vendingmachine.data.model;

public abstract class Beer {

    protected String description = "No type beer";
    protected Size size;

    public String getDescription() {
        return size.getDescription() + ", " + description;
    }

    public abstract float cost();

    public enum Size {
        SMALL((float) 3, "Small Cup"),
        MIDDLE((float) 4, "Middle Cup"),
        BIG((float) 5, "Big Cup");

        private String description;
        private float price;

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

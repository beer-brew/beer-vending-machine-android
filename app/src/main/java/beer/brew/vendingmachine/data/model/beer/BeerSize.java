package beer.brew.vendingmachine.data.model.beer;


public enum BeerSize {

    SMALL("Small Cup"),
    MIDDLE("Middle Cup"),
    BIG("Big Cup");

    private final String description;

    BeerSize(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}

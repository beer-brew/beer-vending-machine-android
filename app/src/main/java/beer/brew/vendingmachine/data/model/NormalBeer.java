package beer.brew.vendingmachine.data.model;

public class NormalBeer extends Beer {

    public NormalBeer(Size size) {
        this.description = "Base beer";
        this.size = size;
    }

    @Override
    public float cost() {
        return 0 + size.getPrice();
    }
}

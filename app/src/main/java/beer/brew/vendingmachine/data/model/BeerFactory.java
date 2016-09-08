package beer.brew.vendingmachine.data.model;

import beer.brew.vendingmachine.data.model.Beer.Size;

public class BeerFactory {

    private BeerFactory() {}

    public static Beer createNormalBeer(Size size) {
        Beer beer = new NormalBeer(size);
        return beer;
    }

}

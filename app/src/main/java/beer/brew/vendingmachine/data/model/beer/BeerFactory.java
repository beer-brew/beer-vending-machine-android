package beer.brew.vendingmachine.data.model.beer;

import beer.brew.vendingmachine.data.model.beer.Beer.Size;

public class BeerFactory {

    private BeerFactory() {}

    public static Beer createNormalBeer(Size size) {
        return new NormalBeer(size);
    }

}

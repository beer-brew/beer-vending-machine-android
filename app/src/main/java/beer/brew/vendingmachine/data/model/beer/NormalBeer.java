package beer.brew.vendingmachine.data.model.beer;

import android.os.Parcel;

public class NormalBeer extends Beer {

    public NormalBeer(Size size) {
        this.description = "Base beer";
        this.size = size;
    }

    public NormalBeer(Parcel in) {
        this.description = in.readString();
        this.size = in.readParcelable(Size.class.getClassLoader());
    }

    @Override
    public float cost() {
        this.price = 0 + size.getPrice();
        return this.price;
    }

}

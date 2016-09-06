package beer.brew.vendingmachine.data.remote;

import java.util.List;

import beer.brew.vendingmachine.data.model.Beer;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;

public interface BeersService {

    String ENDPOINT = "";

    @GET("")
    Observable<List<Beer>> getBeers();

    class Creator {

        public static BeersService newBeersService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RibotsService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(BeersService.class);
        }
    }

}

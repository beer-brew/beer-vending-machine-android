package beer.brew.vendingmachine.data.remote;

import beer.brew.vendingmachine.payment.data.Order;
import beer.brew.vendingmachine.payment.data.PayResult;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface OrderService {

    String ENDPOINT = "";

    @POST("")
    Observable<String> getOrderInfo(@Body Order order);

    @POST("")
    Observable<PayResult> getPayResult(@Body String orderId);

    class Creator {

        public static OrderService newPaymentService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RibotsService.ENDPOINT)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(OrderService.class);
        }
    }
}

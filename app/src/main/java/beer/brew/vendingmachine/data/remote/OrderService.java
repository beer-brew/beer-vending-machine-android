package beer.brew.vendingmachine.data.remote;

import beer.brew.vendingmachine.data.model.PayResult;
import beer.brew.vendingmachine.data.model.WechatpayOrder;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface OrderService {

    String ENDPOINT = "http://103.227.51.161:5150";

    @GET("/beerbrew/preorder")
    Observable<WechatpayOrder> getOrderInfo();

    @POST("")
    Observable<PayResult> getPayResult(@Body String orderId);

    class Creator {

        public static OrderService newOrderService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OrderService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(OrderService.class);
        }
    }
}

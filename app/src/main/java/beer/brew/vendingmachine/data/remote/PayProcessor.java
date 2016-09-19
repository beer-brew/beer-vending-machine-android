package beer.brew.vendingmachine.data.remote;

import android.content.Context;
import rx.Observable;

public abstract class PayProcessor {

    private final Context context;

    public PayProcessor(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract Observable<PayStatus> pay(String orderInfo);

    public enum PayStatus {
        PAY_SUCCESS,
        PAY_ERROR,
        PAY_CANCEL
    }
}

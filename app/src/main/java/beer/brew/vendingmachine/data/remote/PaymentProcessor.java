package beer.brew.vendingmachine.data.remote;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import beer.brew.vendingmachine.payment.data.Order;
import rx.Observable;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class PaymentProcessor {

    public Observable<PayStatus> pay(String orderInfo, Order.PayType payType) {
        switch (payType) {
            case ALIPAY: return payByAlipay(orderInfo);
            case WECHAT_PAY: return payByWechatPay(orderInfo);
            default: return Observable.empty();
        }
    }

    private Observable<PayStatus> payByAlipay(String orderInfo) {
        final PayTask payTask = new PayTask(context);
        return Observable.just(orderInfo)
                .subscribeOn(io())
                .map(new Func1<String, PayStatus>() {
                    @Override
                    public PayStatus call(String orderInfo) {
                        Map<String, String> payStatus = payTask.payV2(orderInfo, true);
                        return handleAlipayStatus(payStatus);
                    }
                })
                .observeOn(mainThread());
    }

    private Observable<PayStatus> payByWechatPay(String orderInfo) {
        //TODO finish wechat payment processor
        return Observable.empty();
    }

    private PayStatus handleAlipayStatus(Map<String, String> payStatus) {
        String status = payStatus.get("");
        return null;
    }

    public enum PayStatus {
        PAY_SUCCESS,
        PAY_ERROR,
        PAY_CANCEL
    }
}

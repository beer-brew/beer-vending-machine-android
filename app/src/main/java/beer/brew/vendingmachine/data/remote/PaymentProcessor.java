package beer.brew.vendingmachine.data.remote;

import android.app.Activity;
import android.content.Context;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import beer.brew.vendingmachine.payment.data.Order;
import rx.Observable;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class PaymentProcessor {

    private final Context context;

    public PaymentProcessor(Context context) {
        this.context = context;
    }

    public Observable<PayStatus> pay(String orderInfo, Order.PayType payType) {
        switch (payType) {
            case ALIPAY: return payByAlipay(orderInfo);
            case WECHAT_PAY: return payByWechatPay(orderInfo);
            default: return Observable.empty();
        }
    }

    private Observable<PayStatus> payByAlipay(String orderInfo) {
        final PayTask payTask = new PayTask((Activity) context);
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
        return Observable.just(orderInfo)
                .map(new Func1<String, PayStatus>() {
                    @Override
                    public PayStatus call(String orderInfo) {
                        IWXAPI api = WXAPIFactory.createWXAPI(context, "wx2989d1fc9d539313");
                        try {
                            JSONObject orderJson = new JSONObject(orderInfo);
                            PayReq req = new PayReq();
                            req.appId			= orderJson.getString("appid");
                            req.partnerId		= "1305176001";
                            req.prepayId		= orderJson.getString("prepay_id");
                            req.nonceStr		= orderJson.getString("nonce_str");
                            req.timeStamp		= "1473748346";
                            req.packageValue	= "wx20160913143226f9efc946130906833589";
                            req.sign			= orderJson.getString("sign");

                            api.sendReq(req);
                            return null;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
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

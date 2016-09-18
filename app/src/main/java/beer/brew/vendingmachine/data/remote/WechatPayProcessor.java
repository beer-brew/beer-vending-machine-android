package beer.brew.vendingmachine.data.remote;

import android.content.Context;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import beer.brew.vendingmachine.data.model.WechatpayOrder;
import beer.brew.vendingmachine.util.JsonUtils;
import rx.Observable;
import rx.functions.Func1;

public class WechatPayProcessor extends PayProcessor {

    public WechatPayProcessor(Context context) {
        super(context);
    }

    @Override
    public Observable<PayStatus> pay(final String orderInfo) {
        return Observable.just(orderInfo)
                .map(new Func1<String, PayStatus>() {
                    @Override
                    public PayStatus call(String content) {
                        IWXAPI api = WXAPIFactory.createWXAPI(getContext(), "wx2989d1fc9d539313");
                        WechatpayOrder order = JsonUtils.fromJson(orderInfo, WechatpayOrder.class);
                        PayReq req = new PayReq();
                        req.appId = order.getAppId();
                        req.partnerId = order.getMchId();
                        req.prepayId = order.getPrepayId();
                        req.nonceStr = order.getNonceStr();
                        req.timeStamp = "1473748346";
                        req.packageValue = "wx20160913143226f9efc946130906833589";
                        req.sign = order.getSign();

                        api.sendReq(req);
                        return null;
                    }
                });
    }
}

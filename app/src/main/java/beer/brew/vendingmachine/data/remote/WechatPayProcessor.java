package beer.brew.vendingmachine.data.remote;

import android.content.Context;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.functions.Func1;

public class WechatPayProcessor extends PayProcessor {

    public WechatPayProcessor(Context context) {
        super(context);
    }

    @Override
    public Observable<PayStatus> pay(String orderInfo) {
        return Observable.just(orderInfo)
                .map(new Func1<String, PayStatus>() {
                    @Override
                    public PayStatus call(String orderInfo) {
                        IWXAPI api = WXAPIFactory.createWXAPI(getContext(), "wx2989d1fc9d539313");
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
}

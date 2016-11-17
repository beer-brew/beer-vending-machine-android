package beer.brew.vendingmachine.util.sign;

import android.content.Context;

import java.util.Map;

import beer.brew.vendingmachine.data.model.AlipayOrder;
import beer.brew.vendingmachine.util.JsonUtils;

public class SignUtils {

    private static final String ASSERT_ALIPAY_CONFIG = "alipay-configuration-sandbox.json";

    public static String sign(Context context, AlipayOrder order) throws Exception {
        AlipayConfig alipayConfig = getAlipayConfigFromAssert(context);
        Map<String, String> params = OrderInfoUtil.buildOrderParamMap(alipayConfig.getAppId(), JsonUtils.toJson(order));
        String orderParam = OrderInfoUtil.buildOrderParam(params);
        String sign = OrderInfoUtil.getSign(params, alipayConfig.getRsaPrivate());

        return orderParam + "&" + sign;
    }

    private static AlipayConfig getAlipayConfigFromAssert(Context context) throws Exception {
        return null;
    }
}

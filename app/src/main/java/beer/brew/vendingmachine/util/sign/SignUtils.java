package beer.brew.vendingmachine.util.sign;

import android.content.Context;
import android.text.TextUtils;

import java.util.Map;

import beer.brew.vendingmachine.data.model.AlipayOrder;
import beer.brew.vendingmachine.util.AssertUtils;
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
        AlipayConfig alipayConfig = AssertUtils.getContentFromAssert(context, ASSERT_ALIPAY_CONFIG, AlipayConfig.class);
        if (alipayConfig == null
                || TextUtils.isEmpty(alipayConfig.getAppId())
                || TextUtils.isEmpty(alipayConfig.getRsaPrivate())) {
            throw new Exception("AlipayConfig should not be null!");
        }

        return alipayConfig;
    }
}

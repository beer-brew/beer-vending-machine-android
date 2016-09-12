package util.sign;

import android.content.Context;

import java.util.Map;
import beer.brew.vendingmachine.payment.data.AlipayOrder;
import beer.brew.vendingmachine.util.AssertUtils;
import beer.brew.vendingmachine.util.GsonUtils;

public class SignUtils {

	public static String sign(Context context, AlipayOrder order) throws Exception {
		AssertUtils.AlipayConfig alipayConfig = AssertUtils.getAlipayConfigFromAssert(context);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(alipayConfig.getAppId(), GsonUtils.toJsonString(order));
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String sign = OrderInfoUtil2_0.getSign(params, alipayConfig.getRsaPrivate());

		return orderParam + "&" + sign;
	}

}

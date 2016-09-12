package beer.brew.vendingmachine.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;
import java.io.Serializable;

import beer.brew.vendingmachine.injection.ApplicationContext;

public class AssertUtils {

    private static final String ASSERT_ALIPAY_CONFIG = "alipay-configuration-sandbox.json";

    public static AlipayConfig getAlipayConfigFromAssert(Context context) throws Exception {
        AlipayConfig alipayConfig = getContentFromAssert(context, ASSERT_ALIPAY_CONFIG, AlipayConfig.class);
        if (alipayConfig == null
                || TextUtils.isEmpty(alipayConfig.getAppId())
                || TextUtils.isEmpty(alipayConfig.getRsaPrivate())) {
            throw new Exception("AlipayConfig should not be null!");
        }

        return alipayConfig;
    }

    private static <T extends Serializable>T getContentFromAssert(Context context, String assetsDir, Class<T> clz) throws Exception {
        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open(assetsDir);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        String content = new String(buffer);
        return GsonUtils.parse(content, clz);
    }

    public class AlipayConfig implements Serializable {
        @SerializedName("app_id")
        String appId;

        @SerializedName("rsa_private")
        String rsaPrivate;

        public AlipayConfig(String appId, String rsaPrivate) {
            this.appId = appId;
            this.rsaPrivate = rsaPrivate;
        }

        public String getAppId() {
            return appId;
        }

        public String getRsaPrivate() {
            return rsaPrivate;
        }
    }
}

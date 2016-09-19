package util.sign;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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

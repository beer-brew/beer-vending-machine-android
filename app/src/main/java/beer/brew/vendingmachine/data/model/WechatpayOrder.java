package beer.brew.vendingmachine.data.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class WechatpayOrder implements Serializable {
    @SerializedName("nonce_str")
    private final String nonceStr;

    @SerializedName("appid")
    private final String appId;

    @SerializedName("sign")
    private final String sign;

    @SerializedName("trade_type")
    private final String tradeType;

    @SerializedName("return_msg")
    private final String returnMsg;

    @SerializedName("resut_code")
    private final String resultCode;

    @SerializedName("mch_id")
    private final String mchId;

    @SerializedName("return_code")
    private final String returnCode;

    @SerializedName("prepay_id")
    private final String prepayId;

    public WechatpayOrder(String nonceStr,
                          String appId,
                          String sign,
                          String tradeType,
                          String returnMsg,
                          String resultCode,
                          String mchId,
                          String returnCode,
                          String prepayId) {

        this.nonceStr = nonceStr;
        this.appId = appId;
        this.sign = sign;
        this.tradeType = tradeType;
        this.returnMsg = returnMsg;
        this.resultCode = resultCode;
        this.mchId = mchId;
        this.returnCode = returnCode;
        this.prepayId = prepayId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getAppId() {
        return appId;
    }

    public String getSign() {
        return sign;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMchId() {
        return mchId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getPrepayId() {
        return prepayId;
    }
}

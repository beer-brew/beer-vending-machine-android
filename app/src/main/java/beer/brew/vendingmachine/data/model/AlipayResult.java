package beer.brew.vendingmachine.data.model;

import android.text.TextUtils;

import java.util.Map;

public class AlipayResult implements PayResult {

    private String resultStatus;
    private String result;
    private String memo;

    public AlipayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public String getMemo() {
        return memo;
    }

    public String getResult() {
        return result;
    }
}

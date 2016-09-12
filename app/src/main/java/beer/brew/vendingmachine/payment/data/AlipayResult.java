package beer.brew.vendingmachine.payment.data;

public class AlipayResult implements PayResult {

    private String resultStatus;
    private String result;
    private String memo;

    public AlipayResult(String resultStatus, String result, String memo) {
        this.resultStatus = resultStatus;
        this.result = result;
        this.memo = memo;
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

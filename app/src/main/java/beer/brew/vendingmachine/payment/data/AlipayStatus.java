package beer.brew.vendingmachine.payment.data;

public class AlipayStatus implements PayStatus {

    private String resultStatus;
    private String result;
    private String memo;

    public AlipayStatus(String resultStatus, String result, String memo) {
        this.resultStatus = resultStatus;
        this.result = result;
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    public String getMemo() {
        return memo;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String getPayStatus() {
        return resultStatus;
    }
}

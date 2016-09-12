package beer.brew.vendingmachine.payment.data;

public interface PayStatus {

    String PAYMENT_FINISHED = "9000";

    String PAYMENT_UNFINISHED = "6001";

    String getPayStatus();
}

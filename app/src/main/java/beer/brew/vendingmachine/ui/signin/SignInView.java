package beer.brew.vendingmachine.ui.signin;

import beer.brew.vendingmachine.ui.base.MvpView;

public interface SignInView extends MvpView {

    void showSignInSuccess();

    void showSignInFailed();

    void showSignInEmailInvalid();

    void showEmailIsEmpty();

    void showPasswordIsEmpty();

    void showProgress(boolean show);
}

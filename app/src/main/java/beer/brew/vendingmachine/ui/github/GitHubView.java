package beer.brew.vendingmachine.ui.github;

import beer.brew.vendingmachine.data.model.GitHubUser;
import beer.brew.vendingmachine.ui.base.MvpView;

import java.util.List;

public interface GitHubView extends MvpView {

    void showList(List<GitHubUser> userList);

    void showError();
}

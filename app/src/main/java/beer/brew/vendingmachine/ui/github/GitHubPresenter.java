package beer.brew.vendingmachine.ui.github;

import beer.brew.vendingmachine.data.model.GitHubUser;
import beer.brew.vendingmachine.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

public class GitHubPresenter extends BasePresenter<GitHubView> implements GitHubInteractor.Callback {

    private GitHubInteractor mGitHubInteractor;

    @Inject
    public GitHubPresenter(GitHubInteractor gitHubInteractor) {
        this.mGitHubInteractor = gitHubInteractor;
    }

    public void loadUserList(String keyword) {
        mGitHubInteractor.load(keyword, this);
    }

    @Override
    public void onLoadUserListComplete(List<GitHubUser> userList) {
        getMvpView().showList(userList);
    }

    @Override
    public void onLoadUserListFailed() {
        getMvpView().showError();
    }
}

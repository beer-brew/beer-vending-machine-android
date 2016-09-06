package beer.brew.vendingmachine.ui.github;

import beer.brew.vendingmachine.data.model.GitHubUser;
import beer.brew.vendingmachine.data.model.GitHubUserList;
import beer.brew.vendingmachine.data.remote.GitHubService;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class GitHubInteractor {

    private GitHubService mGitHubService;

    public interface Callback {
        void onLoadUserListComplete(List<GitHubUser> userList);

        void onLoadUserListFailed();
    }

    @Inject
    public GitHubInteractor(GitHubService gitHubService) {
        this.mGitHubService = gitHubService;
    }

    public void load(String keyword, final Callback callback) {
        mGitHubService.getGitHubUserList(keyword).subscribeOn(io()).observeOn(mainThread()).subscribe(new Observer<GitHubUserList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onLoadUserListFailed();
            }

            @Override
            public void onNext(GitHubUserList gitHubUserList) {
                callback.onLoadUserListComplete(gitHubUserList.getItems());
            }
        });
    }
}

package beer.brew.vendingmachine.ui.main;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainPresenterTest {

    @Mock MainMvpView view;
    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter();
        presenter.attachView(view);
    }

    @After
    public void tearDown() {
        presenter.detachView();
    }
}

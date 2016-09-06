package beer.brew.vendingmachine.ui.main;

import java.util.List;

import beer.brew.vendingmachine.data.model.Ribot;
import beer.brew.vendingmachine.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}

package com.route.presenters.views;

public interface AbstractStatefulView {

    void showProgress();

    void showError(Throwable t);

    void hideProgress();

    void hideError();

}

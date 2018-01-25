package com.route.view.activities.base;

import android.os.Bundle;

import com.route.presenters.base.BasePresenter;

public abstract class BasePresenterActivity extends BaseActivity {

    private BasePresenter presenter;
    private boolean presenterInitialized;

    protected void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onStart() {
        super.onStart();
        if(!presenterInitialized && presenter != null) {
            presenterInitialized = true;
            presenter.onStart(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null) presenter.onResume();
    }

    @Override
    protected void onPause() {
        if(presenter != null) presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(presenter != null) presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(presenter != null) presenter.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        if(presenter != null) presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(presenter != null) presenter.onDestroy();
        super.onDestroy();
    }
}

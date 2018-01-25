package com.route.presenters.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;

public abstract class BasePresenter<T> {

    protected T view;

    @CallSuper
    public void onStart(T view) {
        this.view = view;
    }

    public void onResume() {}

    public void onPause() {}

    public void onSaveInstanceState(Bundle state) {}

    public void onRestoreInstanceState(Bundle state) {}

    public void onStop() {}

    public void onDestroy() {}

}

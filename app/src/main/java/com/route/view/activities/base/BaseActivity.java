package com.route.view.activities.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.route.presenters.views.AbstractStatefulView;

public abstract class BaseActivity extends AppCompatActivity implements AbstractStatefulView {

    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
    }

    protected abstract int getLayout();

    @Override
    public void showProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Get path...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    @Override
    public void showError(Throwable t) {
        hideProgress();
    }

    @Override
    public void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public void hideError() {
        hideProgress();
    }
}

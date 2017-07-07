package com.eflashloan.wct.base;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Preconditions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eflashloan.wct.BuildConfig;
import com.eflashloan.wct.R;

import butterknife.ButterKnife;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:31
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment implements BaseContract.View {
    /**
     * 加载数据的对话框显示次数的计数
     */
    private volatile int loadDialogCount = 0;
    /**
     * 加载数据显示的对话框
     */
    private Dialog loadDialog = null;

    private View contentView = null;
    /**
     * 与本View关联的Presenter
     */
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, contentView);
            presenter = Preconditions.checkNotNull(createPresenter());
            start();
            presenter.start();
        }
        return contentView;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @NonNull
    protected abstract P createPresenter();

    protected void start() {}

    @Override
    public void showLoadDialog() {
        if (loadDialog == null) {
            loadDialog = new Dialog(getActivity(), R.style.LoadDialog);
            View loadDialogContentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_load_dialog, null);
            ProgressBar progressBar = (ProgressBar) loadDialogContentView.findViewById(R.id.progress_load_dialog);
            int progressColor = ContextCompat.getColor(getActivity(), R.color.colorPrimary);
            progressBar.getIndeterminateDrawable().setColorFilter(progressColor, PorterDuff.Mode.SRC_ATOP);
            loadDialog.setCanceledOnTouchOutside(BuildConfig.DEBUG);
            loadDialog.setCancelable(true);
            loadDialog.setContentView(loadDialogContentView);
            loadDialogCount = 0;
        }
        if (!loadDialog.isShowing()) {
            loadDialog.show();
        }
        loadDialogCount++;
    }

    @Override
    public void cancelLoadDialog() {
        cancelLoadDialog(false);
    }

    protected void cancelLoadDialog(boolean force) {
        if (force) {
            loadDialogCount = 0;
        }
        loadDialogCount--;
        if (loadDialogCount <= 0 && loadDialog != null) {
            loadDialog.cancel();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int msgId) {
        Toast.makeText(getActivity(), msgId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnack(String msg, int actionResId, final Runnable action) {
        Snackbar.make(contentView, msg, Snackbar.LENGTH_SHORT).setAction(actionResId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.run();
            }
        }).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroy() {
        cancelLoadDialog(true);
        presenter.destroy();
        super.onDestroy();
    }
}

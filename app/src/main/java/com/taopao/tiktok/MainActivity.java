package com.taopao.tiktok;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.taopao.commonsdk.AppSettingUtils;
import com.taopao.commonsdk.RouterHub;
import com.taopao.tiktok.LoadingDialog;
import com.taopao.tiktok.R;
import com.taopao.tiktok.test.MainPresenter;
import com.taopao.tiktok.ui.base.BaseActivity;
import com.taopao.tiktok.ui.di.component.AppComponent;
import com.taopao.tiktok.ui.dialog.CommentBottomSheetDialogFragment;

@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity<MainPresenter> {
    private LoadingDialog mLoadingDialog;

    public void comment(View view) {
        Log.d("MainActivity", "comment: sadas ");
        CommentBottomSheetDialogFragment commentBottomSheetDialogFragment = new CommentBottomSheetDialogFragment();
        commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
//        startActivity(new Intent(this, ListActivity.class));
//        throw new UnsupportedOperationException("啦啦啦啦啦啦啦阿拉啦啦啦啦啦啦啦啦啦");
    }

    public void loading(View view) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public int layoutResID() {
        AppSettingUtils.setFirstStart(false);
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }
}

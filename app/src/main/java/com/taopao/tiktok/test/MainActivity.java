package com.taopao.tiktok.test;

import android.support.annotation.NonNull;
import android.view.View;

import com.taopao.tiktok.R;
import com.taopao.tiktok.ui.base.BaseActivity;
import com.taopao.tiktok.ui.dialog.CommentBottomSheetDialogFragment;
import com.taopao.tiktok.ui.dl.component.AppComponent;

public class MainActivity extends BaseActivity<MainPresenter> {
    public void comment(View view) {
        CommentBottomSheetDialogFragment commentBottomSheetDialogFragment = new CommentBottomSheetDialogFragment();
        commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }
}

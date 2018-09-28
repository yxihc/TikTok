package com.taopao.tiktok.test;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.taopao.tiktok.R;
import com.taopao.tiktok.tests.ListActivity;
import com.taopao.tiktok.ui.base.BaseActivity;
import com.taopao.tiktok.ui.di.component.AppComponent;

public class MainActivity extends BaseActivity<MainPresenter> {


    public void comment(View view) {
        Log.d("MainActivity", "comment: sadas ");
//        CommentBottomSheetDialogFragment commentBottomSheetDialogFragment = new CommentBottomSheetDialogFragment();
//        commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
        startActivity(new Intent(this, ListActivity.class));
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }
}

package com.taopao.tiktok.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.taopao.tiktok.R;
import com.taopao.tiktok.ui.dialog.CommentBottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void comment(View view) {
        CommentBottomSheetDialogFragment commentBottomSheetDialogFragment = new CommentBottomSheetDialogFragment();
        commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
    }
}

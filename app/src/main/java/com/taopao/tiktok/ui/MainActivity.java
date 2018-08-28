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
        Toast.makeText(MainActivity.this, "啥答案所", Toast.LENGTH_SHORT).show();
        CommentBottomSheetDialogFragment commentBottomSheetDialogFragment = new CommentBottomSheetDialogFragment();
        commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
    }
}

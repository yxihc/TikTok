package com.taopao.tiktok.test;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * @Author：淘跑
 * @Date: 2018/11/14 16:47
 * @Use：
 */
public class TesttwoD extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TestDialog testDialog = new TestDialog(getContext(), getTheme());
//        testDialog.setCanceledOnTouchOutside(true);
        return testDialog;
    }

}

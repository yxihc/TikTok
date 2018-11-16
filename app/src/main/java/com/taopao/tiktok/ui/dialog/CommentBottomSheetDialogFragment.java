package com.taopao.tiktok.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.taopao.tiktok.R;
import com.taopao.tiktok.test.InputDialog;
import com.taopao.tiktok.test.TesttwoD;
import com.taopao.tiktok.test.TransparentActivity;

/**
 * @Author：淘跑
 * @Date: 2018/8/28  17:04
 * @Use：
 */
public class CommentBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给dialog设置主题为透明背景 不然会有默认的白色背景
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里将view的高度设置为精确高度，即可屏蔽向上滑动不占全屏的手势。
        //如果不设置高度的话 会默认向上滑动时dialog覆盖全屏
        View view = inflater.inflate(R.layout.dialog_comment, container, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getScreenHeight(getActivity()) / 2));
        return view;
    }

    private void initView() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
//        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getScreenHeight(getActivity()) / 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(100));

        view.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog inputDialog = new InputDialog(getActivity());
                Window window = inputDialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                window.setSoftInputMode(params.SOFT_INPUT_STATE_VISIBLE);
                inputDialog.show();
            }
        });
        initView();
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            final TextView text;

            MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.recycle_item_list_dialog, parent, false));
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }

    }

    /**
     * 如果想要点击外部消失的话 重写此方法
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //设置点击外部可消失
        dialog.setCanceledOnTouchOutside(true);
        //设置使软键盘弹出的时候dialog不会被顶起
        Window win = dialog.getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        win.setSoftInputMode(params.SOFT_INPUT_ADJUST_NOTHING);
        return dialog;
    }

    /**
     * 得到屏幕的高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}
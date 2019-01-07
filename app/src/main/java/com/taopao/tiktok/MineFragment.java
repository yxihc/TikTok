package com.taopao.tiktok;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineFragment extends Fragment {
    @BindView(R.id.iv)
    ImageView mIv;
    Unbinder unbinder;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIv.animate().alpha(0)
                        .setDuration(500)
                        .start();
                mHandler.sendEmptyMessageDelayed(0, 500);
            }
        });
    }

    boolean isStart;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isStart = !isStart;
            if (isStart) {
                mIv.setImageResource(R.drawable.start_red);
            } else {
                mIv.setImageResource(R.drawable.start_white);
            }
            mIv.animate().alpha(1)
                    .setDuration(500)
                    .start();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

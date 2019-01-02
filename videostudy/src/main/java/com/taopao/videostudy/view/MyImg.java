package com.taopao.videostudy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.taopao.videostudy.R;


/**
 * @Author：淘跑
 * @Date: 2019/1/2 10:48
 * @Use：
 */
public class MyImg extends View {
    public MyImg(Context context) {
        super(context);
        init(context);
    }

    public MyImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    Paint paint = new Paint();
    Bitmap bitmap;

    private void init(Context context) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}

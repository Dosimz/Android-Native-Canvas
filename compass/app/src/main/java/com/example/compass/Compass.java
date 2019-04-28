package com.example.compass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class Compass extends View {
    public static final int DP_D = 300;
    private float r;
    private Paint paint;
    private Path needle;
    float rotateDegree;

    public Compass(Context context, AttributeSet attrs) {
        super(context, attrs);
        r = dp2Px(DP_D / 2);
        // 初始画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        // 配置画笔的大小和对齐方式
        paint.setTextSize(dp2Px(18));
        paint.setTextAlign(Paint.Align.CENTER);
        // 初始化指针的绘制路径
        needle = new Path();
        needle.moveTo(-r / 10, 0);
        needle.lineTo(0, 7*r/10);
        needle.lineTo(r/10,0);
        needle.lineTo(0,-7*r/10);
        needle.lineTo(-r/10,0);
        needle.lineTo(r/10, 0);
    }
    public float dp2Px(final float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }
    public void rotate(float degree) {
        rotateDegree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.translate(r, r); //参考点移到中心
        canvas.drawCircle(0,0, (float) (0.9*r), paint);
        //准备旋转。。。       旋转时保持指针方向固定，可通过反向旋转指定角度实现
        canvas.save();
        canvas.rotate(-rotateDegree);
        canvas.drawPath(needle, paint);
        canvas.restore();
        // 绘制N
        canvas.save();
        canvas.translate(0, -4*r/5+24);
        canvas.drawText("N", 0,0,paint);
        canvas.restore();
        // 绘制其他 方向
        canvas.save();
        canvas.translate(0, 4*r/5);
        canvas.drawText("S", 0,0,paint);
        canvas.restore();
    }
}

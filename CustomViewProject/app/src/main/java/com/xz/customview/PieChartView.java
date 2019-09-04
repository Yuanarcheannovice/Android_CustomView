package com.xz.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * @author xz
 */
public class PieChartView extends View {

    private ArrayList<PieChartData> mDatas = null;
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;

    RectF mRect = new RectF(0, 0, 0, 0);

    /**
     * new  view 的时候使用
     *
     * @param context context
     */
    public PieChartView(Context context) {
        this(context, null);
    }

    /**
     * xml 中使用
     *
     * @param context context
     * @param attrs   attrs
     */
    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //设置画笔填充
        mPaint.setStyle(Paint.Style.FILL);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDatas) {
            return;
        }
        float r = Math.min(mWidth, mHeight) / 2f;
        mRect.set(-r, -r, r, r);
        canvas.translate(mWidth / 2, mHeight / 2);
        // 饼状图初始绘制角度
        float currentStartAngle = 0;
        for (PieChartData data : mDatas) {
            mPaint.setColor(data.getColor());
            canvas.drawArc(mRect, currentStartAngle, data.getAngle(), true, mPaint);
            currentStartAngle = currentStartAngle + data.getAngle();
        }
    }

    private void initData() {
        //计算数值和
        float sumValue = 0;

        for (PieChartData data : mDatas) {
            sumValue += data.getNumber();
        }

        for (PieChartData data : mDatas) {
            // 百分比
            float percentage = data.getNumber() / sumValue;
            // 对应的角度
            float angle = percentage * 360;
            // 记录百分比
            data.setPercentage(percentage);
            // 记录角度大小
            data.setAngle(angle);
        }

    }

    /**
     * 设置数据显示
     *
     * @param datas datas
     */
    public void setDatas(ArrayList<PieChartData> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas = datas;
            initData();
            invalidate();
        }
    }


    /**
     * 饼状图数据
     */
    public static class PieChartData {
        /**
         * 名字
         */
        private String name;
        /**
         * 数值
         */
        private float number;
        /**
         * 百分比
         */
        private float percentage;
        /**
         * 颜色
         */
        @ColorInt
        private int color;
        /**
         * 角度
         */
        private float angle = 0;


        public PieChartData(String name, float number, int color) {
            this.name = name;
            this.number = number;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getNumber() {
            return number;
        }

        public void setNumber(float number) {
            this.number = number;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getAngle() {
            return angle;
        }

        public void setAngle(float angle) {
            this.angle = angle;
        }
    }
}

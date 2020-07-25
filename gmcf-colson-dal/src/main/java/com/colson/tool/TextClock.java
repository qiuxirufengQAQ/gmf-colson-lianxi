//package com.colson.tool;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.view.animation.LinearInterpolator;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//
//public class TextClock {
//    private Paint mPaint;
//    private Paint mHelperPaint;
//
//    float mWidth;                 //控件宽
//    float mHeight;                //控件高
//    float mHourR;                 //小时的半径
//    float mMinuteR;               //分钟的半径
//    float mSecondR;               //秒的半径
//
//    float mHourDeg = 0f;               //小时的角度
//    float mMinuteDeg = 0f;             //分钟的角度
//    float mSecondDeg = 0f;             //秒的角度
//
//    private List<String> list_china;    //中文显示日期
//
//    private ValueAnimator mAnimator;
//
//    public TextClock(Context context) {
//        this(context,null);
//    }
//
//    public TextClock(Context context, AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public TextClock(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.FILL);
//
//        mHelperPaint = new Paint();
//        mHelperPaint.setAntiAlias(true);
//        mHelperPaint.setColor(Color.RED);
//        mHelperPaint.setStyle(Paint.Style.FILL);
//
//        list_china = new ArrayList<>();
//        list_china.add("日");
//        list_china.add("一");
//        list_china.add("二");
//        list_china.add("三");
//        list_china.add("四");
//        list_china.add("五");
//        list_china.add("六");
//        list_china.add("七");
//        list_china.add("八");
//        list_china.add("九");
//        list_china.add("十");
//
//        //处理动画，声明全局的处理器
//        mAnimator = ValueAnimator.ofFloat(6f, 0f);//由6降到1
//        mAnimator.setDuration(150);
//        mAnimator.setInterpolator(new LinearInterpolator()); //插值器设为线性
//
//        doInvalidate();
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int width = measureWidth(widthMeasureSpec);
//        int height = measureHeight(heightMeasureSpec);
//        setMeasuredDimension(width, height);
//
//        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
//        mHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
//    }
//
//    private int measureHeight(int measureSpec)
//    {
//        int mode = MeasureSpec.getMode(measureSpec);
//        int val = MeasureSpec.getSize(measureSpec);
//        int result = 0;
//        switch (mode)
//        {
//            case MeasureSpec.EXACTLY:
//                result = val;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                break;
//        }
//        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
//        return result + getPaddingTop() + getPaddingBottom();
//    }
//
//    private int measureWidth(int measureSpec)
//    {
//        int mode = MeasureSpec.getMode(measureSpec);
//        int val = MeasureSpec.getSize(measureSpec);
//        int result = 0;
//        switch (mode)
//        {
//            case MeasureSpec.EXACTLY:
//                result = val;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                break;
//        }
//        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
//        return result + getPaddingLeft() + getPaddingRight();
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//
//        //后文会涉及到
//        //统一用View宽度*系数来处理大小，这样可以联动适配样式
//        mHourR = mWidth * 0.143f;
//        mMinuteR = mWidth * 0.35f;
//        mSecondR = mWidth * 0.45f;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        if (canvas == null)
//            return;
//        canvas.drawColor(Color.BLACK);//填充背景
//        canvas.save();
//        canvas.translate(mWidth / 2, mHeight / 2);//原点移动到中心
//
//        //绘制各元件，后文会涉及到
//        drawCenterInfo(canvas);
//        drawHour(canvas, mHourDeg);
//        drawMinute(canvas, mMinuteDeg);
//        drawSecond(canvas, mSecondDeg);
//
//        //从原点处向右画一条辅助线，之后要处理文字与x轴的对齐问题，稍后再说
//        canvas.drawLine(0f, 0f, mWidth, 0f, mHelperPaint);
//
//        canvas.restore();
//    }
//
//    //绘制圆中的信息
//    private void drawCenterInfo(Canvas canvas)
//    {
//        Calendar mCalendar = Calendar.getInstance();
//        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
//        int minute = mCalendar.get(Calendar.MINUTE);
//        StringBuilder strMiute = new StringBuilder();
//        strMiute.append(minute);
//        if(minute < 10)
//        {
//            strMiute.insert(0,"0");
//        }
//
//        mPaint.setTextSize(mHourR * 0.4f); //字体大小根据「时圈」半径来计算
//        mPaint.setColor(Color.WHITE);
//        mPaint.setAlpha(255);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(hour + ":" + minute, 0f, getBottomY(), mPaint);
//
//        //绘制月份、星期
//        int month = mCalendar.get(Calendar.MONTH) + 1;
//        StringBuilder monthstr = new StringBuilder();
//        monthstr.append(month);
//
//        if(month < 10)
//        {
//            monthstr.insert(0,"0");
//        }
//        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
//        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;//私有的扩展方法，将Int数字转换为 一、十一、二十等，后文绘制三个文字圈都会用该方法
//        String strDayOfWeek = IntToChinaText(dayOfWeek);
//
//        mPaint.setTextSize(mHourR * 0.16f); //字体大小根据「时圈」半径来计算
//        mPaint.setAlpha(255);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(monthstr + "." + day +" 星期" + strDayOfWeek, 0f, getCenteredY(), mPaint);
//    }
//
//    //绘制圆中的小时信息
//    private void drawHour(Canvas canvas, float degrees)
//    {
//        mPaint.setTextSize(mHourR * 0.16f);
//
//        //处理整体旋转
//        canvas.save();
//        canvas.rotate(degrees);
//
//        for (int i=0;i<12;i++) {
//            canvas.save();
//
//            //从x轴开始旋转，每30°绘制一下「几点」，12次就画完了「时圈」
//            float iDeg = 360 / 12f * i;
//            canvas.rotate(iDeg);
//
//            //这里处理当前时间点的透明度，因为degrees控制整体逆时针旋转
//            //iDeg控制绘制时顺时针，所以两者和为0时，刚好在x正半轴上，也就是起始绘制位置。
//            int mAlpha = 0;
//            if((iDeg + degrees) == 0f)
//            {
//                mAlpha = 255;
//            }else{
//                mAlpha = (int)(0.6f * 255);
//            }
//
//            mPaint.setAlpha(mAlpha);
//            mPaint.setTextAlign(Paint.Align.LEFT);
//
//            canvas.drawText(IntToChinaText((i + 1)) + "点", mHourR, getCenteredY(), mPaint);
//            canvas.restore();
//        }
//
//        canvas.restore();
//    }
//
//    /**
//     * 绘制圆中的分钟信息
//     * @param canvas
//     * @param degrees
//     */
//    private void drawMinute(Canvas canvas, float degrees)
//    {
//        mPaint.setTextSize(mHourR * 0.16f);
//
//        //处理整体旋转
//        canvas.save();
//        canvas.rotate(degrees);
//
//        for (int i=0;i<60;i++) {
//            canvas.save();
//
//            float iDeg = 360 / 60f * i;
//            canvas.rotate(iDeg);
//
//            int mAlpha = 0;
//            if((iDeg + degrees) == 0f)
//            {
//                mAlpha = 255;
//            }else{
//                mAlpha = (int)(0.6f * 255);
//            }
//
//            mPaint.setAlpha(mAlpha);
//            mPaint.setTextAlign(Paint.Align.RIGHT);
//
//            if (i < 59) {
//                canvas.drawText(IntToChinaText(i+1) + "分", mMinuteR, getCenteredY(), mPaint);
//            }
//            canvas.restore();
//        }
//
//        canvas.restore();
//    }
//
//
//    private void drawSecond(Canvas canvas, float degrees)
//    {
//        mPaint.setTextSize(mHourR * 0.16f);
//
//        //处理整体旋转
//        canvas.save();
//        canvas.rotate(degrees);
//
//        for (int i=0;i<60;i++) {
//            canvas.save();
//
//            float iDeg = 360 / 60f * i;
//            canvas.rotate(iDeg);
//
//            int mAlpha = 0;
//            if((iDeg + degrees) == 0f)
//            {
//                mAlpha = 255;
//            }else{
//                mAlpha = (int)(0.6f * 255);
//            }
//
//            mPaint.setAlpha(mAlpha);
//            mPaint.setTextAlign(Paint.Align.RIGHT);
//
//            if (i < 59) {
//                canvas.drawText(IntToChinaText(i+1) +"秒", mSecondR, getCenteredY(), mPaint);
//            }
//            canvas.restore();
//        }
//
//        canvas.restore();
//    }
//
//
//    public void doInvalidate()
//    {
//        Calendar mCalendar = Calendar.getInstance();
//        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
//        final int minute = mCalendar.get(Calendar.MINUTE);
//        StringBuilder strMiute = new StringBuilder();
//        strMiute.append(minute);
//        if(minute < 10)
//        {
//            strMiute.insert(0,"0");
//        }
//        final int second = mCalendar.get(Calendar.SECOND);
//
//        mHourDeg = -360 / 12f * (hour - 1);
//        mMinuteDeg = -360 / 60f * (minute - 1);
//        mSecondDeg = -360 / 60f * (second - 1);
//
//        //记录当前角度，然后让秒圈线性的旋转6°
//        final float hd = mHourDeg;
//        final float md = mMinuteDeg;
//        final float sd = mSecondDeg;
//
//
//
//        //处理动画
//        mAnimator.removeAllUpdateListeners();//需要移除先前的监听
//        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float av = (Float)animation.getAnimatedValue();
//
//                if (minute == 0 && second == 0) {
//                    mHourDeg = hd + av * 5;//时圈旋转角度是分秒的5倍，线性的旋转30°
//                }
//
//                if (second == 0) {
//                    mMinuteDeg = md + av;//线性的旋转6°
//                }
//
//                mSecondDeg = sd + av;//线性的旋转6°
//
////
//                invalidate();
//            }
//        });
//
//
//        mAnimator.start();
//    }
//
//    /**
//     * 取Y轴的值，让其底部对齐
//     * @return
//     */
//    private float getBottomY()
//    {
//        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//
//        return -fontMetrics.bottom;
//    }
//
//    /**
//     * 中间对齐
//     * @return
//     */
//    private float getCenteredY()
//    {
//        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//        return mPaint.getFontSpacing()/2 - fontMetrics.bottom;
//    }
//
//
//    /**
//     * 将数字转化为中文
//     * @param number
//     * @return
//     */
//    private String IntToChinaText(int number)
//    {
//        StringBuilder str = new StringBuilder();
//        char[] sNumber = String.valueOf(number).toCharArray();
//        if(sNumber.length > 1)
//        {
//            int one = Integer.valueOf(sNumber[0] - '0');
//            if(one != 1)
//            {
//                str.append(list_china.get(one));
//            }
//            str.append("十");
//            int num = Integer.valueOf(sNumber[1] - '0');
//            if(num > 0) {
//
//                str.append(list_china.get(num));
//            }
//        }else{
//            str.append(list_china.get(number));
//        }
//
//        return str.toString();
//    }
//}

package com.example.fivestar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @Description: Created by Administrator on 2017/11/1.
 */

public class FiveStarView extends View {
    private double width;
    private double height;
    private double size;
    private int curProgress;
    public FiveStarView(Context context) {
        super(context);
    }

    public FiveStarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FiveStarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //获取大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        //根据宽高中的最小值对控件进行宽高设定
        size =  Math.min(width,height);
        setMeasuredDimension((int)size,(int)size);
        Log.e("width",width+";"+height);
    }
    //进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paintInner = new Paint();
        int layerId = canvas.saveLayer(0, 0, (float) size, (float) size, null, Canvas.ALL_SAVE_FLAG);
        paintInner.setColor(Color.YELLOW);
        paintInner.setStyle(Paint.Style.FILL);
        paintInner.setAntiAlias(true);
        drawPath(canvas,paintInner,size);
        paintInner.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        paintInner.setColor(Color.WHITE);
        paintInner.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,(float)size,(float)size*(100-curProgress)/100,paintInner);
        paintInner.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
    //绘制轮廓
    private void drawPath(Canvas canvas,Paint paint ,double size){
        Path path = new Path();
        double r = size/2;//计算五角星所在圆的半径
        //求出圆心坐标
        double r_x,r_y;
        r_x = size/2;//圆心x坐标
        r_y = size/2;//圆心y坐标
        //求出五角星顶点坐标
        Log.e("string",Math.sin(2*Math.PI*36/360)+"");
        double r_x1 = r_x;
        double r_y1 = r_y + r;
        double r_x2 = r_x1 + 0.58*r;
        double r_y2 = r_y + 0.8*r;
        double r_x3 = r_x1 + 0.95*r;
        double r_y3 = r_y + 0.31*r;
        double r_x4 = r_x3;
        double r_y4 = r_y - 0.31*r;
        double r_x5 = r_x2;
        double r_y5 = r_y - 0.8*r;
        double r_x6 = r_x1;
        double r_y6 = r_y - r;
        double r_x7 = r_x1 - 0.58*r;
        double r_y7 = r_y5;
        double r_x8 = r_x1 - 0.95*r;
        double r_y8 = r_y4;
        double r_x9 = r_x8;
        double r_y9 = r_y3;
        double r_x10 = r_x7;
        double r_y10 = r_y2;

        path.moveTo((float) r_x6,(float) r_y6);
        path.lineTo((float) (r_x5-(r_x5-r)/2),(float) (r_y5-(r_y5-r)/2));
        path.lineTo((float) r_x4,(float) r_y4);
        path.lineTo((float) (r_x3-(r_x3-r)/2),(float) (r_y3-(r_y3-r)/2));
        path.lineTo((float) r_x2,(float) r_y2);
        path.lineTo((float) r_x1,(float) (r_y1-(r_y1-r)/2));
        path.lineTo((float) r_x10,(float) r_y10);
        path.lineTo((float) (r_x9-(r_x9-r)/2),(float) (r_y9-(r_y9-r)/2));
        path.lineTo((float) r_x8,(float) r_y8);
        path.lineTo((float) (r_x7-(r_x7-r)/2),(float) (r_y7-(r_y7-r)/2));
        path.close();
        canvas.drawPath(path,paint);
    }

    /**
     * 设置当前进度
     * @param curProgress 当前进度
     */
    public void setCurProgress(int curProgress){
        this.curProgress = curProgress;
        invalidate();
    }
}

package com.xsyu.inputreportproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ReportItemView extends GridView {
    public ReportItemView(Context context) {
        super(context);
    }

    public ReportItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReportItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //复写计算尺寸方法


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新计算大小
        int heightspec= MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightspec);
    }
}

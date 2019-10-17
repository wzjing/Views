package com.wzjing.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import kotlin.math.min

class XScrollView(context: Context, attrs: AttributeSet? = null) : ScrollView(context, attrs) {

    var maxHeight: Int = -1

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.XScrollView)
        maxHeight = typeArray.getDimensionPixelSize(R.styleable.XScrollView_maxHeight, -1)
        typeArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val actualHeight =
            View.resolveSizeAndState(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec, 0)
        val actualWidth =
            View.resolveSizeAndState(MeasureSpec.getSize(widthMeasureSpec), widthMeasureSpec, 0)
        setMeasuredDimension(
            actualWidth,
            if (maxHeight > 0) min(maxHeight, actualHeight) else actualHeight
        )
    }
}
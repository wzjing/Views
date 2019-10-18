package com.wzjing.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.core.view.children
import kotlin.math.max
import kotlin.math.min

class XScrollView(context: Context, attrs: AttributeSet? = null) : ScrollView(context, attrs) {

    private val TAG = "XScrollView"

    var maxHeight: Int = -1

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.XScrollView)
        maxHeight = typeArray.getDimensionPixelSize(R.styleable.XScrollView_maxHeight, -1)
        typeArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val computeHeight =
            View.resolveSizeAndState(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec, 0)

        val child = getChildAt(0)
        child.measure(widthMeasureSpec, heightMeasureSpec)
        val childHeight = child.measuredHeight

        val realHeight = max(
            minimumHeight,
            if (maxHeight > 0) min(maxHeight, childHeight) else computeHeight
        )
//        setMeasuredDimension(computeWidth, realHeight)
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(realHeight, MeasureSpec.EXACTLY))
        Log.d(
            TAG,
            "computeHeight: $computeHeight/${MeasureSpec.getMode(computeHeight)}, maxHeight: $maxHeight, minHeight: $minimumHeight childHeight: $childHeight"
        )
    }

//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        super.onLayout(changed, l, t, r, b)
//        for (child in children) {
//            val childParams = child.layoutParams as MarginLayoutParams
//            child.layout(childParams.topMargin, childParams.marginStart, measuredWidth - (childParams.marginStart + childParams.marginEnd), measuredHeight - (childParams.topMargin + childParams.bottomMargin))
//        }
//    }
}
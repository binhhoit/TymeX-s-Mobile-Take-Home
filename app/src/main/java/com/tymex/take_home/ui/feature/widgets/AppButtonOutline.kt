package com.tymex.take_home.ui.feature.widgets

import android.app.ActionBar
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import com.base.utils.RippleUtil
import com.tymex.takehome.R

class AppButtonOutline: AppCompatButton {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val normalColor = resources.getColor(com.base.R.color.colorWhite, null)
        val rippleColor = resources.getColor(R.color.colorPrimary, null)
        val cornerRadius = resources.getDimension(com.base.R.dimen._4sdp)
        val strokeWidth = resources.getDimension(com.base.R.dimen._1sdp)

        setTextAppearance(R.style.AppButtonOutlineTextAppearance)

        gravity = Gravity.CENTER

        background =
            RippleUtil.getRippleStrokeDrawable(normalColor, rippleColor, cornerRadius,
                strokeWidth.toInt(), rippleColor)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(layoutParams.width == ActionBar.LayoutParams.WRAP_CONTENT) {
            minWidth = resources.getDimension(com.base.R.dimen._327sdp).toInt()
        }
        if(layoutParams.height == ActionBar.LayoutParams.WRAP_CONTENT) {
            minHeight = resources.getDimension(com.base.R.dimen._56sdp).toInt()
        }
    }
}
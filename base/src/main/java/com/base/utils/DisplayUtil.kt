package com.base.utils

import android.content.Context
import android.util.TypedValue

class DisplayUtil {

    companion object {
        private var deviceWidth = 0
        private var deviceHeight = 0

        fun getDeviceWidth(context: Context): Int {
            if (deviceWidth == 0)
                deviceWidth = (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
            return deviceWidth
        }

        fun getDeviceHeight(context: Context): Int {
            if (deviceHeight == 0)
                deviceHeight = context.resources.displayMetrics.heightPixels
            return deviceHeight
        }

        fun convertDpToPx(context: Context?, dp: Float): Float {
            if (context == null) return 0f
            val r = context.resources
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, r.displayMetrics)
        }
    }
}
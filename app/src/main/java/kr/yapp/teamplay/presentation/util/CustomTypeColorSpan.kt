package kr.yapp.teamplay.presentation.util

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */

class CustomTypeColorSpan(family: String, fontColor: Int, type: Typeface?) : TypefaceSpan(family) {

    var typeFace = type
    var color = fontColor

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, typeFace)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, typeFace)
    }

    private fun applyCustomTypeFace(paint: Paint, typeface: Typeface?) {
        typeface?.let { tf ->
            val old = paint.typeface
            val oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()

            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }

            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }

            paint.color = color
            paint.typeface = tf
        }
    }
}

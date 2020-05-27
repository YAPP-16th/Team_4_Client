package kr.yapp.teamplay.presentation.util.extension

import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import kr.yapp.teamplay.presentation.util.CustomTypeColorSpan

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */

data class SpannableComponent(
    val changeData: Array<String>,
    @FontRes val font: Int,
    @ColorRes val color: Int
)

@BindingAdapter("setSpannableBold")
fun setSpannableBold(
    textView: TextView,
    component: SpannableComponent?
) {
    component?.changeData?.forEach {
        ResourcesCompat.getFont(textView.context, component.font)?.let { typeface ->
            SpannableString(textView.text).apply {
                if (!textView.text.toString().contains(it)) {
                    return@let
                }

                val matchStart = textView.text.toString().indexOf(it)
                val matchEnd = matchStart + it.length
                setSpan(
                    CustomTypeColorSpan("", ContextCompat.getColor(textView.context, component.color), typeface),
                    matchStart,
                    matchEnd,
                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }.let {
                textView.text = it
            }
        }
    }
}

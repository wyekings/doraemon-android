package com.wyekings.uikit.edittext

import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import java.util.regex.Pattern

class FloatInputFilter(
    private val pointerLength: Int = 2,
    private val maxValue: Int = Int.MAX_VALUE,
) : InputFilter {

    private val pattern: Pattern = Pattern.compile("([0-9]|\\.)*")
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        val sourceText = source.toString()
        val destText = dest.toString()
        if (TextUtils.isEmpty(sourceText)) {
            return ""
        }
        val matcher = pattern.matcher(source)
        if (destText.contains(POINTER)) {
            if (!matcher.matches()) {
                return ""
            } else {
                if (POINTER == source.toString()) {
                    return ""
                }
            }
            val index = destText.indexOf(POINTER)
            val length = dend - index
            if (length > pointerLength) {
                return dest.subSequence(dstart, dend)
            }
        } else {
            if (!matcher.matches()) {
                return ""
            } else {
                if (POINTER == source.toString() && TextUtils.isEmpty(destText)) {
                    return ""
                } else if (POINTER != source.toString() && ZERO == destText) {
                    return ""
                }
            }
        }
        val sumText = (destText + sourceText).toDouble()
        return if (sumText > maxValue) {
            dest.subSequence(dstart, dend)
        } else dest.subSequence(dstart, dend).toString() + sourceText
    }

    companion object {
        private const val POINTER = "."
        private const val ZERO = "0"
    }
}

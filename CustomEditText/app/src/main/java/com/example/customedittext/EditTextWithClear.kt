package com.example.customedittext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class EditTextWithClear @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {

    private var iconDrawable: Drawable? = null// ContextCompat.getDrawable(context, R.drawable.ic_clear)

        init {
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.EditTextWithClear,
                0,
                0,
            ).apply {
                try {
                    val iconId = getResourceId(R.styleable.EditTextWithClear_clearIcon,0)
                    if (iconId != 0) {
                        iconDrawable = ContextCompat.getDrawable(context,R.drawable.ic_clear)
                    }
                } finally {
                    recycle()
                }
            }

        }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        toggleClearIcon()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {e->
            iconDrawable?.let {
                if (
                    e.action == MotionEvent.ACTION_UP
                    && e.x > width - it.intrinsicWidth - 20
                    && e.x < width + 20
                    && e.y > height / 2 - it.intrinsicHeight / 2 - 20
                    && e.y < height / 2 + it.intrinsicHeight / 2 + 20
                ) {
                    text?.clear()
                }
            }

        }
        performClick()
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        toggleClearIcon()
    }

    private fun toggleClearIcon() {
        val icon = if (isFocused && text?.isNotEmpty() == true) iconDrawable else null
        setCompoundDrawablesRelativeWithIntrinsicBounds(null , null, icon, null)
    }


}
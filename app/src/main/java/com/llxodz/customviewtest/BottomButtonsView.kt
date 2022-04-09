package com.llxodz.customviewtest

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.llxodz.customviewtest.databinding.PartButtonsBinding

class BottomButtonsView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    desStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, desStyleRes) {

    private val binding: PartButtonsBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, desStyleRes)
    }

    private fun initializeAttributes(
        attrs: AttributeSet?,
        defStyleAttr: Int,
        desStyleRes: Int
    ) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            desStyleRes
        )
        with(binding) {
            val positiveButtonText =
                typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
            positiveButton.text = positiveButtonText ?: "Ok"

            val negativeButtonText =
                typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
            negativeButton.text = negativeButtonText ?: "Cancel"

            val positiveButtonBgColor =
                typedArray.getColor(
                    R.styleable.BottomButtonsView_bottomPositiveBackgroundColor,
                    Color.BLACK
                )
            positiveButton.backgroundTintList = ColorStateList.valueOf(positiveButtonBgColor)

            val negativeButtonBgColor =
                typedArray.getColor(
                    R.styleable.BottomButtonsView_bottomNegativeBackgroundColor,
                    Color.WHITE
                )
            negativeButton.backgroundTintList = ColorStateList.valueOf(negativeButtonBgColor)

            val isProgressMode =
                typedArray.getBoolean(R.styleable.BottomButtonsView_bottomProgressMode, false)
            if (isProgressMode) {
                positiveButton.visibility = View.INVISIBLE
                negativeButton.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            } else {
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        }

        typedArray.recycle()
    }
}

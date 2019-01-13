package com.example.joaogolias.pocunittests.components.calculationCellView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.joaogolias.pocunittests.R
import com.example.joaogolias.pocunittests.components.squareInput.SquareInputView
import kotlinx.android.synthetic.main.calculation_cell_view.view.*

class CalculationCellView(context: Context, private val attrs: AttributeSet) : LinearLayout(context, attrs), SquareInputView.EditorActionListener {

    init {
        LayoutInflater.from(context).inflate(R.layout.calculation_cell_view, this, true)
        setListeners()
    }

    private fun initializeAttributes() {
        this.attrs?.let {
//            val obtainStyledAttributes = context.obtainStyledAttributes(it, R.styleable.CalculationCellView, 0, 0)
//            mNextLeftView = obtainStyledAttributes.getResourceId(R.styleable.Calcu)

//            mPresenter.setValidationConfig(
//                obtainStyledAttributes.getBoolean(R.styleable.CustomEditText_emptinessIsValid, false),
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_emptyErrorText) ?: "",
//                obtainStyledAttributes.getInt(R.styleable.CustomEditText_minLength, 0),
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_invalidInputLengthText) ?: "",
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_requiredCharacterSet) ?: "",
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_missingCharacterErrorText) ?: "")
//
//            mInputType = obtainStyledAttributes.getInt(R.styleable.CustomEditText_inputType, -1)
//            mHint = obtainStyledAttributes.getString(R.styleable.CustomEditText_hint)

        }
    }

    override fun onImeActionNextKey() {
        secondNumberSiv.editTextRequestFocus()
    }

    private fun setListeners() {
        firstNumberSiv.setEditorActionListener(this)
    }
}
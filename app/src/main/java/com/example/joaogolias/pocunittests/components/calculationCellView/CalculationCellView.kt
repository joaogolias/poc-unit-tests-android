package com.example.joaogolias.pocunittests.components.calculationCellView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.joaogolias.pocunittests.R
import com.example.joaogolias.pocunittests.components.squareInput.SquareInputView
import kotlinx.android.synthetic.main.calculation_cell_view.view.*

class CalculationCellView(context: Context, private val attrs: AttributeSet) :
    LinearLayout(context, attrs),
    SquareInputView.EditorActionListener,
    CalculationCellViewContract.View {

        private val mPresenter by lazy {
            CalculationCellViewPresenter()
        }

        override fun getFirstNumber(): Int {
            return firstNumberSiv.getText().toInt()
        }

        override fun getSecondNumber(): Int {
            return secondNumberSiv.getText().toInt()
        }

        override fun displaySecondNumber(show: Boolean) {
            secondNumberSiv.visibility = if(show) View.VISIBLE else View.GONE
        }

        override fun setResult(result: Int) {
            resultTv.text = result.toString()
        }

        override fun setOperationSign(operationSign: String) {
            operationTv.text = operationSign
        }


        init {
                LayoutInflater.from(context).inflate(R.layout.calculation_cell_view, this, true)
                mPresenter.onViewAttached(this)
                setListeners()
                initializeAttributes()
            }

            private fun initializeAttributes() {
                this.attrs?.let {
                    val obtainStyledAttributes = context.obtainStyledAttributes(it, R.styleable.CalculationCellView, 0, 0)
                    val operationSign = obtainStyledAttributes.getInt(R.styleable.CalculationCellView_operation, -1)
                    mPresenter.setOperationType(operationSign)
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
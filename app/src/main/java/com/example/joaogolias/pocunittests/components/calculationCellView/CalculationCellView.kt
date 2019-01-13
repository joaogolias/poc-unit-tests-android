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
    CalculationCellViewContract.View {

        private val mPresenter by lazy {
            CalculationCellViewPresenter()
        }

        override fun getFirstNumber(): Int {
            var returnableNumber = 0
            if (!firstNumberSiv.getText().isEmpty()) {
                returnableNumber = firstNumberSiv.getText().toInt()
            }
            return returnableNumber
        }

        override fun getSecondNumber(): Int {
            var returnableNumber = 0
            if (!secondNumberSiv.getText().isEmpty()) {
                returnableNumber = secondNumberSiv.getText().toInt()
            }
            return returnableNumber
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

        override fun setFirstNumberSivActionDone() {
            firstNumberSiv.setImeOption(0)
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
                }
            }

            private fun setListeners() {
                firstNumberSiv.setEditorActionListener({
                    secondNumberSiv.editTextRequestFocus()
                }, {
                    mPresenter.calculate(getFirstNumber(), getSecondNumber())
                })

                secondNumberSiv.setEditorActionListener(null,{
                        mPresenter.calculate(getFirstNumber(), getSecondNumber())
                })


                secondNumberSiv.setOnEditTextFocusChangeHandler {hasFocus ->
                    if(!hasFocus) {
                        mPresenter.calculate(getFirstNumber(), getSecondNumber())
                    }
                }
            }
}
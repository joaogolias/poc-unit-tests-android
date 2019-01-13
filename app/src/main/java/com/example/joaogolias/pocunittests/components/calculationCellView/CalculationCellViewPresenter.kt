package com.example.joaogolias.pocunittests.components.calculationCellView


class CalculationCellViewPresenter : CalculationCellViewContract.Presenter {
    private lateinit var mView: CalculationCellViewContract.View

    override fun onViewAttached(view: CalculationCellViewContract.View) {
        mView = view
    }

    override fun setOperationType(operationType: Int) {
        when(operationType) {
            0 -> mView.setOperationSign("+")
            1 -> mView.setOperationSign("-")
            2 -> mView.setOperationSign("*")
            3 -> {
                mView.setOperationSign("!")
                mView.displaySecondNumber(false)
            }
            else -> mView.setOperationSign("+")
        }
    }

    override fun calculate(firstNumber: Int, secondNumber: Int?) {

    }
}


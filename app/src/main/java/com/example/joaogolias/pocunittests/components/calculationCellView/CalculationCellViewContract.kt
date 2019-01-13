package com.example.joaogolias.pocunittests.components.calculationCellView

interface CalculationCellViewContract {
    interface View {
        fun getFirstNumber(): Int
        fun getSecondNumber(): Int
        fun displaySecondNumber(show: Boolean)
        fun setResult(result: Int)
        fun setOperationSign(operationSign: String)
        fun setFirstNumberSivActionDone()
    }

    interface Presenter {
        fun onViewAttached(view: CalculationCellViewContract.View)
        fun setOperationType(operationType: Int)
        fun calculate(firstNumber: Int, secondNumber: Int?)
    }

}
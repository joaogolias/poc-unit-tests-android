package com.example.joaogolias.pocunittests.components.calculationCellView

interface CalculationCellViewContract {
    interface View {
        fun getFirstNumber(): Int
        fun getSecondNumber(): Int
        fun displaySecondNumber(show: Boolean)
        fun setResult(result: Int)
        fun setOperationSign(operationSign: String)
    }

    interface Presenter {
        fun setOperationType(operationType: OperationType)
        fun calculate(firstNumber: Int, secondNumber: Int?)
    }

    enum class OperationType(val type: Int) {
        SUM(0),
        SUBTRACTION(1),
        MULTIPLY(2),
        DIVISION(3),
        FACTORIAL(4)
    }
}
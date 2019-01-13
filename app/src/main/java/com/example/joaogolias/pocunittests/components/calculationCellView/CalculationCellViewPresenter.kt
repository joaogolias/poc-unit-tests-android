package com.example.joaogolias.pocunittests.components.calculationCellView


class CalculationCellViewPresenter : CalculationCellViewContract.Presenter {
    private lateinit var mView: CalculationCellViewContract.View
    private var operationType: Int = 0
    override fun onViewAttached(view: CalculationCellViewContract.View) {
        mView = view
    }

    override fun setOperationType(operationType: Int) {
        this.operationType = operationType
        when(operationType) {
            0 -> mView.setOperationSign("+")
            1 -> mView.setOperationSign("-")
            2 -> mView.setOperationSign("*")
            3 -> {
                mView.setOperationSign("!")
                mView.displaySecondNumber(false)
                mView.setFirstNumberSivActionDone()
            }
            else -> mView.setOperationSign("+")
        }
    }

    override fun calculate(firstNumber: Int, secondNumber: Int?) {
        val result = when(operationType) {
            0 -> sum(firstNumber, secondNumber)
            1 -> subtract(firstNumber, secondNumber)
            2 -> multiply(firstNumber, secondNumber)
            3 -> factorial(firstNumber)
            else -> sum(firstNumber, secondNumber)
        }

        mView.setResult(result)

    }

    override fun sum(firstNumber: Int, secondNumber: Int?): Int {
        return firstNumber + (secondNumber ?: 0)
    }

    override fun subtract(firstNumber: Int, secondNumber: Int?): Int {
        return firstNumber - (secondNumber ?: 0)
    }

    override fun multiply(firstNumber: Int, secondNumber: Int?): Int {
        return firstNumber * (secondNumber ?: 0)
    }

    override fun factorial(number: Int): Int {
        if (number == 0) return 1
        return number*factorial(number-1)
    }
}


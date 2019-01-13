package com.example.joaogolias.pocunittests

import com.example.joaogolias.pocunittests.components.calculationCellView.CalculationCellViewContract
import com.example.joaogolias.pocunittests.components.calculationCellView.CalculationCellViewPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CalculationViewPresenterTest {
    @Mock
    lateinit var view: CalculationCellViewContract.View

    lateinit var presenter: CalculationCellViewContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CalculationCellViewPresenter()
        presenter.onViewAttached(view)
    }

    @Test
    fun `should return the right sum`() {
        val result = presenter.sum(1, 4)
        Assert.assertEquals(5, result)
    }

    @Test
    fun `if second number is null, sum should return the first`() {
        val result = presenter.sum(7, null)
        Assert.assertEquals(7, result)
    }

    @Test
    fun `should return the right subtraction`() {
        val result = presenter.subtract(1, 4)
        Assert.assertEquals(-3, result)
    }

    @Test
    fun `if second number is null, subtraction should return the first`() {
        val result = presenter.sum(8, null)
        Assert.assertEquals(8, result)
    }

    @Test
    fun `should return the right product`() {
        val result = presenter.multiply(3, 9)
        Assert.assertEquals(27, result)
    }

    @Test
    fun `if second number is null, product should return 0`() {
        val result = presenter.multiply(6, null)
        Assert.assertEquals(0, result)
    }

    @Test
    fun `if factorial input is 0, it should return 1` (){
        val result = presenter.factorial(0)
        Assert.assertEquals(1, result)
    }

    @Test
    fun `should return the right factorial`() {
        val result = presenter.factorial(6)
        Assert.assertEquals(720, result)
    }
}
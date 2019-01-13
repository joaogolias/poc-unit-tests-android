package com.example.joaogolias.pocunittests.components.customEditText

import org.junit.Before
import org.junit.Test
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CustomEditTexPresentertTest {

    @Mock
    lateinit var view: CustomEditTextContract.View

    lateinit var presenter: CustomEditTextContract.Presenter

    val emptinessIsValid = false
    val emptinessError = "EmptinessError"
    val minLength = 3
    val invalidInputLengthText = "invalidInputLengthText"
    val requiredCharacterSet = "@"
    val missingCharacterErrorText = "missingCharacterErrorText"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CustomEditTextPresenter()
        presenter.onViewAttached(view)

        presenter.setValidationConfig(
            emptinessIsValid,
            emptinessError,
            minLength,
            invalidInputLengthText,
            requiredCharacterSet,
            missingCharacterErrorText)
    }

    @Test
    fun `should hide floating hint tv, when editText has no focus`() {
        presenter.handleEditTextClick(false, "")

        verify(view).displayFloatingHintTv(false)
    }

    @Test
    fun `should show floating hint tv, when editText has focus`() {
        presenter.handleEditTextClick(true, "")

        verify(view).displayFloatingHintTv(true)
    }

    @Test
    fun `should show hint tv, when editText has no focus`() {
        presenter.handleEditTextClick(false, "")

        verify(view).displayFloatingHintTv(false)
    }

    @Test
    fun `should hide hint tv, when editText has focus`() {
        presenter.handleEditTextClick(true, "")

        verify(view).displayFloatingHintTv(true)
    }

    @Test
    fun `should hide error tv, when edit text has focus`() {
        presenter.handleEditTextClick(true, "")

        verify(view).displayErrorTv(false, "")
    }


}


package com.example.joaogolias.pocunittests.components.customEditText

interface CustomEditTextContract {
    interface View {
        fun displayErrorTv(show: Boolean, text: String)
        fun displayEditTextHint(show: Boolean)
        fun displayFloatingHintTv(show: Boolean)
    }

    interface Presenter {
        fun onViewAttached(view: View)
        fun validate(text: String)
        fun handleEditTextFocusChange(hasFocus: Boolean, validationText: String)
        fun setValidationConfig(
            emptinessIsValid: Boolean,
            emptyErrorText: String,
            minLength: Int,
            invalidInputLengthText: String,
            requiredCharacterSet: String,
            missingCharacterErrorText: String)
    }
}
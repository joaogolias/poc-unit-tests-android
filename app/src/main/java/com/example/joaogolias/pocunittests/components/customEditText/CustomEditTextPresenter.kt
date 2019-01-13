package com.example.joaogolias.pocunittests.components.customEditText

class CustomEditTextPresenter : CustomEditTextContract.Presenter {
    private lateinit var mView: CustomEditTextContract.View

    private var mEmptinessIsValid: Boolean = false
    private var mEmptyErrorText: String? = ""
    private var mMinLength: Int = 0
    private var mInvalidInputLengthText: String? = ""
    private var mRequiredCharacterSet: String? = ""
    private var mMissingCharacterErrorText: String? = ""

    override fun onViewAttached(view: CustomEditTextContract.View){
        mView = view
    }

    override fun setValidationConfig(
        emptinessIsValid: Boolean,
        emptyErrorText: String,
        minLength: Int,
        invalidInputLengthText: String,
        requiredCharacterSet: String,
        missingCharacterErrorText: String) {
        mEmptinessIsValid = emptinessIsValid
        mEmptyErrorText = emptyErrorText
        mMinLength = minLength
        mInvalidInputLengthText = invalidInputLengthText
        mRequiredCharacterSet = requiredCharacterSet
        mMissingCharacterErrorText = missingCharacterErrorText
    }


    override fun handleEditTextFocusChange(hasFocus: Boolean, validationText: String) {
        mView.displayFloatingHintTv(hasFocus)
        mView.displayEditTextHint(!hasFocus)
        if (hasFocus) {
            mView.displayErrorTv(false, "")
        } else {
            validate(validationText)
        }
    }

    override  fun validate(text: String): Boolean{
        var valid = true
        if(!mEmptinessIsValid) {
            if(text.isEmpty()) {
                valid = false
                mView.displayErrorTv(true, mEmptyErrorText ?: "Invalid")
            }
        }

        if(valid && mMinLength > 0) {
            if(text.length < mMinLength) {
                valid = false
                mView.displayErrorTv(true, mInvalidInputLengthText ?: "Invalid length")
            }
        }

        if(valid && mRequiredCharacterSet?.isEmpty() == false) {
            if(!text.contains(mRequiredCharacterSet!!)){
                valid = false
                mView.displayErrorTv(true, mMissingCharacterErrorText ?: "Invalid")
            }
        }

        if(valid) {
            mView.displayErrorTv(false, "")
        }

        return valid
    }
}
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


    override fun handleEditTextClick(hasFocus: Boolean, validationText: String) {
        mView.displayFloatingHintTv(hasFocus)
        mView.displayEditTextHint(!hasFocus)
        if (hasFocus) {
            mView.displayErrorTv(false, "")
        } else {
            validate(validationText)
        }
    }

    override  fun validate(text: String){
        var alreadyValidated = false
        if(!mEmptinessIsValid) {
            if(text.isEmpty()) {
                alreadyValidated = true
                mView.displayErrorTv(true, mEmptyErrorText ?: "Invalid")
            }
        }

        if(!alreadyValidated && mMinLength > 0) {
            if(text.length < mMinLength) {
                alreadyValidated = true
                mView.displayErrorTv(true, mInvalidInputLengthText ?: "Invalid length")
            }
        }

        if(!alreadyValidated && mRequiredCharacterSet?.isEmpty() == false) {
            if(!text.contains(mRequiredCharacterSet!!)){
                alreadyValidated = true
                mView.displayErrorTv(true, mMissingCharacterErrorText ?: "Invalid")
            }
        }

        if(!alreadyValidated) {
            mView.displayErrorTv(false, "")
        }
    }
}
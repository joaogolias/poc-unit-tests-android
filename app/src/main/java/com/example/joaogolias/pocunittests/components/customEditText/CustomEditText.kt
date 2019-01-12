package com.example.joaogolias.pocunittests.components.customEditText

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.joaogolias.pocunittests.R
import kotlinx.android.synthetic.main.custom_edit_text.view.*

class CustomEditText(context: Context?, val attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var IS_PASSWORD: Int = 1

    private var mEmptinessIsValid: Boolean = false
    private var mEmptyErrorText: String? = ""
    private var mMinLength: Int = 0
    private var mInvalidInputLengthText: String? = ""
    private var mRequiredCharacterSet: String? = ""
    private var mMissingCharacterErrorText: String? = ""
    private var mHint: String? = ""
    private var mInputType: Int = 0


    init {
        LayoutInflater.from(context).inflate(R.layout.custom_edit_text, this, true)
        initializeAttributes()
        initializeEditText()
        setListeners()
    }


    private fun setListeners() {
        customEditTextEditText.setOnFocusChangeListener{ view, hasFocus ->
            floatingHintTv.visibility = if(hasFocus) View.VISIBLE else View.GONE
            floatingHintTv.text = mHint
            showEditTextHint(!hasFocus)

            if(hasFocus) {
                showErrorTv(false, "")
            } else {
                validate()
            }
        }
    }

    private fun initializeAttributes() {
        this.attrs?.let {
            val obtainStyledAttributes = context.obtainStyledAttributes(it, R.styleable.CustomEditText, 0, 0)

            mEmptinessIsValid = obtainStyledAttributes.getBoolean(R.styleable.CustomEditText_emptinessIsValid, false)
            mEmptyErrorText = obtainStyledAttributes.getString(R.styleable.CustomEditText_emptyErrorText)
            mMinLength = obtainStyledAttributes.getInt(R.styleable.CustomEditText_minLength, 0)
            mInvalidInputLengthText = obtainStyledAttributes.getString(R.styleable.CustomEditText_invalidInputLengthText)
            mRequiredCharacterSet = obtainStyledAttributes.getString(R.styleable.CustomEditText_requiredCharacterSet)
            mMissingCharacterErrorText = obtainStyledAttributes.getString(R.styleable.CustomEditText_missingCharacterErrorText)
            mHint = obtainStyledAttributes.getString(R.styleable.CustomEditText_hint)
            mInputType = obtainStyledAttributes.getInt(R.styleable.CustomEditText_inputType, 0)

        }
    }

    private fun initializeEditText() {
        showEditTextHint(true)
        showErrorTv(false, "")

        when(mInputType) {
            IS_PASSWORD -> customEditTextEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    fun showEditTextHint(show: Boolean) {
        customEditTextEditText?.hint = if(show) mHint else ""
    }

    fun validate() {
        var alreadyValidated: Boolean = false
        if(!mEmptinessIsValid) {
            if(customEditTextEditText.text.isEmpty()) {
                alreadyValidated = true
                showErrorTv(true, mEmptyErrorText ?: "Invalid")
            }
        }

        if(!alreadyValidated && mMinLength > 0) {
            if(customEditTextEditText.text.length < mMinLength) {
                alreadyValidated = true
                showErrorTv(true, mInvalidInputLengthText ?: "Invalid length")
            }
        }

        if(!alreadyValidated && mRequiredCharacterSet?.isEmpty() == false) {
            if(!customEditTextEditText.text.contains(mRequiredCharacterSet!!)){
                alreadyValidated = true
                showErrorTv(true, mMissingCharacterErrorText ?: "Invalid")
            }
        }

        if(!alreadyValidated) {
            showErrorTv(false, "")
        }
    }

    private fun showErrorTv(show: Boolean, text: String) {
        errorTv?.visibility = if(show) View.VISIBLE else View.GONE
        errorTv?.text = text
    }


}
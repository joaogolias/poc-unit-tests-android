package com.example.joaogolias.pocunittests.components.customEditText

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.joaogolias.pocunittests.R
import kotlinx.android.synthetic.main.custom_edit_text.view.*

class CustomEditText(context: Context?, val attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var mEmptinessIsValid: Boolean = false
    private var mEmptyErrorText: String? = ""
    private var mMinLength: Int = 0
    private var mInvalidInputLengthText: String? = ""
    private var mRequiredCharacterSet: String? = ""
    private var mMissingCharacterErrorText: String? = ""
    private var mHint: String? = ""
    private var isValid: Boolean = true


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

        }
    }

    private fun initializeEditText() {
        showEditTextHint(true)
        showErrorTv(false, "")
    }

    fun showEditTextHint(show: Boolean) {
        customEditTextEditText?.hint = if(show) mHint else ""
    }

    fun validate() {
        if(!mEmptinessIsValid) {
            if(customEditTextEditText.text.isEmpty()) {
                isValid = false
                showErrorTv(true, mEmptyErrorText ?: "Invalid")
            }
        }

        if(isValid && mMinLength > 0) {
            if(customEditTextEditText.text.length < mMinLength) {
                isValid = false
                showErrorTv(true, mInvalidInputLengthText ?: "Invalid length")
            }
        }

        if(isValid && mRequiredCharacterSet?.isEmpty() == false) {
            if(!customEditTextEditText.text.contains(mRequiredCharacterSet!!)){
                isValid = false
                showErrorTv(true, mMissingCharacterErrorText ?: "Invalid")
            }
        }

        if(isValid) {
            showErrorTv(false, "")
        }
    }

    private fun showErrorTv(show: Boolean, text: String) {
        errorTv?.visibility = if(show) View.VISIBLE else View.GONE
        errorTv?.text = text
    }


}
package com.example.joaogolias.pocunittests.components.customEditText

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.joaogolias.pocunittests.R
import kotlinx.android.synthetic.main.custom_edit_text.view.*

class CustomEditText(context: Context?, private val attrs: AttributeSet?) : LinearLayout(context, attrs), CustomEditTextContract.View {

    private var IS_PASSWORD: Int = 1
    private var mHint: String? = ""
    private var mInputType: Int = 0

    private val mPresenter by lazy {
        CustomEditTextPresenter()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_edit_text, this, true)
        mPresenter.onViewAttached(this)
        initializeAttributes()
        initializeEditText()
        setListeners()
    }

    override fun displayFloatingHintTv(show: Boolean) {
        floatingHintTv?.text = mHint
        floatingHintTv?.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun displayEditTextHint(show: Boolean) {
        input?.hint = if(show) mHint else ""
    }

    override fun displayErrorTv(show: Boolean, text: String) {
        errorTv?.visibility = if(show) View.VISIBLE else View.GONE
        errorTv?.text = text
    }


    private fun setListeners() {
        input.setOnFocusChangeListener{ view, hasFocus ->
            mPresenter.handleEditTextFocusChange(hasFocus, input?.text.toString())
        }
    }

    private fun initializeAttributes() {
        this.attrs?.let {
            val obtainStyledAttributes = context.obtainStyledAttributes(it, R.styleable.CustomEditText, 0, 0)

            mPresenter.setValidationConfig(
                obtainStyledAttributes.getBoolean(R.styleable.CustomEditText_emptinessIsValid, false),
                obtainStyledAttributes.getString(R.styleable.CustomEditText_emptyErrorText) ?: "",
                obtainStyledAttributes.getInt(R.styleable.CustomEditText_minLength, 0),
                obtainStyledAttributes.getString(R.styleable.CustomEditText_invalidInputLengthText) ?: "",
                obtainStyledAttributes.getString(R.styleable.CustomEditText_requiredCharacterSet) ?: "",
                obtainStyledAttributes.getString(R.styleable.CustomEditText_missingCharacterErrorText) ?: "")

            mInputType = obtainStyledAttributes.getInt(R.styleable.CustomEditText_inputType, -1)
            mHint = obtainStyledAttributes.getString(R.styleable.CustomEditText_hint)

        }
    }

    private fun initializeEditText() {
        displayEditTextHint(true)
        displayErrorTv(false, "")

        when(mInputType) {
            IS_PASSWORD -> input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }


    fun validate(): Boolean {
        return mPresenter.validate(input?.text.toString())
    }
}
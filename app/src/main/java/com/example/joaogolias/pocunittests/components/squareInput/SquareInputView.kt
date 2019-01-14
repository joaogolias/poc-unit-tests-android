package com.example.joaogolias.pocunittests.components.squareInput

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.square_input_view.view.*
import com.example.joaogolias.pocunittests.R
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.joaogolias.pocunittests.activities.hideKeyboard
import kotlinx.android.synthetic.main.custom_edit_text.view.*


class SquareInputView(context: Context, private val attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var mNextLeftView: Int = -1
    private var mImeOptions: Int = -1
    private var onImeActionNextKey: (() -> (Any))? = null
    private var onImeActionDoneKey: (() -> (Any))? = null
    private var onEditTextFocusChangeHandler: ((hasFocus: Boolean) -> (Any))? = null
    private var mInputType: Int = -1


    init {
        LayoutInflater.from(context).inflate(R.layout.square_input_view, this, true)
        initializeAttributes()
        initializeEditText()
    }

    private fun initializeAttributes() {
        this.attrs?.let {
            val obtainStyledAttributes = context.obtainStyledAttributes(it, R.styleable.SquareInputView, 0, 0)
            mNextLeftView = obtainStyledAttributes.getResourceId(R.styleable.SquareInputView_nextLeftView, -1)
            mImeOptions = obtainStyledAttributes.getInt(R.styleable.SquareInputView_imeOptions, -1)
            mInputType = obtainStyledAttributes.getInt(R.styleable.SquareInputView_inputTypeSI, -1)
        }
    }

    private fun initializeEditText() {
        squareInputEt.nextFocusLeftId = mNextLeftView
        setImeOption(mImeOptions)
        setInputType(mInputType)
    }

    fun setImeOption(option: Int) {
        when(option) {
            0 -> squareInputEt.imeOptions = EditorInfo.IME_ACTION_DONE
            1 -> squareInputEt.imeOptions = EditorInfo.IME_ACTION_NEXT
            else -> squareInputEt.imeOptions = EditorInfo.IME_ACTION_DONE
        }
    }

    fun setInputType(type: Int) {
        when(type) {
            1 -> squareInputEt.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            2 -> squareInputEt.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD)
            else -> squareInputEt.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    fun getText(): String {
        return if(squareInputEt != null) squareInputEt.text.toString() else ""
    }

    fun editTextRequestFocus() {
        this.requestFocus()
        squareInputEt.requestFocus()
    }

    fun setOnEditTextFocusChangeHandler(handler: (hasFocus: Boolean) -> (Any)) {
        this.onEditTextFocusChangeHandler = handler
        squareInputEt.setOnFocusChangeListener{ _, hasFocus ->
            this.onEditTextFocusChangeHandler?.invoke(hasFocus)
        }
    }

    fun setEditorActionListener(onImeActionNextKey: (() -> (Any))?, onImeActionDoneKey: (() -> (Any))?) {
        this.onImeActionNextKey = onImeActionNextKey
        this.onImeActionDoneKey = onImeActionDoneKey

        squareInputEt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT && this.onImeActionNextKey != null) {
                this.onImeActionNextKey?.invoke()
                return@OnEditorActionListener true
            }
            if (actionId == EditorInfo.IME_ACTION_DONE && this.onImeActionDoneKey != null) {
                this.onImeActionDoneKey?.invoke()
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }
}
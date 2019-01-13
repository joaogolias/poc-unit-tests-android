package com.example.joaogolias.pocunittests.components.squareInput

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.square_input_view.view.*
import com.example.joaogolias.pocunittests.R
import android.view.inputmethod.EditorInfo
import android.widget.TextView



class SquareInputView(context: Context, private val attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var mNextLeftView: Int = -1
    private var mImeOptions: Int = -1

    enum class ImeOptions(val value: Int) {
        DONE(0),
        NEXT(1)
    }

    interface EditorActionListener {
        fun onImeActionNextKey()
    }
    private var mEditorActionListener: EditorActionListener? = null

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

//            mPresenter.setValidationConfig(
//                obtainStyledAttributes.getBoolean(R.styleable.CustomEditText_emptinessIsValid, false),
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_emptyErrorText) ?: "",
//                obtainStyledAttributes.getInt(R.styleable.CustomEditText_minLength, 0),
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_invalidInputLengthText) ?: "",
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_requiredCharacterSet) ?: "",
//                obtainStyledAttributes.getString(R.styleable.CustomEditText_missingCharacterErrorText) ?: "")
//
//            mInputType = obtainStyledAttributes.getInt(R.styleable.CustomEditText_inputType, -1)
//            mHint = obtainStyledAttributes.getString(R.styleable.CustomEditText_hint)

        }
    }

    fun setEditorActionListener(editorActionListener: EditorActionListener) {
        mEditorActionListener = editorActionListener
    }

    private fun initializeEditText() {
        squareInputEt.nextFocusLeftId = mNextLeftView

        squareInputEt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                this.mEditorActionListener?.onImeActionNextKey()
                return@OnEditorActionListener true
            }
            false
            })

        when(mImeOptions) {
            0 -> squareInputEt.imeOptions = EditorInfo.IME_ACTION_DONE
            1 -> squareInputEt.imeOptions = EditorInfo.IME_ACTION_NEXT
            else -> squareInputEt.imeOptions = EditorInfo.IME_ACTION_DONE
        }
    }

    fun editTextRequestFocus() {
        this.requestFocus()
        squareInputEt.requestFocus()
    }
}
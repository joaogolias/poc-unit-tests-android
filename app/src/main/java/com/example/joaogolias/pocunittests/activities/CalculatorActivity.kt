package com.example.joaogolias.pocunittests.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.joaogolias.pocunittests.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setListeners()
    }

    private fun setListeners() {
        square3.setEditorActionListener(null, {
            square4.editTextRequestFocus()
        })
    }

}

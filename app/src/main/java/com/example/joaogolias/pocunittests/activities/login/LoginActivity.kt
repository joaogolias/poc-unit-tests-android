package com.example.joaogolias.pocunittests.activities.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.joaogolias.pocunittests.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private var loginController = LoginController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    private fun setListeners() {
        signInButton.setOnClickListener{
            email.validate()
            password.validate()
        }
    }

}

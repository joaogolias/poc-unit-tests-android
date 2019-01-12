package com.example.joaogolias.pocunittests.activities.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.joaogolias.pocunittests.R

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
    }

}

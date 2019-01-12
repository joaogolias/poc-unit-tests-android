package com.example.joaogolias.unittestspoc.activities.login

class LoginController {

    fun verifyEmail(email: String?): Boolean {
        return email?.contains("@") ?: false

    }

    fun verifyPassword(password: String?): Boolean {
        val length = password?.length ?: 0
        return length > 6
    }
}
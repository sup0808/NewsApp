package com.demo.newsapp.util

object FormValidator {

    fun validateEmailId(email : String) : Boolean{
        return email.isNotEmpty() && isValidEmail(email)
    }

     fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")

        return emailRegex.matches(email)
    }

     fun validatePassword(password : String) : Boolean{
        return password.isNotEmpty() && password.length>6
    }
}
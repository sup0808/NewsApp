package com.demo.newsapp.presentation.login


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.demo.newsapp.presentation.login.state.ErrorState
import com.demo.newsapp.presentation.login.state.LoginEvent
import com.demo.newsapp.presentation.login.state.UserState
import com.demo.newsapp.util.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    var userState = mutableStateOf(UserState())
    private set

    private var _errorState = mutableStateOf(ErrorState())
    val errorState : State<ErrorState> = _errorState


    fun onAction(loginEvent: LoginEvent){
        when(loginEvent){
            is LoginEvent.EmailChanged ->{
                userState.value = userState.value.copy(emailId = loginEvent.emailId)
                println("username ${userState.value.emailId}")
            }

            is LoginEvent.PasswordChanged ->{
                userState.value = userState.value.copy(password = loginEvent.password)
                println("password ${userState.value.emailId}")
            }

            is LoginEvent.Submit ->{
                validateUserLogin()

            }
        }
    }


    fun validateUserLogin(){
        val isEmailId = FormValidator.validateEmailId(userState.value.emailId)
        _errorState.value = _errorState.value.copy(emailStatus = !isEmailId)

        val isPassword = FormValidator.validatePassword(userState.value.password)
        _errorState.value = _errorState.value.copy(passwordStatus = !isPassword)

        if(isEmailId && isPassword){
            println("validated ")
        }
        else{
            println("not validated")
        }
    }
}
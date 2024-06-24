package com.demo.newsapp.presentation.login


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.newsapp.domain.GetLoginUseCase
import com.demo.newsapp.presentation.login.state.ErrorState
import com.demo.newsapp.presentation.login.state.LoginEvent
import com.demo.newsapp.presentation.login.state.LoginState
import com.demo.newsapp.presentation.login.state.UserState
import com.demo.newsapp.util.FormValidator
import com.demo.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getLoginUseCase: GetLoginUseCase): ViewModel() {

    var userState = mutableStateOf(UserState())
    private set

    private var _errorState = mutableStateOf(ErrorState())
    val errorState : State<ErrorState> = _errorState

    private var _loginState = mutableStateOf(LoginState())
    val loginState : State<LoginState> = _loginState


    fun onAction(loginEvent: LoginEvent){
        when(loginEvent){
            is LoginEvent.EmailChanged ->{
                userState.value = userState.value.copy(emailId = loginEvent.emailId)
                println("username ${userState.value.emailId}")
            }

            is LoginEvent.PasswordChanged ->{
                userState.value = userState.value.copy(password = loginEvent.password)
                println("password ${userState.value.password}")
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
            println(" validated")
           loginUser()
        }
        else{
            println("not validated")
            loginUser()
        }
    }

    fun loginUser(){
        viewModelScope.launch {
            getLoginUseCase().onEach {
                when(it){
                    is Resource.Loading -> {
                        _loginState.value = LoginState(isLoading = true)
                    }
                    is Resource.Success ->{
                        _loginState.value = LoginState(success =  it.datas,status = it.datas?.record?.status!!)
                    }
                    is Resource.Error -> {
                        _loginState.value = LoginState(error = it.errorMessage,status = it.datas?.record?.status!!)
                    }

                }
            }.launchIn(viewModelScope)
        }
    }
}
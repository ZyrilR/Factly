package com.example.factly.screens.login

interface LoginContract {

    interface View {
        fun showError(message: String)
        fun navigateToDashboard()
        fun navigateToRegister()
    }

    interface Presenter {
        fun onLoginClicked(identifier: String, password: String)
        fun onRegisterClicked()
        fun onDestroy()
    }
}
package com.example.factly.screens.register

interface RegisterContract {

    interface View {
        fun showError(message: String)
        fun showSuccess()
        fun navigateToLogin()
    }

    interface Presenter {
        fun onRegisterClicked(username: String, email: String, password: String, confirmPassword: String)
        fun onDestroy()
    }
}
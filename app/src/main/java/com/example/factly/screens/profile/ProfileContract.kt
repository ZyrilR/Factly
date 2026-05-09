package com.example.factly.screens.profile

interface ProfileContract {

    interface View {
        fun showUserInfo(username: String, email: String, password: String)
        fun showError(message: String)
        fun showSuccess(message: String)
        fun navigateToDashboard()
        fun navigateToLogin()
    }

    interface Presenter {
        fun loadUserInfo()
        fun onBackClicked()
        fun onLogoutClicked()
        fun onDestroy()
    }
}
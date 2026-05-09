package com.example.factly.screens.register

import com.example.factly.data.repositories.UserRepository

class RegisterPresenter(private var view: RegisterContract.View?) : RegisterContract.Presenter {

    override fun onRegisterClicked(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view?.showError("Please fill in all fields")
            return
        }
        if (!email.contains("@")) {
            view?.showError("Enter a valid email")
            return
        }
        if (password != confirmPassword) {
            view?.showError("Passwords do not match")
            return
        }
        val success = UserRepository.register(username, email, password)
        if (success) {
            view?.showSuccess()
            view?.navigateToLogin()
        } else {
            view?.showError("Username or email already exists")
        }
    }

    override fun onDestroy() {
        view = null
    }
}
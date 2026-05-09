package com.example.factly.screens.login

import com.example.factly.data.repositories.UserRepository

class LoginPresenter(private var view: LoginContract.View?) : LoginContract.Presenter {

    override fun onLoginClicked(identifier: String, password: String) {
        if (identifier.isEmpty() || password.isEmpty()) {
            view?.showError("Fields cannot be empty")
            return
        }
        val success = UserRepository.login(identifier, password)
        if (success) {
            view?.navigateToDashboard()
        } else {
            view?.showError("Invalid credentials")
        }
    }

    override fun onRegisterClicked() {
        view?.navigateToRegister()
    }

    override fun onDestroy() {
        view = null
    }
}
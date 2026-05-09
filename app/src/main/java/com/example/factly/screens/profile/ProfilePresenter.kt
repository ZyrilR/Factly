package com.example.factly.screens.profile

import com.example.factly.data.repositories.UserRepository

class ProfilePresenter(private var view: ProfileContract.View?) : ProfileContract.Presenter {

    override fun loadUserInfo() {
        val user = UserRepository.getLoggedInUser()
        if (user != null) {
            view?.showUserInfo(user.username, user.email, user.password)
        } else {
            view?.showError("No user session found")
        }
    }

    override fun onBackClicked() {
        view?.navigateToDashboard()
    }

    override fun onLogoutClicked() {
        UserRepository.logout()
        view?.navigateToLogin()
    }

    override fun onDestroy() {
        view = null
    }
}
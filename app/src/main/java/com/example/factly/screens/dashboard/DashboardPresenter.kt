package com.example.factly.screens.dashboard

import com.example.factly.data.models.Fact
import com.example.factly.data.repositories.FactRepository
import com.example.factly.data.repositories.UserRepository

class DashboardPresenter(private var view: DashboardContract.View?) : DashboardContract.Presenter {

    private var currentFact: Fact? = null

    override fun loadFact() {
        currentFact = FactRepository.getRandomFact()
        currentFact?.let { view?.displayFact(it) }
    }

    override fun onNextClicked() {
        loadFact()
    }

    override fun onSaveClicked() {
        val fact = currentFact
        if (fact != null) {
            FactRepository.saveFavorite(fact)
            view?.showSaved()
        } else {
            view?.showError("No fact to save")
        }
    }

    override fun onProfileClicked() {
        view?.navigateToProfile()
    }

    override fun onFavoritesClicked() {
        view?.navigateToFavorites()
    }

    override fun onLogoutClicked() {
        UserRepository.logout()
        view?.navigateToLogin()
    }

    override fun onDestroy() {
        view = null
    }
}
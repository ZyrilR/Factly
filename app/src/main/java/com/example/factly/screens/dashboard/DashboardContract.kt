package com.example.factly.screens.dashboard

import com.example.factly.data.models.Fact

interface DashboardContract {

    interface View {
        fun displayFact(fact: Fact)
        fun showSaved()
        fun showStarOutline()
        fun showError(message: String)
        fun navigateToProfile()
        fun navigateToFavorites()
        fun navigateToLogin()
    }

    interface Presenter {
        fun loadFact()
        fun onNextClicked()
        fun onSaveClicked()
        fun onProfileClicked()
        fun onFavoritesClicked()
        fun onLogoutClicked()
        fun onDestroy()
    }
}
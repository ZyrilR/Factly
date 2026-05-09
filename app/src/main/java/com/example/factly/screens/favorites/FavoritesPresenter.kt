package com.example.factly.screens.favorites

import com.example.factly.data.models.Fact
import com.example.factly.data.repositories.FactRepository

class FavoritesPresenter(private var view: FavoritesContract.View?) : FavoritesContract.Presenter {

    override fun loadFavorites() {
        view?.showFavorites(FactRepository.getFavorites())
    }

    override fun onFactClicked(fact: Fact) {
        view?.showMessage("${fact.topic}: ${fact.content}")
    }

    override fun removeFavorite(position: Int) {
        FactRepository.removeFavorite(position)
        view?.showMessage("Removed from favorites")
        view?.refreshList()
    }

    override fun onDestroy() {
        view = null
    }
}
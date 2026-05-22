package com.example.factly.screens.favorites

import com.example.factly.data.models.Fact

interface FavoritesContract {

    interface View {
        fun showFavorites(facts: List<Fact>)
        fun showFactDetail(fact: Fact)
        fun showRemoveConfirmation(position: Int)
        fun showMessage(message: String)
        fun refreshList()
    }

    interface Presenter {
        fun loadFavorites()
        fun onFactClicked(fact: Fact)
        fun onRemoveClicked(position: Int)
        fun removeFavorite(position: Int)
        fun onDestroy()
    }
}
package com.example.factly.screens.topics

import com.example.factly.data.repositories.FactRepository

class TopicSelectionPresenter(private var view: TopicSelectionContract.View?) : TopicSelectionContract.Presenter {

    override fun loadTopics() {
        view?.showTopics(FactRepository.allTopics, FactRepository.selectedTopics)
    }

    override fun onApplyClicked(selected: Set<String>) {
        FactRepository.selectedTopics = selected.toMutableSet()
        view?.navigateBack()
    }

    override fun onBackClicked() { view?.navigateBack() }

    override fun onDestroy() { view = null }
}

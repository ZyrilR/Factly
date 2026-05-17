package com.example.factly.screens.topics

interface TopicSelectionContract {

    interface View {
        fun showTopics(topics: List<String>, selected: Set<String>)
        fun navigateBack()
    }

    interface Presenter {
        fun loadTopics()
        fun onApplyClicked(selected: Set<String>)
        fun onBackClicked()
        fun onDestroy()
    }
}

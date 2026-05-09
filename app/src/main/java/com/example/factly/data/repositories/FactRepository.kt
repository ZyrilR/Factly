package com.example.factly.data.repositories

import com.example.factly.data.models.Fact

object FactRepository {

    private val facts = listOf(
        Fact(1,  "Honey never spoils. Archaeologists found 3000-year-old honey in Egyptian tombs.", "Science"),
        Fact(2,  "The Great Wall of China is not visible from space with the naked eye.", "History"),
        Fact(3,  "The Pacific Ocean is larger than all landmasses combined.", "Geography"),
        Fact(4,  "Octopuses have three hearts and blue blood.", "Science"),
        Fact(5,  "The shortest war in history lasted 38 to 45 minutes.", "History"),
        Fact(6,  "Vatican City is the smallest country in the world.", "Geography"),
        Fact(7,  "A group of flamingos is called a flamboyance.", "Science"),
        Fact(8,  "The Eiffel Tower can grow 6 inches taller in summer due to heat.", "Science"),
        Fact(9,  "Russia has more time zones than any other country.", "Geography"),
        Fact(10, "The first Olympic Games were held in 776 BC in Greece.", "History")
    )

    private val favorites = mutableListOf<Fact>()
    private var currentIndex = 0

    fun getRandomFact(): Fact {
        val shuffled = facts.shuffled()
        return shuffled[currentIndex % shuffled.size].also { currentIndex++ }
    }

    fun saveFavorite(fact: Fact) {
        if (!favorites.contains(fact)) favorites.add(fact)
    }

    fun removeFavorite(position: Int) {
        if (position in favorites.indices) favorites.removeAt(position)
    }

    fun getFavorites(): List<Fact> = favorites.toList()
}
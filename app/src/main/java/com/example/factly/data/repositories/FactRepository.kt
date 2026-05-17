package com.example.factly.data.repositories

import com.example.factly.data.models.Fact

object FactRepository {

    val allTopics = listOf("Science", "Sports", "Geography", "Technology", "Food", "Animals", "Space", "Human Body", "History")

    private val facts = listOf(
        Fact(1,  "Honey never spoils. Archaeologists found pots of honey in ancient Egyptian tombs that are over 3,000 years old and still edible.", "Food"),
        Fact(2,  "The Great Wall of China is not visible from space with the naked eye.", "History"),
        Fact(3,  "The Pacific Ocean is larger than all landmasses on Earth combined.", "Geography"),
        Fact(4,  "Octopuses have three hearts and blue blood.", "Science"),
        Fact(5,  "The shortest war in history lasted only 38 to 45 minutes.", "History"),
        Fact(6,  "Vatican City is the smallest country in the world.", "Geography"),
        Fact(7,  "A group of flamingos is called a flamboyance.", "Animals"),
        Fact(8,  "The Eiffel Tower can grow 6 inches taller in summer due to thermal expansion.", "Science"),
        Fact(9,  "Russia has more time zones than any other country — 11 in total.", "Geography"),
        Fact(10, "The first Olympic Games were held in 776 BC in ancient Greece.", "Sports"),
        Fact(11, "A bolt of lightning is five times hotter than the surface of the sun.", "Science"),
        Fact(12, "The term 'bug' in programming came from an actual insect found in a computer in 1947.", "Technology"),
        Fact(13, "Bananas are berries, but strawberries are not.", "Food"),
        Fact(14, "There are more stars in the universe than grains of sand on all Earth's beaches.", "Space"),
        Fact(15, "The human body has enough iron to make a 3-inch nail.", "Human Body"),
        Fact(16, "A sneeze travels at about 100 miles per hour.", "Human Body"),
        Fact(17, "The first website ever created is still online today.", "Technology"),
        Fact(18, "A day on Venus is longer than a year on Venus.", "Space"),
        Fact(19, "Sloths can hold their breath longer than dolphins.", "Animals"),
        Fact(20, "Avocados are fruits, and they are technically a type of berry.", "Food")
    )

    private val favorites = mutableListOf<Fact>()
    var selectedTopics = mutableSetOf<String>()

    fun getRandomFact(): Fact {
        val pool = if (selectedTopics.isEmpty()) facts else facts.filter { it.topic in selectedTopics }
        return (if (pool.isEmpty()) facts else pool).random()
    }

    fun saveFavorite(fact: Fact) {
        if (!favorites.contains(fact)) favorites.add(fact)
    }

    fun removeFavorite(position: Int) {
        if (position in favorites.indices) favorites.removeAt(position)
    }

    fun getFavorites(): List<Fact> = favorites.toList()
}

package com.example.factly.data.repositories

import com.example.factly.data.models.Fact

object FactRepository {

    val allTopics = listOf(
        "Science", "Sports", "Geography", "Technology",
        "Food", "Animals", "Space", "Human Body", "History"
    )

    private val facts = listOf(
        // Science
        Fact(1,  "Honey never spoils. Archaeologists found pots of honey in ancient Egyptian tombs that are over 3,000 years old and still edible.", "Food"),
        Fact(2,  "A bolt of lightning is five times hotter than the surface of the sun.", "Science"),
        Fact(3,  "The Eiffel Tower can grow 6 inches taller in summer due to thermal expansion.", "Science"),
        Fact(4,  "Octopuses have three hearts and blue blood.", "Science"),
        Fact(5,  "Water can boil and freeze at the same time — this is called the triple point.", "Science"),
        Fact(6,  "Sound travels about four times faster through water than through air.", "Science"),
        Fact(7,  "A teaspoonful of neutron star material would weigh about 10 million tonnes.", "Science"),
        Fact(8,  "Glass is actually a super-cooled liquid, not a solid — it flows extremely slowly over centuries.", "Science"),

        // Sports
        Fact(9,  "The first Olympic Games were held in 776 BC in ancient Greece.", "Sports"),
        Fact(10, "A regulation NBA basketball must be inflated to between 7.5 and 8.5 pounds per square inch.", "Sports"),
        Fact(11, "Golf is the only sport played on the moon — Alan Shepard hit a golf ball there in 1971.", "Sports"),
        Fact(12, "The marathon distance of 26.2 miles was standardized at the 1908 London Olympics.", "Sports"),
        Fact(13, "Table tennis balls travel at speeds of up to 150 km/h during professional matches.", "Sports"),
        Fact(14, "Usain Bolt's record 100m sprint speed reached approximately 44.72 km/h.", "Sports"),
        Fact(15, "Archery was removed from the Olympics in 1920 and reinstated in 1972.", "Sports"),
        Fact(16, "The Tour de France covers roughly 3,500 km and burns around 120,000 calories per rider.", "Sports"),

        // Geography
        Fact(17, "The Pacific Ocean is larger than all landmasses on Earth combined.", "Geography"),
        Fact(18, "Vatican City is the smallest country in the world.", "Geography"),
        Fact(19, "Russia has more time zones than any other country — 11 in total.", "Geography"),
        Fact(20, "Canada has more lakes than all other countries in the world combined.", "Geography"),
        Fact(21, "The Sahara Desert is roughly the same size as the contiguous United States.", "Geography"),
        Fact(22, "Mount Everest grows about 4mm taller every year due to tectonic uplift.", "Geography"),
        Fact(23, "The Amazon River discharges more water than the next seven largest rivers combined.", "Geography"),
        Fact(24, "Australia is wider than the moon — about 4,000 km vs the moon's 3,474 km diameter.", "Geography"),

        // Technology
        Fact(25, "The term 'bug' in programming came from an actual insect found in a computer in 1947.", "Technology"),
        Fact(26, "The first website ever created is still online today.", "Technology"),
        Fact(27, "More than 90% of the world's currency exists only digitally.", "Technology"),
        Fact(28, "The first computer mouse was made of wood, invented by Doug Engelbart in 1964.", "Technology"),
        Fact(29, "There are more possible iterations of a game of chess than atoms in the observable universe.", "Technology"),
        Fact(30, "The average smartphone today has more computing power than NASA had during the Apollo moon landings.", "Technology"),
        Fact(31, "About 3.5 billion Google searches are made every single day.", "Technology"),
        Fact(32, "Wi-Fi was invented accidentally while a scientist was trying to detect mini black holes.", "Technology"),

        // Food
        Fact(33, "Bananas are berries, but strawberries are not.", "Food"),
        Fact(34, "Avocados are fruits, and they are technically a type of berry.", "Food"),
        Fact(35, "Honey never spoils and has been found edible in 3,000-year-old Egyptian tombs.", "Food"),
        Fact(36, "Almonds are a member of the peach family.", "Food"),
        Fact(37, "Chocolate was once used as currency by the Aztecs.", "Food"),
        Fact(38, "Carrots were originally purple, not orange — Dutch growers developed the orange variety.", "Food"),
        Fact(39, "Peanuts are not nuts — they are legumes, closer related to beans and lentils.", "Food"),
        Fact(40, "Apples, pears, and roses all belong to the same plant family, Rosaceae.", "Food"),

        // Animals
        Fact(41, "A group of flamingos is called a flamboyance.", "Animals"),
        Fact(42, "Sloths can hold their breath longer than dolphins.", "Animals"),
        Fact(43, "Crows can recognize human faces and hold grudges for years.", "Animals"),
        Fact(44, "A shrimp's heart is located in its head.", "Animals"),
        Fact(45, "Butterflies taste with their feet using chemoreceptors on their tarsi.", "Animals"),
        Fact(46, "Wombat droppings are cube-shaped — the only animal known to produce cube feces.", "Animals"),
        Fact(47, "Elephants are the only animals that cannot jump.", "Animals"),
        Fact(48, "A group of owls is called a parliament.", "Animals"),

        // Space
        Fact(49, "There are more stars in the universe than grains of sand on all Earth's beaches.", "Space"),
        Fact(50, "A day on Venus is longer than a year on Venus.", "Space"),
        Fact(51, "Footprints left on the moon will last for at least 100 million years due to no wind or rain.", "Space"),
        Fact(52, "The largest known star, UY Scuti, is about 1,700 times the radius of our Sun.", "Space"),
        Fact(53, "One million Earths could fit inside the Sun.", "Space"),
        Fact(54, "The International Space Station travels at 28,000 km/h — completing 16 orbits per day.", "Space"),
        Fact(55, "Neutron stars spin up to 700 times per second.", "Space"),
        Fact(56, "The Milky Way galaxy is estimated to be about 13.6 billion years old.", "Space"),

        // Human Body
        Fact(57, "The human body has enough iron to make a 3-inch nail.", "Human Body"),
        Fact(58, "A sneeze travels at about 100 miles per hour.", "Human Body"),
        Fact(59, "Your brain generates about 12-25 watts of electricity — enough to power a low-wattage LED bulb.", "Human Body"),
        Fact(60, "The human nose can detect over 1 trillion different scents.", "Human Body"),
        Fact(61, "Bones are about 5 times stronger than steel of the same density.", "Human Body"),
        Fact(62, "The surface area of human lungs is roughly equal to the size of a tennis court.", "Human Body"),
        Fact(63, "Humans share 60% of their DNA with bananas.", "Human Body"),
        Fact(64, "The cornea of the eye has no blood supply — it gets oxygen directly from the air.", "Human Body"),

        // History
        Fact(65, "The Great Wall of China is not visible from space with the naked eye.", "History"),
        Fact(66, "The shortest war in history lasted only 38 to 45 minutes.", "History"),
        Fact(67, "Cleopatra lived closer in time to the Moon landing than to the construction of the Great Pyramid.", "History"),
        Fact(68, "The Vikings used the stars and a 'sunstone' crystal to navigate even on cloudy days.", "History"),
        Fact(69, "Napoleon was not unusually short — he was around 5'7\", average for his era.", "History"),
        Fact(70, "The Roman Empire lasted for over 1,000 years — from 27 BC to 1453 AD if including the East.", "History"),
        Fact(71, "Oxford University is older than the Aztec Empire.", "History"),
        Fact(72, "Ancient Egyptians used moldy bread as an antibiotic centuries before penicillin was discovered.", "History")
    )

    private val favorites = mutableListOf<Fact>()
    var selectedTopics = mutableSetOf<String>()

    fun getRandomFact(): Fact {
        val pool = if (selectedTopics.isEmpty()) facts
        else facts.filter { it.topic in selectedTopics }
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
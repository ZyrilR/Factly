package com.example.factly.data.repositories

import com.example.factly.data.models.Fact

object FactRepository {

    val allTopics = listOf(
        "Science", "Sports", "Geography", "Technology",
        "Food", "Animals", "Space", "Human Body", "History"
    )

    private val facts = listOf(
        // Science
        Fact(1, "Honey never spoils. Archaeologists found pots of honey in ancient Egyptian tombs that are over 3,000 years old and still edible.", "Science"),
        Fact(2, "A bolt of lightning is five times hotter than the surface of the sun.", "Science"),
        Fact(3, "The Eiffel Tower can grow 6 inches taller in summer due to thermal expansion.", "Science"),
        Fact(4, "Octopuses have three hearts and blue blood.", "Science"),
        Fact(5, "Water can boil and freeze at the same time. This is called the triple point.", "Science"),
        Fact(6, "Sound travels about four times faster through water than through air.", "Science"),
        Fact(7, "A teaspoonful of neutron star material would weigh about 10 million tonnes.", "Science"),
        Fact(8, "Glass is actually a super-cooled liquid, not a solid. It flows extremely slowly over centuries.", "Science"),
        Fact(9, "The human eye can distinguish approximately 10 million different colors.", "Science"),
        Fact(10, "Rubber bands last longer when refrigerated because cold slows the degradation of their polymers.", "Science"),
        Fact(11, "A day on Mars is 24 hours and 37 minutes, remarkably close to an Earth day.", "Science"),

        // Sports
        Fact(12, "The first Olympic Games were held in 776 BC in ancient Greece.", "Sports"),
        Fact(13, "A regulation NBA basketball must be inflated to between 7.5 and 8.5 PSI.", "Sports"),
        Fact(14, "Golf is the only sport played on the moon. Alan Shepard hit a golf ball there in 1971.", "Sports"),
        Fact(15, "The marathon distance of 26.2 miles was standardized at the 1908 London Olympics.", "Sports"),
        Fact(16, "Table tennis balls can travel at speeds of up to 150 km/h during professional matches.", "Sports"),
        Fact(17, "Usain Bolt reached approximately 44.72 km/h during his record 100m sprint.", "Sports"),
        Fact(18, "Archery was removed from the Olympics in 1920 and reinstated in 1972.", "Sports"),
        Fact(19, "The Tour de France covers roughly 3,500 km and burns around 120,000 calories per rider.", "Sports"),
        Fact(20, "The longest tennis match in history lasted 11 hours and 5 minutes at Wimbledon 2010.", "Sports"),
        Fact(21, "Michael Phelps holds more Olympic gold medals than 67 countries have ever won combined.", "Sports"),
        Fact(22, "A standard soccer ball is made of 32 panels: 20 hexagons and 12 pentagons.", "Sports"),

        // Geography
        Fact(23, "The Pacific Ocean is larger than all landmasses on Earth combined.", "Geography"),
        Fact(24, "Vatican City is the smallest country in the world.", "Geography"),
        Fact(25, "Russia has 11 time zones, more than any other country.", "Geography"),
        Fact(26, "Canada has more lakes than all other countries combined.", "Geography"),
        Fact(27, "The Sahara Desert is roughly the same size as the contiguous United States.", "Geography"),
        Fact(28, "Mount Everest grows about 4 mm taller every year due to tectonic uplift.", "Geography"),
        Fact(29, "The Amazon River discharges more water than the next seven largest rivers combined.", "Geography"),
        Fact(30, "Australia is wider than the moon.", "Geography"),
        Fact(31, "There are more pyramids in Sudan than in Egypt.", "Geography"),
        Fact(32, "The Dead Sea is so salty that people float effortlessly.", "Geography"),
        Fact(33, "Iceland has no mosquitoes because of its climate and geology.", "Geography"),

        // Technology
        Fact(34, "The term 'bug' in programming came from an actual insect found in a computer in 1947.", "Technology"),
        Fact(35, "The first website ever created is still online today.", "Technology"),
        Fact(36, "More than 90% of the world’s currency exists only digitally.", "Technology"),
        Fact(37, "The first computer mouse was made of wood and invented by Doug Engelbart in 1964.", "Technology"),
        Fact(38, "There are more possible chess games than atoms in the observable universe.", "Technology"),
        Fact(39, "Modern smartphones have more computing power than NASA had during the Apollo moon landings.", "Technology"),
        Fact(40, "About 3.5 billion Google searches are made every day.", "Technology"),
        Fact(41, "Wi-Fi was invented accidentally while scientists were trying to detect mini black holes.", "Technology"),
        Fact(42, "The first domain name ever registered was Symbolics.com on March 15, 1985.", "Technology"),
        Fact(43, "Email is older than the World Wide Web.", "Technology"),
        Fact(44, "The Apollo 11 guidance computer had less processing power than a modern USB cable.", "Technology"),

        // Food
        Fact(45, "Bananas are berries, but strawberries are not.", "Food"),
        Fact(46, "Avocados are fruits and technically a type of berry.", "Food"),
        Fact(47, "Honey never spoils and has been found edible in 3,000-year-old Egyptian tombs.", "Food"),
        Fact(48, "Almonds are part of the peach family.", "Food"),
        Fact(49, "Chocolate was once used as currency by the Aztecs.", "Food"),
        Fact(50, "Carrots were originally purple before orange varieties were developed.", "Food"),
        Fact(51, "Peanuts are legumes, not nuts.", "Food"),
        Fact(52, "Apples, pears, and roses belong to the same plant family, Rosaceae.", "Food"),
        Fact(53, "Cashews grow outside the cashew apple fruit, and their shells are toxic.", "Food"),
        Fact(54, "Ketchup was sold as medicine in the 1830s.", "Food"),
        Fact(55, "Kopi Luwak is one of the world’s most expensive coffees.", "Food"),

        // Animals
        Fact(56, "A group of flamingos is called a flamboyance.", "Animals"),
        Fact(57, "Sloths can hold their breath longer than dolphins.", "Animals"),
        Fact(58, "Crows can recognize human faces and remember them for years.", "Animals"),
        Fact(59, "A shrimp’s heart is located in its head.", "Animals"),
        Fact(60, "Butterflies taste with their feet.", "Animals"),
        Fact(61, "Wombats produce cube-shaped droppings.", "Animals"),
        Fact(62, "Elephants are the only animals that cannot jump.", "Animals"),
        Fact(63, "A group of owls is called a parliament.", "Animals"),
        Fact(64, "Mantis shrimp can punch with the force of a bullet and have 16 color receptors.", "Animals"),
        Fact(65, "Sea otters hold hands while sleeping to avoid drifting apart.", "Animals"),
        Fact(66, "A group of cats is called a clowder, while a group of kittens is called a kindle.", "Animals"),

        // Space
        Fact(67, "There are more stars in the universe than grains of sand on Earth’s beaches.", "Space"),
        Fact(68, "A day on Venus is longer than its year.", "Space"),
        Fact(69, "Footprints on the moon can last for millions of years because there is no wind or rain.", "Space"),
        Fact(70, "UY Scuti is about 1,700 times the radius of the Sun.", "Space"),
        Fact(71, "About one million Earths could fit inside the Sun.", "Space"),
        Fact(72, "The International Space Station travels at about 28,000 km/h.", "Space"),
        Fact(73, "Neutron stars can spin up to 700 times per second.", "Space"),
        Fact(74, "The Milky Way is estimated to be around 13.6 billion years old.", "Space"),
        Fact(75, "If all empty atomic space were removed, humanity could fit into a sugar cube.", "Space"),
        Fact(76, "Saturn’s rings are only about 10 meters thick on average.", "Space"),
        Fact(77, "The Sagittarius B2 cloud contains massive amounts of alcohol in space.", "Space"),

        // Human Body
        Fact(78, "The human body contains enough iron to make a 3-inch nail.", "Human Body"),
        Fact(79, "A sneeze can travel at about 100 miles per hour.", "Human Body"),
        Fact(80, "The brain generates enough electricity to power a small LED bulb.", "Human Body"),
        Fact(81, "The human nose can detect over 1 trillion different scents.", "Human Body"),
        Fact(82, "Bones are about five times stronger than steel of the same density.", "Human Body"),
        Fact(83, "Human lungs have a surface area roughly equal to a tennis court.", "Human Body"),
        Fact(84, "Humans share about 60% of their DNA with bananas.", "Human Body"),
        Fact(85, "The cornea has no blood supply and gets oxygen directly from the air.", "Human Body"),
        Fact(86, "Stomach acid is strong enough to dissolve zinc, but the stomach lining constantly replaces itself.", "Human Body"),
        Fact(87, "The liver performs over 500 functions in the body.", "Human Body"),
        Fact(88, "Human blood vessels stretch about 100,000 kilometers in total length.", "Human Body"),

        // History
        Fact(89, "The Great Wall of China is not visible from space with the naked eye.", "History"),
        Fact(90, "The shortest war in history lasted between 38 and 45 minutes.", "History"),
        Fact(91, "Cleopatra lived closer to the Moon landing than to the construction of the Great Pyramid.", "History"),
        Fact(92, "Vikings used stars and sunstones to navigate on cloudy days.", "History"),
        Fact(93, "Napoleon Bonaparte was around 5'7, average for his time.", "History"),
        Fact(94, "The Roman Empire lasted for more than 1,000 years.", "History"),
        Fact(95, "Oxford University is older than the Aztec Empire.", "History"),
        Fact(96, "Ancient Egyptians used moldy bread as an early antibiotic.", "History"),
        Fact(97, "The Great Fire of London destroyed over 13,000 houses but reportedly killed fewer than 10 people.", "History"),
        Fact(98, "Albert Einstein was offered the presidency of Israel in 1952 but declined.", "History"),
        Fact(99, "ENIAC weighed 27 tons and filled an entire room.", "History"),
        Fact(100, "Shaking hands originated as a way to show neither person carried a weapon.", "History"),
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
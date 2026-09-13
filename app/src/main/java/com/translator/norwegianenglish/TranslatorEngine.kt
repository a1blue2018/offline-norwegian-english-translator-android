package com.translator.norwegianenglish

object TranslatorEngine {
    // Dictionary mapping for Norwegian to English
    private val norwegianToEnglish = mapOf(
        "hallo" to "hello",
        "takk" to "thank you",
        "ja" to "yes",
        "nei" to "no",
        "vær så god" to "here you go",
        "hva heter du" to "what is your name",
        "jeg heter" to "my name is",
        "hvor" to "where",
        "hva" to "what",
        "hvem" to "who",
        "når" to "when",
        "hvorfor" to "why",
        "hvordan" to "how",
        "god morgen" to "good morning",
        "god natt" to "good night",
        "ja takk" to "yes please",
        "nei takk" to "no thank you",
        "unnskyld" to "sorry",
        "vær så snill" to "please",
        "bilvei" to "highway",
        "mann" to "man",
        "kvinne" to "woman",
        "barn" to "child",
        "hus" to "house",
        "dør" to "door",
        "vindu" to "window",
        "stol" to "chair",
        "bord" to "table",
        "bok" to "book",
        "penn" to "pen",
        "rødt" to "red",
        "blått" to "blue",
        "grønt" to "green",
        "gult" to "yellow",
        "svart" to "black",
        "hvitt" to "white",
        "stor" to "big",
        "liten" to "small",
        "hot" to "hot",
        "kald" to "cold",
        "varm" to "warm",
        "kjærlighet" to "love",
        "venn" to "friend",
        "familie" to "family",
        "mat" to "food",
        "drikke" to "drink",
        "vann" to "water",
        "kaffe" to "coffee",
        "te" to "tea",
        "melk" to "milk",
        "brød" to "bread",
        "smør" to "butter",
        "ost" to "cheese",
        "egg" to "egg",
        "kjøtt" to "meat",
        "fisk" to "fish",
        "grønnsaker" to "vegetables",
        "frukt" to "fruit",
        "eple" to "apple",
        "appelsin" to "orange",
        "banan" to "banana",
        "blomst" to "flower",
        "tre" to "tree",
        "skog" to "forest",
        "fjell" to "mountain",
        "elv" to "river",
        "sjø" to "lake",
        "hav" to "sea",
        "sand" to "sand",
        "stein" to "stone",
        "gull" to "gold",
        "sølv" to "silver",
        "jern" to "iron",
        "glass" to "glass",
        "papir" to "paper",
        "klær" to "clothes",
        "skje" to "shoe",
        "hatt" to "hat",
        "jakke" to "jacket",
        "bukse" to "pants",
        "kjole" to "dress",
        "skjorte" to "shirt",
        "sokk" to "sock",
        "hansker" to "gloves",
        "scarf" to "scarf",
        "lue" to "hat",
        "kår" to "car",
        "sykkel" to "bicycle",
        "tog" to "train",
        "fly" to "plane",
        "skip" to "ship",
        "båt" to "boat",
        "buss" to "bus",
        "taxi" to "taxi",
        "cykel" to "motorcycle",
        "sykkelpath" to "bike path",
        "driva" to "drift",
        "kjøre" to "drive",
        "gå" to "walk",
        "løpe" to "run",
        "hoppe" to "jump",
        "falle" to "fall",
        "stige" to "climb",
        "svømme" to "swim",
        "danse" to "dance",
        "synge" to "sing",
        "spille" to "play",
        "jobbe" to "work",
        "sove" to "sleep",
        "våkne" to "wake up",
        "sitte" to "sit",
        "stå" to "stand",
        "ligge" to "lie",
        "rygge" to "back",
        "forover" to "forward",
        "ut" to "out",
        "inn" to "in",
        "opp" to "up",
        "ned" to "down",
        "ved siden av" to "beside",
        "over" to "over",
        "under" to "under",
        "foran" to "in front",
        "bak" to "behind",
        "mellom" to "between",
        "innenfor" to "inside",
        "utenfor" to "outside",
        "nærmere" to "closer",
        "fjernere" to "farther"
    )

    fun translate(text: String, norwegianToEnglish: Boolean): String {
        val lowerText = text.lowercase().trim()

        if (lowerText.isEmpty()) {
            return ""
        }

        return if (norwegianToEnglish) {
            // Norwegian to English
            this.norwegianToEnglish[lowerText] 
                ?: findClosestMatch(lowerText, this.norwegianToEnglish)
                ?: "Translation not found"
        } else {
            // English to Norwegian (reverse lookup)
            val englishToNorwegian = this.norwegianToEnglish.entries.associateBy({ it.value }) { it.key }
            englishToNorwegian[lowerText]
                ?: findClosestMatch(lowerText, englishToNorwegian)
                ?: "Translation not found"
        }
    }

    private fun findClosestMatch(text: String, dictionary: Map<String, String>): String? {
        // Simple fuzzy matching - check if dictionary keys contain the text
        return dictionary.entries
            .find { it.key.contains(text) || text.contains(it.key) }
            ?.value
    }
}

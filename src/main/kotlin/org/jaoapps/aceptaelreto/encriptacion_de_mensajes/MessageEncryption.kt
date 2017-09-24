package org.jaoapps.aceptaelreto.encriptacion_de_mensajes


class Dictionary(referencePosition: Char = 'p', encryptionKey: Char = 'p') {

    private val dictionary:Map<Char, Int> = createDictionary()
    private var jump: Int = 0   // TODO: ¿Por qué necesito inicializar esto para que no me falle?
    public lateinit var encryptedDictionary: MutableMap<Char, Int>  // TODO: Crear los getters

    init {
        createCesarDictionary(encryptionKey, referencePosition)
    }

    internal fun createCesarDictionary(encryptionKey: Char, referencePosition: Char) {
        val cipherPosition = dictionary[encryptionKey]
        val referencePosition = dictionary[referencePosition]
        jump = cipherPosition!! - referencePosition!!

        encryptedDictionary = createEncryptedDictionary()
    }

    internal fun createDictionary(): Map<Char, Int> {
        return mutableMapOf(
                'a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8, 'i' to 9, 'j' to 10,
                'k' to 11, 'l' to 12, 'm' to 13, 'n' to 14, 'o' to 15, 'p' to 16, 'q' to 17, 'r' to 18, 's' to 19, 't' to 20,
                'u' to 21, 'v' to 22, 'w' to 23, 'x' to 24, 'y' to 25, 'z' to 26
        )
    }

    // TODO: no puedo pasarle la función con la lógica de los ifs a una lambda o algo así? lo he intentado y no entiendo como funciona.
    private fun createEncryptedDictionary(): MutableMap<Char, Int> {
        val encryptedDictionary = mutableMapOf<Char, Int>()

        for ((key, value) in dictionary) {
            val newValue = if (jumpIsPositive()) moveRight(value) else moveLeft(value)
            encryptedDictionary.put(key, newValue)
        }

        return encryptedDictionary
    }

    private fun jumpIsPositive() = jump >= 0

    private fun moveRight(value: Int): Int {
        return if (reachDictionaryLeftLimit(value)) (value + dictionary.size - jump) else (value - jump)
    }

    private fun reachDictionaryLeftLimit(value: Int) = value - jump <= 0

    private fun moveLeft(value: Int): Int {
        val absJump = Math.abs(jump)
        return if (reachDictionaryRightLimit(value, absJump)) (value + absJump - dictionary.size) else (value + absJump)
    }

    private fun reachDictionaryRightLimit(value: Int, absJump: Int) = value + absJump > dictionary.size

    internal fun getNumberOfUnstressedVowels(phrase: String, dictionary: Map<Char, Int> = encryptedDictionary): Int {
        val vocalsOfDictionary = getVocalsOf(dictionary)
        var vowels = 0

        for (letter in phrase.toLowerCase()) {
            if (vocalsOfDictionary.contains(letter)) {
                vowels++
            }
        }

        return vowels
    }

    private fun getVocalsOf(dictionary: Map<Char, Int>): Map<Char, Int> {
        return dictionary.filterValues { isVowel(it)}
    }

    private fun isVowel(it: Int): Boolean {
        // a = 1 e = 5 i = 9 o = 15 u = 21
        when (it) {
            1, 5, 9, 15, 21 -> return true
            else -> return false
        }
    }
}


// TODO: Como no los estamos enviando no hago el bucle que hace que pare si pone "FIN" como dice el problema
fun encryptedMessages(message: String): Int {
    val cypher = message[0]
    val msg = message.drop(1)

    val cesarDictionary = Dictionary(encryptionKey = cypher)
    return cesarDictionary.getNumberOfUnstressedVowels(msg)
}
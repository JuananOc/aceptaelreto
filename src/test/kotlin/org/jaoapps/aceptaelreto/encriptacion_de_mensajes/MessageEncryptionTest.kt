package org.jaoapps.aceptaelreto.encriptacion_de_mensajes

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MessageEncryptionTest {

    // TODO: Por qué si tiene el lateinit no puedo ponerla como val?
    private lateinit var dictionary: Map<Char, Int>

    @Before
    fun setUp() {
        // TODO: ¿ñ? ¿ll?
        this.dictionary = mapOf(
                'a' to 1 , 'b' to 2 , 'c' to 3 , 'd' to 4 , 'e' to 5 , 'f' to 6 , 'g' to 7 , 'h' to 8 , 'i' to 9 , 'j' to 10 ,
                'k' to 11 , 'l' to 12 , 'm' to 13 , 'n' to 14 , 'o' to 15 , 'p' to 16 , 'q' to 17 , 'r' to 18 , 's' to 19 , 't' to 20 ,
                'u' to 21 , 'v' to 22 , 'w' to 23 , 'x' to 24 , 'y' to 25 , 'z' to 26)
    }

    @Test
    fun create_normal_dictionary() {
        Assert.assertEquals(Dictionary().createDictionary(),  dictionary)
    }

    @Test
    fun create_cesar_dictionary_with_positive_jump() {
        // given
        val expectedDictionary = mapOf(
                'a' to 26, 'b' to 1, 'c' to 2, 'd' to 3, 'e' to 4, 'f' to 5, 'g' to 6, 'h' to 7, 'i' to 8, 'j' to 9,
                'k' to 10, 'l' to 11, 'm' to 12, 'n' to 13, 'o' to 14, 'p' to 15, 'q' to 16, 'r' to 17, 's' to 18, 't' to 19,
                'u' to 20, 'v' to 21, 'w' to 22, 'x' to 23, 'y' to 24, 'z' to 25
        )

        // when
        val encryptedDictionary = Dictionary(encryptionKey = 'q').encryptedDictionary

        // then
        Assert.assertEquals(expectedDictionary, encryptedDictionary)
    }
    @Test
    fun create_cesar_dictionary_with_negative_jump() {
        // given
        val expectedDictionary = mapOf(
                'a' to 2, 'b' to 3, 'c' to 4, 'd' to 5, 'e' to 6, 'f' to 7, 'g' to 8, 'h' to 9, 'i' to 10, 'j' to 11,
                'k' to 12, 'l' to 13, 'm' to 14, 'n' to 15, 'o' to 16, 'p' to 17, 'q' to 18, 'r' to 19, 's' to 20, 't' to 21,
                'u' to 22, 'v' to 23, 'w' to 24, 'x' to 25, 'y' to 26, 'z' to 1
        )

        // when
        val encryptedDictionary = Dictionary(encryptionKey = 'o').encryptedDictionary

        // then
        Assert.assertEquals(expectedDictionary, encryptedDictionary)
    }

    @Test
    fun number_of_unstressed_vowels_with_default_dictionary() {
        // given
        val phrase = "aeiouAEIOU"

        // when
        val vowels = Dictionary().getNumberOfUnstressedVowels(phrase, dictionary)

        // then
        Assert.assertEquals(10, vowels)
    }

    @Test
    fun number_of_unstressed_vowels_with_dictionary_cipher_with_positive_jump() {
        // given
        val phrase = "bfjpvBFJPV"
        val dictionaryCypherWithQ = mapOf(
                'a' to 26, 'b' to 1, 'c' to 2, 'd' to 3, 'e' to 4, 'f' to 5, 'g' to 6, 'h' to 7, 'i' to 8, 'j' to 9,
                'k' to 10, 'l' to 11, 'm' to 12, 'n' to 13, 'o' to 14, 'p' to 15, 'q' to 16, 'r' to 17, 's' to 18, 't' to 19,
                'u' to 20, 'v' to 21, 'w' to 22, 'x' to 23, 'y' to 24, 'z' to 25
        )

        // when
        val vowels = Dictionary().getNumberOfUnstressedVowels(phrase, dictionaryCypherWithQ)

        // then
        Assert.assertEquals(10, vowels)
    }

    @Test
    fun number_of_unstressed_vowels_with_dictionary_cipher_with_negative_jump() {
        // given
        val phrase = "zdhntZDHNT"
        val dictionaryCypherWithO = mapOf(
                'a' to 2, 'b' to 3, 'c' to 4, 'd' to 5, 'e' to 6, 'f' to 7, 'g' to 8, 'h' to 9, 'i' to 10, 'j' to 11,
                'k' to 12, 'l' to 13, 'm' to 14, 'n' to 15, 'o' to 16, 'p' to 17, 'q' to 18, 'r' to 19, 's' to 20, 't' to 21,
                'u' to 22, 'v' to 23, 'w' to 24, 'x' to 25, 'y' to 26, 'z' to 1
        )

        // when
        val vowels = Dictionary().getNumberOfUnstressedVowels(phrase, dictionaryCypherWithO)

        // then
        Assert.assertEquals(10, vowels)
    }

    @Test
    fun encrypted_messages() {
        Assert.assertEquals(12, encryptedMessages("pEsta cadena esta sin codificar"))
        Assert.assertEquals(1, encryptedMessages("pfin"))
        Assert.assertEquals(10, encryptedMessages("qbfjpvBFJPV"))
        Assert.assertEquals(10, encryptedMessages("ozdhntZDHNT"))
        Assert.assertEquals(4, encryptedMessages("xXzwoziui-Um"))
        Assert.assertEquals(1, encryptedMessages("qGJO"))
    }

}
package org.jaoapps.aceptaelreto.cuadrados_diabolicos_y_esotericos

import org.junit.Assert.*
import org.junit.Test

class DevilAndEsotericSquareTest {

    @Test
    fun create_square() {
        val square = Square(2, "1 2 3 4")
        val expectedSquare = listOf(listOf(1, 2), listOf(3, 4))

        assertEquals(square.dimension, 2)
        assertEquals(square.square, expectedSquare)  // TODO: sobreescribir el equals del square para que sea como el equals de List<>

        val square2 = Square(3, "1 2 3 4 5 6 7 8 9")
        val expectedSquare2 = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))

        assertEquals(square2.dimension, 3)
        assertEquals(square2.square, expectedSquare2)
    }

    @Test
    fun is_diabolic() {
        val diabolicSquare = Square(3, "28 21 26 23 25 27 24 29 22")
        assertTrue(diabolicSquare.isDiabolic())

        val notDiabolic = Square(2, "1 2 3 4")
        assertFalse(notDiabolic.isDiabolic())
    }

    @Test
    fun is_esoteric() {
        val notDiabolic = Square(2, "1 2 3 4")
        assertFalse(notDiabolic.isEsoteric())

        val diabolicNotEsoteric = Square(3, "28 21 26 23 25 27 24 29 22")
        assertFalse(diabolicNotEsoteric.isEsoteric())

        val oddEsoteric = Square(3, "4 9 2 3 5 7 8 1 6")
        assertTrue(oddEsoteric.isEsoteric())

        val evenEsoteric = Square(8, "1 63 62 4 5 59 58 8 56 10 11 53 52 14 15 49 48 18 19 45 44 22 23 41 25 39 38 28 29 35 34 32 33 31 30 36 37 27 26 40 24 42 43 21 20 46 47 17 16 50 51 13 12 54 55 9 57 7 6 60 61 3 2 64")
        assertTrue(evenEsoteric.isEsoteric())
    }

    @Test
    fun diabolic_and_esoteric_square() {
        val esoteric = Square(3, "4 9 2 3 5 7 8 1 6")
        assertEquals(esoteric.isDevilEsotericSquare(), "ESOTERICO")

        val nothing = Square(2, "1 2 3 4")
        assertEquals(nothing.isDevilEsotericSquare(), "NO")

        val anotherEsoteric = Square(4, "16 3 2 13 5 10 11 8 9 6 7 12 4 15 14 1")
        assertEquals(anotherEsoteric.isDevilEsotericSquare(), "ESOTERICO")

        val diabolic = Square(3, "28 21 26 23 25 27 24 29 22")
        assertEquals(diabolic.isDevilEsotericSquare(), "DIABOLICO")

        val anotherNothing = Square(3, "2 8 1 6 3 5 7 4 9")
        assertEquals(anotherNothing.isDevilEsotericSquare(), "NO")
    }

}



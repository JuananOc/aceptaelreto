package org.jaoapps.aceptaelreto.cuadrados_diabolicos_y_esotericos

import org.junit.Assert
import org.junit.Test

class DevilAndEsotericSquareTest {

    @Test
    fun create_square() {
        val square = Square(2, "1 2 3 4")
        val expectedSquare = listOf(listOf(1, 2), listOf(3, 4))

        Assert.assertEquals(square.size, 2)
        Assert.assertEquals(square.square, expectedSquare)  // TODO: sobreescribir el equals del square para que sea como el equals de List<>

        val square2 = Square(3, "1 2 3 4 5 6 7 8 9")
        val expectedSquare2 = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))

        Assert.assertEquals(square2.size, 3)
        Assert.assertEquals(square2.square, expectedSquare2)
    }

    @Test
    fun is_diabolic() {
        val diabolicSquare = Square(listOf(listOf(28, 21, 26), listOf(23, 25, 27), listOf(24, 29, 22)), "28 21 26 23 25 27 24 29 22")
        Assert.assertTrue(diabolicSquare.isDiabolic())

        val not_diabolic = Square(listOf(listOf(1, 2), listOf(3, 4)), "1 2 3 4")
        Assert.assertFalse(not_diabolic.isDiabolic())
    }

    @Test
    fun is_esoteric() {
        val not_diabolic = Square(listOf(listOf(1, 2), listOf(3, 4)), "1 2 3 4")
        Assert.assertFalse(not_diabolic.isEsoteric())

        val diabolicNotEsoteric = Square(listOf(listOf(28, 21, 26), listOf(23, 25, 27), listOf(24, 29, 22)), "28 21 26 23 25 27 24 29 22")
        Assert.assertFalse(diabolicNotEsoteric.isEsoteric())

        val oddEsoteric = Square(listOf(listOf(4, 9, 2), listOf(3, 5, 7), listOf(8, 1, 6)), "4 9 2 3 5 7 8 1 6")
        Assert.assertTrue(oddEsoteric.isEsoteric())

        val evenEsoteric = Square(listOf(
                listOf(1, 63, 62, 4, 5, 59, 58, 8),
                listOf(56, 10, 11, 53, 52, 14, 15, 49),
                listOf(48, 18, 19, 45, 44, 22, 23, 41),
                listOf(25, 39, 38, 28, 29, 35, 34, 32),
                listOf(33, 31, 30, 36, 37, 27, 26, 40),
                listOf(24, 42, 43, 21, 20, 46, 47, 17),
                listOf(16, 50, 51, 13, 12, 54, 55, 9),
                listOf(57, 7, 6, 60, 61, 3, 2, 64)), "1 63 62 4 5 59 58 8 56 10 11 53 52 14 15 49 48 18 19 45 44 22 23 41 25 39 38 28 29 35 34 32 33 31 30 36 37 27 26 40 24 42 43 21 20 46 47 17 16 50 51 13 12 54 55 9 57 7 6 60 61 3 2 64")
        Assert.assertTrue(evenEsoteric.isEsoteric())
    }

    @Test
    fun diabolic_and_esoteric_square() {
        val esoteric = Square(3, "4 9 2 3 5 7 8 1 6")
        Assert.assertEquals(esoteric.isDevilEsotericSquare(), "ESOTERICO")

        val nothing  = Square(2, "1 2 3 4")
        Assert.assertEquals(nothing.isDevilEsotericSquare(), "NO")

        val anotherEsoteric = Square(4, "16 3 2 13 5 10 11 8 9 6 7 12 4 15 14 1")
        Assert.assertEquals(anotherEsoteric.isDevilEsotericSquare(), "ESOTERICO")

        val diabolic = Square(3, "28 21 26 23 25 27 24 29 22")
        Assert.assertEquals(diabolic.isDevilEsotericSquare(), "DIABOLICO")

        val anotherNothing = Square(3, "2 8 1 6 3 5 7 4 9")
        Assert.assertEquals(anotherNothing.isDevilEsotericSquare(), "NO")
    }

}



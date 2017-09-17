package org.jaoapps.aceptaelreto.kaprekar

import org.junit.Assert.assertEquals
import org.junit.Test

class KaprekarTest {

    @Test
    fun is_kaprecar() {
        assertEquals(kaprekarIterations("6174"), 1)
    }

    @Test
    fun is_repdigit() {
        assertEquals(8, kaprekarIterations("1111"))
        assertEquals(8, kaprekarIterations("2222"))
        assertEquals(8, kaprekarIterations("3333"))
        assertEquals(8, kaprekarIterations("4444"))
        assertEquals(8, kaprekarIterations("5555"))
        assertEquals(8, kaprekarIterations("6666"))
        assertEquals(8, kaprekarIterations("7777"))
        assertEquals(8, kaprekarIterations("8888"))
    }

    @Test
    fun calculate_iterations() {
        assertEquals(5, kaprekarIterations("1121"))
        assertEquals(7, kaprekarIterations("1893"))
        assertEquals(3, kaprekarIterations("3524"))
    }

}
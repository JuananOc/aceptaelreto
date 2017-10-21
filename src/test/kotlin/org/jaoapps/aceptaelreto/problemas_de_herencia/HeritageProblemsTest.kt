package org.jaoapps.aceptaelreto.problemas_de_herencia

import org.junit.Assert
import org.junit.Test

class HeritageProblemsTest {

    @Test
    fun get0GradePolynomial() {
        // When
        val first = isTheCastFair(0, listOf(1), 1)
        val second = isTheCastFair(0, listOf(0), 1)
        val third = isTheCastFair(0, listOf(-1), 1)
        val fourth = isTheCastFair(0, listOf(2), 1)

        // Then
        Assert.assertEquals("CAIN", first)
        Assert.assertEquals("ABEL", second)
        Assert.assertEquals("JUSTO", third)
        Assert.assertEquals("JUSTO", fourth)
    }

    @Test
    fun get1GradePolynomial() {
        val first = isTheCastFair(1, listOf(1, 0), 10)
        Assert.assertEquals("ABEL", first)

        val second = isTheCastFair(1, listOf(3, -1), 10000)
        Assert.assertEquals("JUSTO", second)

        val third = isTheCastFair(1, listOf(3, -1), 2)
        Assert.assertEquals("ABEL", third)
    }

    @Test
    fun get3GradePolynomial() {
        val first = isTheCastFair(3, listOf(1, 2, -1, 0), 1000)
        Assert.assertEquals("ABEL", first)

        val second = isTheCastFair(3, listOf(3, 2, -1, 1), 1000)
        Assert.assertEquals("CAIN", second)
    }
}

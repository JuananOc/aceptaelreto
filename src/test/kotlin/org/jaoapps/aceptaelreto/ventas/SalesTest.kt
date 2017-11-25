package org.jaoapps.aceptaelreto.ventas

import org.junit.Assert
import org.junit.Test

class SalesTest {

    @Test
    fun week_with_maximum_minimum_and_sunday_sales_over_average() {
        // When
        val result = SalesCalculator(listOf(185.80, 250.36, 163.45, 535.20, 950.22, 450.38)).calc()

        Assert.assertEquals("SABADO JUEVES SI", result)
    }

    @Test
    fun week_with_maximum_minimum_and_sunday_sales_below_average() {
        // When
        val result = SalesCalculator(listOf(185.80, 250.36, 163.45, 535.20, 950.22, 450.38)).calc()

        Assert.assertEquals("SABADO JUEVES SI", result)
    }

    @Test
    fun week_with_draw_minimum_and_sunday_sales_over_average() {
        // When
        val result = SalesCalculator(listOf(1.0, 1.0, 1.5, 1.6, 1.7, 2.0)).calc()

        Assert.assertEquals("DOMINGO EMPATE SI", result)
    }

    @Test
    fun week_with_draw_minimum_and_sunday_sales_below_average() {
        // When
        val result = SalesCalculator(listOf(1.0, 1.0, 1.5, 1.6, 1.7, 1.2)).calc()

        Assert.assertEquals("SABADO EMPATE NO", result)
    }

    @Test
    fun week_with_draw_maximum_and_sunday_sales_over_average() {
        // When
        val result = SalesCalculator(listOf(1.0, 1.5, 1.5, 1.5, 2.0, 2.0)).calc()

        Assert.assertEquals("EMPATE MARTES SI", result)
    }

    @Test
    fun week_with_draw_maximum_and_sunday_sales_below_average() {
        // When
        val result = SalesCalculator(listOf(1.0, 1.5, 1.5, 2.0, 2.0, 1.2)).calc()

        Assert.assertEquals("EMPATE MARTES NO", result)
    }

    @Test
    fun week_with_draw_max_and_minimum_and_sunday_sales_over_average() {
        // When
        val result = SalesCalculator(listOf(1.0, 1.0, 1.5, 1.5, 2.0, 2.0)).calc()

        Assert.assertEquals("EMPATE EMPATE SI", result)
    }

    @Test
    fun week_with_draw_max_and_minimum_and_sunday_sales_below_average() {
        // When
        val result = SalesCalculator(listOf(1.0, 1.5, 1.5, 2.0, 2.0, 1.0)).calc()

        Assert.assertEquals("EMPATE EMPATE NO", result)
    }

}

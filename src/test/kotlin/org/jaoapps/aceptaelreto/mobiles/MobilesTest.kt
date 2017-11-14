package org.jaoapps.aceptaelreto.mobiles

import org.junit.Assert.assertEquals
import org.junit.Test

class MobilesTest {

    @Test
    fun is_balanced() {
        assertEquals("SI", calculateBalance(listOf(1, 1, 1, 1)))
    }

    @Test
    fun not_balanced() {
        assertEquals("NO", calculateBalance(listOf(1, 2, 1, 1)))
    }

    @Test
    fun balanced_with_left_sub_balance() {
        assertEquals("SI", calculateBalance(listOf(5, 1, 0, 1, 1, 4, 4, 1)))
    }

    @Test
    fun not_balanced_with_left_sub_balance() {
        assertEquals("NO", calculateBalance(listOf(0, 2, 1, 4, 1, 4, 4, 1)))
    }

    @Test
    fun balancedWithRightSubBalance() {
        assertEquals("SI", calculateBalance(listOf(0, 1, 1, 5, 1, 4, 4, 1)))
    }

    @Test
    fun exampleOfAER() {
        assertEquals("SI", calculateBalance(listOf(0, 2, 0, 4, 0, 3, 0, 1, 1, 1, 1, 1, 2, 4, 4, 2, 1, 6, 3, 2)))
        assertEquals("NO", calculateBalance(listOf(0, 1, 3, 4, 2, 3, 3, 2)))
    }

}

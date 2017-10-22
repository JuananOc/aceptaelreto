package org.jaoapps.aceptaelreto.mobiles

class Mobile(private val weightAndDistances: List<Int>, private var index: Int = 0) {

    fun areBalanced(): Boolean {
        val actualWeightAndDistance = getActualWeightAndDistance()
        val pi = actualWeightAndDistance[0]
        val di = actualWeightAndDistance[1]
        val pd = actualWeightAndDistance[2]
        val dd = actualWeightAndDistance[3]

        return calculateWeight(pi) * di == calculateWeight(pd) * dd
    }

    private fun getActualWeightAndDistance(): List<Int> {
        val from = 4 * index
        val to = from + 4
        return weightAndDistances.subList(from, to)
    }

    private fun calculateWeight(weight: Int): Int {
        if (weight != 0) return weight

        index += 1
        val actualWeightAndDistance = getActualWeightAndDistance()
        val pi = calculateWeight(actualWeightAndDistance[0])
        val di = actualWeightAndDistance[1]
        val pd = calculateWeight(actualWeightAndDistance[2])
        val dd = actualWeightAndDistance[3]

        if (pi * di == pd * dd) {
            return pi + pd
        }

        return weight
    }

}

fun calculateBalance(weightAndDistances: List<Int>): String {
    return if (Mobile(weightAndDistances).areBalanced()) "SI" else "NO"
}


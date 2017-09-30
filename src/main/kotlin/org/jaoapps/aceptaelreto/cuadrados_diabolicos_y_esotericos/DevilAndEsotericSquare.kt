package org.jaoapps.aceptaelreto.cuadrados_diabolicos_y_esotericos

import java.util.*


class Square(val dimension: Int, celdas: String) {

    val square: List<List<Int>>
    private val squareLimit: Int = dimension - 1
    private val magicConstant: Int
    private val magicConstant2: Int

    init {
        val cells = celdas.split(" ").toIntList()

        this.square = ArrayList(dimension)

        for ((index, _) in cells.withIndex()) {
            if (hasToCreateNewRow(index)) {
                square.add(cells.subList(index, index + dimension))
            }
        }

        this.magicConstant = square[0].sum()
        this.magicConstant2 = 4 * magicConstant / dimension
    }

    private fun List<String>.toIntList() = this.map { it.toInt() }

    private fun hasToCreateNewRow(cellIndex: Int) = cellIndex % dimension == 0

    fun isDiabolic() = rowsAreEqualsToMagicConstant() && columsAreEqualsToMagicConstant() && diagonalsAreEqualsToMagicConstant()

    private fun diagonalsAreEqualsToMagicConstant() = leftToRightDiagonalIsEqualsToMagicConstant() && rightToLeftIsEqualsToMagicConstant()

    private fun rowsAreEqualsToMagicConstant() = square.all { it.sum() == magicConstant }

    private fun columsAreEqualsToMagicConstant() = (0 until dimension).all { square[it].sum() == magicConstant }

    private fun leftToRightDiagonalIsEqualsToMagicConstant() = (0 until dimension).sumBy { square[it][it] } == magicConstant

    private fun rightToLeftIsEqualsToMagicConstant() = (dimension - 1 downTo 0).sumBy { square[it][it] } == magicConstant

    fun isEsoteric(): Boolean {
        if (!isDiabolic()) return false
        if (!containsAllNaturalNumber()) return false
        if (!sumOfCornersAreEqualsToConstantMagic2()) return false

        if (isEven()) {
            val centralRightIndex = dimension / 2
            val centralLeftIndex = centralRightIndex - 1

            if (!sumOfLateralFieldsAreEqualsToDoubleOfMagicConstant2(centralRightIndex, centralLeftIndex)) return false
            if (!sumCentralFieldsAreEqualsToMagicConstant2(centralRightIndex, centralLeftIndex)) return false

        } else {
            val centralIndex = dimension / 2
            if (!sumLateralFieldsAreEqualsToMagicConstant2(centralIndex)) return false
            if (!centralFieldMultipliedBy4AreEqualsToMagicConstant2(centralIndex)) return false
        }

        return true
    }

    private fun containsAllNaturalNumber(): Boolean {
        val naturalNumbers = (1..dimension * dimension).toList()

        var total = emptyList<Int>()

        for (list in square) {
            total += list
        }

        return total.sorted().equals(naturalNumbers)
    }

    private fun sumOfCornersAreEqualsToConstantMagic2(): Boolean {
        val sumSquareCorner = square[0][0] + square[0][squareLimit] + square[squareLimit][0] + square[squareLimit][squareLimit]
        if (sumSquareCorner == magicConstant2) return true

        return false
    }

    private fun isEven() = dimension % 2 == 0

    private fun sumOfLateralFieldsAreEqualsToDoubleOfMagicConstant2(centralRightIndex: Int, centralLeftIndex: Int): Boolean {
        val sumLateralFields = square[0][centralRightIndex] + square[0][centralLeftIndex] +
                square[centralRightIndex][0] + square[centralLeftIndex][0] +
                square[centralRightIndex][squareLimit] + square[centralLeftIndex][squareLimit] +
                square[squareLimit][centralRightIndex] + square[squareLimit][centralLeftIndex]
        if (sumLateralFields == 2 * magicConstant2) return true
        return false
    }

    private fun sumCentralFieldsAreEqualsToMagicConstant2(centralRightIndex: Int, centralLeftIndex: Int): Boolean {
        val centralFields = square[centralRightIndex][centralRightIndex] +
                square[centralLeftIndex][centralRightIndex] +
                square[centralRightIndex][centralLeftIndex] +
                square[centralLeftIndex][centralLeftIndex]
        if (centralFields == magicConstant2) return true
        return false
    }

    private fun sumLateralFieldsAreEqualsToMagicConstant2(centralIndex: Int): Boolean {
        val sumCentralFields = square[0][centralIndex] + square[centralIndex][0] + square[centralIndex][squareLimit] + square[squareLimit][centralIndex]
        if (sumCentralFields == magicConstant2) return true
        return false
    }

    private fun centralFieldMultipliedBy4AreEqualsToMagicConstant2(centralIndex: Int): Boolean {
        val centralField = square[centralIndex][centralIndex]
        if (centralField * 4 == magicConstant2) return true
        return false
    }

    fun isDevilEsotericSquare(): String {
        if (isEsoteric()) return "ESOTERICO"
        if (isDiabolic()) return "DIABOLICO"
        return "NO"
    }
}

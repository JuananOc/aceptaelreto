package org.jaoapps.aceptaelreto.cuadrados_diabolicos_y_esotericos


class Square {

    // TODO: Hacer privadas con getter
    public val size: Int
    public val data: List<Int>
    public val square: List<List<Int>>
    public val magicConstant: Int
    public val magicConstant2: Int
    private val squareLimit: Int
        get() {
            val squareLimit = size - 1
            return squareLimit
        }

    constructor(size: Int, data: String) {
        this.size = size
        this.data = getDataSet(data)
        this.square = createSquare()
        this.magicConstant = this.square[0].sum()
        this.magicConstant2 = 4 * magicConstant / size
    }

    // TODO: no pasar el data
    internal constructor(square: List<List<Int>>, data: String) {
        this.square = square
        this.size = square.size
        this.data = getDataSet(data)
        this.magicConstant = this.square[0].    sum()
        this.magicConstant2 = 4 * magicConstant / size
    }

    private fun getDataSet(data: String): ArrayList<Int> {
        val listSplitedBySpaces = data.split(" ")
        return listSplitedBySpaces.toIntList()
    }

    private fun createSquare(): List<List<Int>> {
        val square = ArrayList<ArrayList<Int>>(size)

        for (squareField in this.data) {
            val fieldIndex = this.data.indexOf(squareField)

            if (hasToCreateNewRow(fieldIndex)) {
                square.add(createRow(fieldIndex))
            }
        }
        return square
    }

    private fun hasToCreateNewRow(fieldIndex: Int) = fieldIndex % size == 0

    private fun createRow(fieldIndex: Int): ArrayList<Int> {
        val row = ArrayList<Int>()

        val squareFileSize = fieldIndex + size
        for (i in fieldIndex until squareFileSize) {
            row.add(this.data[i])
        }

        return row;
    }

    fun isDiabolic(): Boolean {
        if (! rowsAreEqualsToMagicConstant()) return false
        if (! columsAreEqualsToMagicConstant()) return false
        if (! diagonalsAreEqualsToMagicConstant()) return false

        return true;
    }

    private fun diagonalsAreEqualsToMagicConstant() = leftToRightDiagonalIsEqualsToMagicConstant() || rightToLeftIsEqualsToMagicConstant()

    private fun rowsAreEqualsToMagicConstant(): Boolean {
        for (row in square) {
            if (row.sum() == magicConstant) return true
        }
        return false
    }

    private fun columsAreEqualsToMagicConstant(): Boolean {
        for (columnIndex in 0 until square.size) {
            var sum = 0
            for (fileIndex in 0 until square.size) {
                sum += square[columnIndex][fileIndex]
            }
            if (sum == magicConstant) return true
        }
        return false
    }

    private fun leftToRightDiagonalIsEqualsToMagicConstant(): Boolean {
        // calculate diagonal from [0,0] to [size, size]
        var sum = 0
        for (diagonalIndex in 0 until square.size) {
            sum += square[diagonalIndex][diagonalIndex]
        }
        if (sum == magicConstant) return true
        return false
    }

    private fun rightToLeftIsEqualsToMagicConstant(): Boolean {
        var sum = 0
        for (diagonalIndex in (square.size - 1) downTo 0) {
            sum += square[diagonalIndex][diagonalIndex]
        }

        if (sum == magicConstant) return true
        return false
    }

    fun isEsoteric(): Boolean {
        if (! isDiabolic()) return false
        if (! containsAllNaturalNumber()) return false
        if (! sumOfCornersAreEqualsToConstantMagic2()) return false

        if (isEven()) {
            val centralRightIndex = size / 2
            val centralLeftIndex = centralRightIndex - 1

            if (! sumOfLateralFieldsAreEqualsToDoubleOfMagicConstant2(centralRightIndex, centralLeftIndex)) return false
            if (! sumCentralFieldsAreEqualsToMagicConstant2(centralRightIndex, centralLeftIndex)) return false

        } else {
            val centralIndex = size / 2
            if (! sumLateralFieldsAreEqualsToMagicConstant2(centralIndex)) return false
            if (! centralFieldMultipliedBy4AreEqualsToMagicConstant2(centralIndex)) return false
        }

        return true
    }

    private fun containsAllNaturalNumber(): Boolean {
        val naturalNumbers = (1..size * size).toList()
        return data.sorted().equals(naturalNumbers)
    }

    private fun sumOfCornersAreEqualsToConstantMagic2(): Boolean {
        val sumSquareCorner = square[0][0] + square[0][squareLimit] + square[squareLimit][0] + square[squareLimit][squareLimit]
        if (sumSquareCorner == magicConstant2) return true

        return false
    }

    private fun isEven() = size % 2 == 0

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

// TODO: por qué no funciona si pongo <String> después del fun
// private fun <String> List<String>.toIntList(): ArrayList<Int> {
private fun List<String>.toIntList(): ArrayList<Int> {
    val new = ArrayList<Int>()

    for (element in this) {
        new.add(element.toInt())
    }
    return new
}

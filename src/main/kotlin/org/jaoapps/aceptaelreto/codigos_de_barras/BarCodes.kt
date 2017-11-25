package org.jaoapps.aceptaelreto.codigos_de_barras

import org.jaoapps.aceptaelreto.codigos_de_barras.BarCodeTypes.EAN13
import org.jaoapps.aceptaelreto.codigos_de_barras.BarCodeTypes.EAN8

enum class BarCodeTypes {
    EAN8,
    EAN13
}

// TODO: Refactorizar esta clase para no romper open close si hubiera que añadir un nuevo typo de código
class BarCodeValidator(var barCode: String) {
    val type: BarCodeTypes
    val barCodeWithoutControlDigit: String
    val controlDigit: Int

    companion object {
        val EAN8_CODE_LENGTH = 8
        val EAN13_CODE_LENGTH = 13

        val VALID_CONTROL_DIGIT = "SI"
        val INVALID_CONTROL_DIGIT = "NO"
        val COUNTRY_BY_CODE = mapOf(
                0 to "EEUU",
                380 to "Bulgaria",
                50 to "Inglaterra",
                539 to "Irlanda",
                560 to "Portugal",
                70 to "Noruega",
                759 to "Venezuela",
                850 to "Cuba",
                890 to "India"
        )
        val UNKNOWN_COUNTRY = "Desconocido"
    }

    init {
        type = getBarCodeType()
        fillBarCode()
        barCodeWithoutControlDigit = barCode.dropLast(1)  // Last digit is the control digit
        controlDigit = barCode.last().toString().toInt() // Transform to String first because we want the char value, not his specific int.
    }

    private fun getBarCodeType(): BarCodeTypes {
        return when (barCode.length) {
            in (0..8) -> EAN8
            in (9..13) -> EAN13
            else -> {
                throw IllegalArgumentException("Bar code not recognized")
            }
        }
    }

    private fun fillBarCode() {
        when (type) {
            EAN8 -> fillBarCodeWithCerosUntil(EAN8_CODE_LENGTH)
            EAN13 -> fillBarCodeWithCerosUntil(EAN13_CODE_LENGTH)
        }
    }

    private fun fillBarCodeWithCerosUntil(length: Int) {
        for (i in barCode.length until length) {
            barCode = "0${barCode}"
        }
    }

    fun validate(): String {
        return when (type) {
            EAN8 -> if (isControlDigitValid()) VALID_CONTROL_DIGIT else INVALID_CONTROL_DIGIT
            EAN13 -> if (isControlDigitValid()) "${VALID_CONTROL_DIGIT} ${validateCountry()}" else INVALID_CONTROL_DIGIT
        }
    }

    private fun isControlDigitValid(): Boolean {
        return controlDigit == calculateControlDigit(barCodeWithoutControlDigit.reversed())
    }

    private fun calculateControlDigit(barCode: String): Int {
        var controlNumber = 0

        for ((index, value) in barCode.withIndex()) {
            if (index.inc() % 2 == 0) { // index.inc() because it has to start with an even index.
                controlNumber = controlNumber + value.toString().toInt() // Transform to String first because we want the char value, not his specific int.
            } else {
                controlNumber = controlNumber + (value.toString().toInt() * 3)  // Transform to String first because we want the char value, not his specific int.
            }
        }

        return 10 - (controlNumber % 10) // i.e if the control number is 88:  10 - (88%10) -> 10 - 8 -> 2
    }

    private fun validateCountry(): String {
        for (digits in 1..3) {
            if (COUNTRY_BY_CODE.containsKey(barCode.take(digits).toInt())) {
                return COUNTRY_BY_CODE[barCode.take(digits).toInt()].toString()
            }
        }

        return UNKNOWN_COUNTRY
    }
}

fun validate(barCode: String): String {
    return when (barCode) {
        "0" -> ""
        else -> BarCodeValidator(barCode).validate()
    }
}

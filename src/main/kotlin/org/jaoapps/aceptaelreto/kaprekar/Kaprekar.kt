package org.jaoapps.aceptaelreto.kaprekar

private val KAPREKAR_CONSTANT = "6174"

fun kaprekarIterations(number: String): Int {
    return when (number) {
        KAPREKAR_CONSTANT -> 0
        "0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888" -> 8
        else -> executeKaprekarRoutine(number)
    }
}

private fun executeKaprekarRoutine(number: String, iterations: Int = 0): Int {
    val num = completeNumberWithCeros(number)
    var i = iterations + 1

    val ascNumbers = num.toList().sorted()
    val descNumbers = ascNumbers.reversed()
    val result = (descNumbers.joinToString("").toInt() - ascNumbers.joinToString("").toInt()).toString()

    if (result != KAPREKAR_CONSTANT) {
        i = executeKaprekarRoutine(result, i)
    }

    return i
}

private fun completeNumberWithCeros(number: String): String {
    var numberComplete = number
    while (numberComplete.length < 4) {
        numberComplete = "0" + numberComplete
    }
    return numberComplete
}


package org.jaoapps.aceptaelreto.kaprekar

fun kaprekarIterations(number: String): Int {
    when (number) {
        "6174" -> return 1
        "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888" -> return 8
        else -> return executeKaprekarRoutine(number)
    }
}

private fun executeKaprekarRoutine(number: String, iterations: Int = 0): Int {
    val num = completeNumberWithCeros(number)
    var i = increaseIterator(iterations)

    val ascNumbers = num.toList().sorted()
    val descNumbers = ascNumbers.reversed()
    val result = (descNumbers.joinToString("").toInt() - ascNumbers.joinToString("").toInt()).toString()

    if (! isKaprecarNumber(result)) {
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

private fun increaseIterator(i: Int): Int {
    return i + 1
}

private fun isKaprecarNumber(result: String) = result == "6174"

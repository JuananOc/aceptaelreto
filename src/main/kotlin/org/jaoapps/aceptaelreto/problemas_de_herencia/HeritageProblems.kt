package org.jaoapps.aceptaelreto.problemas_de_herencia

import java.lang.Math.abs

fun isTheCastFair(grade: Int, coefficients: List<Int>, rectangles: Int): String {
    when (grade) {
        0 -> return when (coefficients[0]) {
            0 -> "ABEL"
            1 -> "CAIN"
            else -> "JUSTO"
        }
        20 -> return ""
        else -> return calcLandOwner(grade, coefficients, rectangles)
    }
}

private fun calcLandOwner(grade: Int, coefficients: List<Int>, rectangles: Int): String {
    val cain = calcCainArea(rectangles, grade, coefficients)
    val abel = 1.0 - cain

    if (landAreasAreEquals(cain, abel)) {
        return "JUSTO"
    } else {
        return if (cain > abel) "CAIN" else "ABEL"
    }
}

private fun calcCainArea(rectangles: Int, grade: Int, coefficients: List<Int>): Double {
    val base = (1.0 / rectangles.toDouble())
    var area = 0.0

    for (rectangleNumber in 0 until rectangles) {
        val lowerLeftVertex = rectangleNumber.toDouble() / rectangles.toDouble()
        val height = calculateHeight(grade, coefficients, lowerLeftVertex)

        area += (base * height)
    }
    return area
}

private fun calculateHeight(maxGrade: Int, coefficients: List<Int>, base: Double): Double {
    var height = 0.0

    for (coefficient in coefficients) {
        val power = maxGrade - coefficients.indexOf(coefficient)
        height += coefficient * pow(base, power)
    }

//  Mi intento de hacerlo con un reduce. No me devuelve los mismo resultados, pero no se exactamente por qué :(
//    val coefficients = coefficients.map { it.toDouble() }
//    var height = coefficients.reduce { he, coefficient -> coefficient * pow(base, maxGrade - coefficients.indexOf(coefficient)) }

    return limitedByCainAndAbelArea(height)
}

private fun pow(base: Double, power: Int): Double {
    return when (power) {
        0 -> 1.0
        else -> {
            base * pow(base, power.dec())
        }
    }
}

private fun limitedByCainAndAbelArea(h: Double): Double {
    var height = h

    if (height > 1.0) {
        height = 1.0
    } else if (height < 0.0) {
        height = 0.0
    }

    return height
}

private fun landAreasAreEquals(cain: Double, abel: Double) = abs(cain - abel) < 0.001

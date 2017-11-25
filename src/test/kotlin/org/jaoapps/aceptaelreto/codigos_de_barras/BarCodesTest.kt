package org.jaoapps.aceptaelreto.codigos_de_barras

import org.junit.Assert.assertEquals
import org.junit.Test

class BarCodesTest {

    @Test
    fun detect_bar_code_in_EAN8_format() {
        // Given
        val ean8With8Digits = "12345678"
        val ean8WithLessThan8Digits = "12345"

        // When / Then
        assertEquals(BarCodeTypes.EAN8, BarCodeValidator(ean8With8Digits).type)
        assertEquals(BarCodeTypes.EAN8, BarCodeValidator(ean8WithLessThan8Digits).type)
    }

    @Test
    fun detect_bar_code_in_EAN13_format() {
        // Given
        val ean13With13Digits = "0123456789123"
        val ean13WithLessThan13Digits = "123456789"

        // When / Then
        assertEquals(BarCodeTypes.EAN13, BarCodeValidator(ean13With13Digits).type)
        assertEquals(BarCodeTypes.EAN13, BarCodeValidator(ean13WithLessThan13Digits).type)
    }

    @Test
    fun detect_invalid_bar_code_formats() {
        // Given
        val emptyBarCode = ""
        val barCodeWithMoreThan13Digits = "01234567891234"

        assertThatBarCodeIsInvalid(emptyBarCode)
        assertThatBarCodeIsInvalid(barCodeWithMoreThan13Digits)
    }

    private fun assertThatBarCodeIsInvalid(emptyBarCode: String) {
        try {
            // When
            BarCodeValidator(emptyBarCode)
        } catch (e: IllegalArgumentException) {
            // Then
            assertEquals("Bar code not recognized", e.message)
        }
    }

    @Test
    fun fill_with_ceros_EAN_bar_codes_with_less_characters() {
        // Given
        val ean8WithLessThan8Digits = "1"
        val ean13WithLessThan13Digits = "123456789"

        // When / Then
        assertEquals("00000001", BarCodeValidator(ean8WithLessThan8Digits).barCode)
        assertEquals("0000123456789", BarCodeValidator(ean13WithLessThan13Digits).barCode)
    }

    @Test
    fun do_nothing_when_bar_code_is_zero() {
        // When
        val result = validate("0")

        assertEquals("", result)
    }

    @Test
    fun validate_control_digit_from_ean8() {
        // Given / When / Then
        assertEquals("SI", validate("65839522"))
        assertEquals("NO", validate("65839521"))
    }

    @Test
    fun validate_control_digit_from_ean13() {
        // Given / When / Then
        assertEquals("SI Desconocido", validate("8414533043847"))
        assertEquals("SI Inglaterra", validate("5029365779425"))
        assertEquals("NO", validate("5129365779425"))
    }

}

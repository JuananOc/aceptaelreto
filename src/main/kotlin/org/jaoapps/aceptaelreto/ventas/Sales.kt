package org.jaoapps.aceptaelreto.ventas


class SalesCalculator(sales: List<Double>) {
    private val maxSalesDay: String
    private val minSalesDay: String
    private val areSundaySalesOverAverage: String

    init {
        maxSalesDay = getSalesDay(sales, sales.max())
        minSalesDay = getSalesDay(sales, sales.min())
        areSundaySalesOverAverage = if (sales.last() > sales.average()) SUNDAY_SALES_OVER_AVERAGE else SUNDAY_SALES_BELOW_AVERAGE
    }

    private fun getSalesDay(sales: List<Double>, filter: Double?): String {
        val salesFiltered = sales.filter { it == filter }.size
        return if (salesFiltered == 1) WORK_DAYS[sales.indexOf(filter)] else DRAW
    }

    companion object {
        private val WORK_DAYS = listOf("MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO")
        private val SUNDAY_SALES_OVER_AVERAGE = "SI"
        private val SUNDAY_SALES_BELOW_AVERAGE = "NO"
        private val DRAW = "EMPATE"
    }

    fun calc(): String {
        return "$maxSalesDay $minSalesDay $areSundaySalesOverAverage"
    }

}

package com.example.grupochurrasquinhodomanuel.core.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun BigDecimal.toCurrencyString(): String {
    val symbols = DecimalFormatSymbols(Locale("pt", "BR"))
    val formatter = DecimalFormat("¤ #,##0.00", symbols)
    formatter.currency = java.util.Currency.getInstance("BRL")
    return formatter.format(this)
}

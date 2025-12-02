package site.markhenrick.recreational.common

import kotlin.math.ceil
import kotlin.math.log10

infix fun Int.wrapMod(divisor: Int): Int = ((this % divisor) + divisor) % divisor

// i.e. ceil(log10(x+1)), but with integer operations only
fun base10DigitCount(x: Long): Int {
    // TODO integer only
    require(x >= 0)
    return ceil(log10((x + 1).toDouble())).toInt()
}

fun intPow(base: Int, exponent: Int): Long {
    require(base != 0 || exponent != 0)
    require(exponent >= 0)
    var result = 1L
    for (i in 1..exponent) {
        result *= base
    }
    return result
}

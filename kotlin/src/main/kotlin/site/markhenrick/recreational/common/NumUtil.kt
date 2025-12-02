package site.markhenrick.recreational.common

infix fun Int.wrapMod(divisor: Int): Int = ((this % divisor) + divisor) % divisor

// i.e. ceil(log10(x+1)), but with integer operations only
fun base10DigitCount(x: Long): Int {
    // Let's just do it the simple way. No evil bit hacking. Yet
    var count = 0
    var current = x
    while (current > 0) {
        current /= 10
        count++
    }
    return count
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

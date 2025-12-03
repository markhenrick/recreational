package site.markhenrick.recreational.common

// i.e. ceil(log10(x+1)), but with integer operations only
fun base10DigitCount(x: Long): Long {
    // Let's just do it the simple way. No evil bit hacking. Yet
    var count = 0L
    var current = x
    while (current > 0) {
        current /= 10
        count++
    }
    return count
}

// Classic CS 101 O(log n) solution based on b^n = (b^(n/2))^2
fun intPow(base: Long, exponent: Long): Long {
    require(base != 0L || exponent != 0L)
    require(exponent >= 0)
    var result = 1L
    var baseRunning = base
    var expRunning = exponent

    while (expRunning > 0) {
        if (expRunning % 2 == 1L) {
            result *= baseRunning
        }
        baseRunning *= baseRunning
        expRunning /= 2 // Which will be compiled as >>
    }

    return result
}

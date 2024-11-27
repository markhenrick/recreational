package site.markhenrick.recreational.common

infix fun Int.wrapMod(divisor: Int): Int = ((this % divisor) + divisor) % divisor
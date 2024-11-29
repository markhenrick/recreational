package site.markhenrick.recreational.common

import kotlin.math.sqrt

// TODO add tests

// Some of these prioritise mathematical simplicity over allocation efficiency
data class ComplexInt(
    val real: Int,
    val imag: Int,
) {
    operator fun plus(other: ComplexInt): ComplexInt = ComplexInt(
        real + other.real,
        imag + other.imag,
    )
    operator fun plus(other: Int): ComplexInt = this + other.r

    operator fun times(other: ComplexInt): ComplexInt = ComplexInt(
        real * other.real - imag * other.imag,
        real * other.imag + imag * other.real,
    )
    operator fun times(other: Int): ComplexInt = this * other.r

    operator fun minus(other: ComplexInt): ComplexInt = ComplexInt(real - other.real, imag - other.imag)
    operator fun minus(other: Int): ComplexInt = this - other.r

    operator fun unaryMinus(): ComplexInt = -1 * this

    fun conjugate(): ComplexInt = ComplexInt(real, -imag)

    fun modulus(): Double = sqrt((real * real + imag * imag).toDouble())

    override fun toString(): String = "$real+${imag}i"
}

// Arguably a bit confusing, since these don't "get" a part, they convert an int, but it lets us write like paper maths
val Int.r: ComplexInt
    get() = ComplexInt(this, 0)
val Int.i: ComplexInt
    get() = ComplexInt(0, this)
infix fun Int.i(imag: Int) = ComplexInt(this, imag)

operator fun Int.plus(other: ComplexInt) = other + this
operator fun Int.times(other: ComplexInt) = other * this
operator fun Int.minus(other: ComplexInt) = -(other - this)
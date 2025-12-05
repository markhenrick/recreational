package site.markhenrick.recreational.common

fun Char.digitToInt(): Int {
    require(this in '0'..'9')
    return this - '0'
}

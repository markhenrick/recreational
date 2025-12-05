package site.markhenrick.recreational.common

fun List<Int>.deltas(): Sequence<Int> = this.asSequence()
    .zip(this.asSequence().drop(1))
    .map { (a, b) -> a - b }

// O(n) check. Useful for assertions
inline fun <T, R : Comparable<R>> List<T>.isSortedBy(crossinline selector: (T) -> R): Boolean {
    for (i in 0..<this.lastIndex) {
        if (selector(this[i]) >= selector(this[i + 1])) {
            return false
        }
    }
    return true
}

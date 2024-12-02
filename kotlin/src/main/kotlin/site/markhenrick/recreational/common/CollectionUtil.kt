package site.markhenrick.recreational.common

fun List<Int>.deltas(): Sequence<Int> = this.asSequence()
    .zip(this.asSequence().drop(1))
    .map { (a, b) -> a - b }
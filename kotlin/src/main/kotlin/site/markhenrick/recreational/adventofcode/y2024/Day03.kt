package site.markhenrick.recreational.adventofcode.y2024

object Day03 {
    private val mulPattern = Regex("mul\\((\\d+),(\\d+)\\)")
    private val doPattern = Regex("do\\(\\)")
    private val dontPattern = Regex("don't\\(\\)")

    fun solvePart1(input: String) = mulPattern.findAll(input)
        .map { evalMul(it) }
        .sum()

    // Decided a state machine was easier to read for this part
    fun solvePart2(input: String): Int {
        var enabled = true
        var offset = 0
        var sum = 0
        while (offset < input.length) {
            if (enabled) {
                val nextMul = mulPattern.find(input, offset) ?: break
                val nextDont = dontPattern.find(input, offset)
                if (nextDont != null && nextDont.range.last < nextMul.range.last) {
                    enabled = false
                    offset = nextDont.range.last
                } else {
                    sum += evalMul(nextMul)
                    offset = nextMul.range.last
                }
            } else {
                val nextDo = doPattern.find(input, offset) ?: break
                enabled = true
                offset = nextDo.range.last
            }
        }
        return sum
    }

    private fun evalMul(mul: MatchResult): Int {
        assert(mul.groups.size == 2 + 1)
        return mul.groups[1]!!.value.toInt() * mul.groups[2]!!.value.toInt()
    }
}
package mpbostock

object Day02 {
    fun partOne(input: List<RedNoseReactorReport>): Int = input.count { it.isSafe() }

    fun partTwo(input: List<RedNoseReactorReport>): Int = input.count { it.isSafeWithDamping() }

    private val input = FileIO.readInput("day02input.txt") { s -> RedNoseReactorReport.fromInput(s) }

    data class RedNoseReactorReport(val levels: List<Int>) {
        private val diffs: List<Int> by lazy { levels.zipWithNext { a, b -> b - a } }
        private val dampenedReports: List<RedNoseReactorReport> by lazy {
            (0..levels.size).map {
                RedNoseReactorReport(levels.filterIndexed { index, _ -> index != it })
            }
        }

        fun isSafe(): Boolean = diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }

        fun isSafeWithDamping(): Boolean = dampenedReports.any { it.isSafe() }

        companion object {
            fun fromInput(input: String): RedNoseReactorReport =
                RedNoseReactorReport(input.split(" ").map { it.toInt() })
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val partOneSolution = partOne(input)
        println(partOneSolution)

        val partTwoSolution = partTwo(input)
        println(partTwoSolution)
    }
}
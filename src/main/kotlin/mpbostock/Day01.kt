package mpbostock

import kotlin.math.abs

object Day01 {
    fun partOne(input: List<String>): Int {
        return HistoricLocations.fromInput(input).sumOfDistances()
    }

    fun partTwo(input: List<String>): Int {
        return HistoricLocations.fromInput(input).similarityScore()
    }

    private val input = FileIO.readInput("day01input.txt") { s -> s }

    data class HistoricLocations(
        val leftList: List<Int> = emptyList(),
        val rightList: List<Int> = emptyList()
    ) {
        fun sumOfDistances(): Int = leftList.sorted().zip(rightList.sorted()).sumOf { abs(it.first - it.second) }

        fun similarityScore(): Int {
            val leftInRightCounts = leftList.toSet().associate { left -> (left to rightList.count { it == left }) }
            return leftList.sumOf { it * leftInRightCounts[it]!! }
        }

        companion object {
            fun fromInput(input: List<String>): HistoricLocations {
                return input.fold(HistoricLocations()) { acc, row ->
                    val split = row.split("   ")
                    acc.copy(
                        leftList = acc.leftList + split[0].toInt(),
                        rightList = acc.rightList + split[1].toInt()
                    )
                }
            }
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
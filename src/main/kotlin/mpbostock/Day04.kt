package mpbostock

object Day04 {
    private const val XMAS = "XMAS"
    fun partOne(input: List<String>): Int = WordSearch.fromInput(input, startingChar = 'X').findXmas()

    fun partTwo(input: List<String>): Int = WordSearch.fromInput(input, startingChar = 'A').findCrossMas()

    private val input = FileIO.readInput("day04input.txt") { s -> s }

    class WordSearch(grid: Grid<Char>): Grid<Char> by grid {
        fun findXmas(): Int = coordinatesOfInterest.fold(0) { acc, start ->
            acc + Direction.values().map {
                var currentCoord = start
                (1 until XMAS.length).fold("X") { acc, _ ->
                    currentCoord = it.move(currentCoord)
                    acc + getCell(currentCoord)
                }
            }.count { it == XMAS }
        }

        fun findCrossMas(): Int = coordinatesOfInterest.fold(0) { acc, start ->
            val ne = start.move(Direction.NorthEast)
            val nw = start.move(Direction.NorthWest)
            val se = start.move(Direction.SouthEast)
            val sw = start.move(Direction.SouthWest)
            val leftDiagonal = "${nw}A${se}"
            val rightDiagonal = "${ne}A${sw}"
            if (leftDiagonal.validMas() && rightDiagonal.validMas()) acc + 1 else acc
        }

        private fun String.validMas() = this == "MAS" || this == "SAM"

        companion object {
            fun fromInput(input: List<String>, startingChar: Char): WordSearch {
                return WordSearch(Grid.fromInput(input, '.', { c -> c }) { c, _ ->  c == startingChar })
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
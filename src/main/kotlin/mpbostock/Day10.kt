package mpbostock

object Day10 {
    fun partOne(input: List<String>): Int = TopographicMap.fromInput(input).trailHeads.sumOf { it.score }

    fun partTwo(input: List<String>): Int = TopographicMap.fromInput(input).trailHeads.sumOf { it.rating }

    private val input = FileIO.readInput("day10input.txt") { s -> s }

    data class TrailHead(val score: Int, val rating: Int)

    class TopographicMap(grid: Grid<Int>) : Grid<Int> by grid {
        private val startingZeros = coordinatesOfInterest
        private fun hike(start: Coordinate): TrailHead? {
            tailrec fun hike(height: Int, current: List<Coordinate>, visited: List<Coordinate>): TrailHead? {
                val moved = current.flatMap { pos ->
                    possibleDirections
                        .map { it.move(pos) }
                        .filter { getCell(it) == height + 1 }
                }
                return when {
                    height == 9 -> TrailHead(
                        visited.toSet().count { getCell(it) == 9 },
                        visited.count { getCell(it) == 9 }
                    )
                    moved.isEmpty() -> null
                    else -> hike(height + 1, moved, visited + moved)
                }
            }
            return hike(0, listOf(start), listOf(start))
        }

        val trailHeads get() = startingZeros.mapNotNull { hike(it) }

        companion object {
            private val possibleDirections = listOf(Direction.North, Direction.South, Direction.East, Direction.West)
            fun fromInput(input: List<String>): TopographicMap {
                return TopographicMap(
                    Grid.fromInput(
                        input,
                        -1,
                        { c -> c.digitToIntOrNull() ?: -1 },
                        { c, _ -> c == '0' }
                    )
                )
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
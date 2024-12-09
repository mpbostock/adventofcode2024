package mpbostock

object Day06 {
    fun partOne(input: LabMap): Int = input.numStepsToLeaveLab()

    fun partTwo(input: LabMap): Int = input.numInfiniteLoops()

    private val input = LabMap.fromInput(FileIO.readInput("day06input.txt") { s -> s })

    class LabMap(grid: Grid<Char>) : Grid<Char> by grid {
        private val startingGuard = coordinatesOfInterest.single() to Direction.North
        private val outsideMap = defaultCell
        private val guardPatrol: Set<Pair<Coordinate, Direction>> by lazy {
            patrol(setOf(startingGuard), startingGuard)
        }

        private tailrec fun patrol(
            visited: Set<Pair<Coordinate, Direction>>,
            guard: Pair<Coordinate, Direction>,
            turnCondition: (Coordinate) -> Boolean = { it.isObstacle() }
        ): Set<Pair<Coordinate, Direction>> {
            if (getCell(guard.first) == outsideMap) return visited
            val forward = guard.forward()
            val updatedGuard = when (turnCondition(forward.first)) {
                true -> guard.turnRight()
                else -> forward
            }
            if (updatedGuard in visited) return emptySet()
            return patrol(
                if (getCell(forward.first) != outsideMap) visited + updatedGuard else visited,
                updatedGuard,
                turnCondition
            )
        }

        fun numStepsToLeaveLab(): Int = guardPatrol.distinctBy { it.first }.size

        fun numInfiniteLoops(): Int {
            val simulatedObstaclePositions = guardPatrol.filter { it != startingGuard }.map { it.first }.distinct().asSequence()
            return simulatedObstaclePositions.fold(emptySet<Coordinate>()) { acc, curr ->
                when {
                    acc.contains(curr) -> acc
                    else -> {
                        val simulatedPatrol = patrol(setOf(startingGuard), startingGuard, { pos ->
                            pos.isObstacle() || pos == curr
                        })
                        if (simulatedPatrol.isEmpty()) acc + curr else acc
                    }
                }
            }.size
        }

        private fun Coordinate.isObstacle(): Boolean = getCell(this) == '#'
        private fun Pair<Coordinate, Direction>.forward(): Pair<Coordinate, Direction> = second.move(first) to second
        private fun Pair<Coordinate, Direction>.turnRight(): Pair<Coordinate, Direction> = first to second.turnRight()

        companion object {
            fun fromInput(input: List<String>): LabMap {
                return LabMap(Grid.fromInput(input, 'X', { c -> c }) { c, _ -> c == '^' })
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
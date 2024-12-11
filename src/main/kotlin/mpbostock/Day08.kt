package mpbostock

object Day08 {
    fun partOne(input: List<String>): Int = AntennaMap.fromInput(input).uniqueAntinodes(ResonantFrequenciesModel).size

    fun partTwo(input: List<String>): Int = AntennaMap.fromInput(input).uniqueAntinodes(ResonantHarmonicsModel).size

    private val input = FileIO.readInput("day08input.txt") { s -> s }

    fun interface ResonantModel {
        fun calculateAntinodes(lines: List<Line>, positionValid: (Coordinate) -> Boolean): Set<Coordinate>
        fun Pair<Coordinate?, Coordinate?>.resonate(
            deltaXDeltaY: Coordinate,
            positionValid: (Coordinate) -> Boolean
        ): Pair<Coordinate?, Coordinate?> =
            Pair(
                first?.minus(deltaXDeltaY).validated(positionValid),
                second?.plus(deltaXDeltaY).validated(positionValid)
            )

        fun Pair<Coordinate?, Coordinate?>.toSet(): Set<Coordinate> = toList().filterNotNull().toSet()
        fun Coordinate?.validated(positionValid: (Coordinate) -> Boolean): Coordinate? =
            this?.takeIf { positionValid(it) }
    }

    object ResonantFrequenciesModel : ResonantModel {
        override fun calculateAntinodes(lines: List<Line>, positionValid: (Coordinate) -> Boolean): Set<Coordinate> =
            lines.flatMap {
                (it.start to it.end).resonate(it.deltaXDeltaY(), positionValid).toSet()
            }.toSet()
    }

    object ResonantHarmonicsModel : ResonantModel {
        private tailrec fun resonate(
            antiNodes: Set<Coordinate>,
            currentLine: Pair<Coordinate?, Coordinate?>,
            deltaXDeltaY: Coordinate,
            positionValid: (Coordinate) -> Boolean
        ): Set<Coordinate> {
            val newLine = currentLine.resonate(deltaXDeltaY, positionValid)
            if (newLine.first == null && newLine.second == null) return antiNodes
            return resonate(antiNodes + newLine.toSet(), newLine, deltaXDeltaY, positionValid)
        }

        override fun calculateAntinodes(lines: List<Line>, positionValid: (Coordinate) -> Boolean): Set<Coordinate> =
            lines.flatMap {
                resonate(setOf(it.start, it.end), (it.start to it.end), it.deltaXDeltaY(), positionValid)
            }.toSet()
    }

    data class Antenna(val frequency: Char, val positions: List<Coordinate>) {
        fun antinodes(model: ResonantModel, positionValid: (Coordinate) -> Boolean): Set<Coordinate> =
            model.calculateAntinodes(positions.toLines(), positionValid)
    }

    class AntennaMap(grid: Grid<Char>) : Grid<Char> by grid {
        private val antennas
            get() = coordinatesOfInterest.groupBy { getCell(it) }.map { Antenna(it.key, it.value) }

        fun uniqueAntinodes(model: ResonantModel): Set<Coordinate> = antennas
            .flatMap { antenna -> antenna.antinodes(model) { getCell(it) != defaultCell } }
            .toSet()

        companion object {
            fun fromInput(input: List<String>): AntennaMap =
                AntennaMap(Grid.fromInput(input, ' ', { c -> c }) { c, _ -> c != '.' })
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
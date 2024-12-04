package mpbostock

interface Grid<T> {
    val cells: Array<Array<PositionedCell<T>>>
    val defaultCell: T
    val width: Int
        get() = cells[0].size
    val height: Int
        get() = cells.size
    val coordinates: Set<Coordinate>
    val coordinatesOfInterest: Set<Coordinate>
    fun getCell(pos: Coordinate) = if (validPos(pos)) cells[pos.y][pos.x].cell else defaultCell
    private fun validPos(pos: Coordinate) = pos.x in 0 until width && pos.y in 0 until height
    fun Coordinate.move(mover: PositionMover) = getCell(mover.move(this))

    companion object {
        inline fun <reified T> fromInput(
            input: List<String>,
            defaultCell: T,
            crossinline charMapper: (Char) -> T,
            crossinline isCellInteresting: (Char, Coordinate) -> Boolean = { _, _ -> false }
        ): Grid<T> {
            val coordinatesOfInterest = mutableSetOf<Coordinate>()
            val cells  = input.mapIndexed { y, line -> line.mapIndexed { x, c ->
                val pos = Coordinate(x, y)
                if (isCellInteresting(c, pos)) {
                    coordinatesOfInterest.add(pos)
                }
                PositionedCell(pos, charMapper(c))
            }.toTypedArray() }.toTypedArray()
            return object: Grid<T> {
                override val cells: Array<Array<PositionedCell<T>>>
                    get() = cells
                override val defaultCell: T
                    get() = defaultCell
                override val coordinates: Set<Coordinate>
                    get() = cells.flatMap { row -> row.map { it.pos } }.toSet()
                override val coordinatesOfInterest: Set<Coordinate>
                    get() = coordinatesOfInterest

            }
        }
    }
}

data class Coordinate(val x: Int, val y: Int)
data class PositionedCell<T>(val pos: Coordinate, val cell: T)
fun interface PositionMover {
    fun move(pos: Coordinate): Coordinate
}

enum class Direction: PositionMover {
    North, NorthEast, East, SouthEast, South, SouthWest, West, NorthWest;
    override fun move(pos: Coordinate): Coordinate {
        return when(this) {
            North -> pos.copy(y = pos.y - 1)
            NorthEast -> pos.copy(x = pos.x + 1, y = pos.y - 1)
            East -> pos.copy(x = pos.x + 1)
            SouthEast -> pos.copy(x = pos.x + 1, y = pos.y + 1)
            South -> pos.copy(y = pos.y + 1)
            SouthWest -> pos.copy(x = pos.x - 1, y = pos.y + 1)
            West -> pos.copy(x = pos.x - 1)
            NorthWest -> pos.copy(x = pos.x - 1, y = pos.y - 1)
        }
    }
}
package mpbostock

import mpbostock.Day06.LabMap
import mpbostock.Day06.partOne
import mpbostock.Day06.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day06Test {
    private val testData = LabMap.fromInput(listOf(
        "....#.....",
        ".........#",
        "..........",
        "..#.......",
        ".......#..",
        "..........",
        ".#..^.....",
        "........#.",
        "#.........",
        "......#..."
    ))

    @Test
    fun `test part one`() {
        assertEquals(41, partOne(testData))
    }

    @Test
    fun `test part two`() {
        assertEquals(6, partTwo(testData))
    }

}
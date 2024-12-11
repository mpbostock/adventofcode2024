package mpbostock

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Day08Test {
    private val testData = listOf(
       "............",
       "........0...",
       ".....0......",
       ".......0....",
       "....0.......",
       "......A.....",
       "............",
       "............",
       "........A...",
       ".........A..",
       "............",
       "............",
    )
    private val oneFrequencyOneAntenna = listOf(
        "..........",
        "..........",
        "..........",
        "....a.....",
        "..........",
        "..........",
        "..........",
        "..........",
        "..........",
        "..........",
    )
    private val oneFrequencyTwoAntennas = listOf(
        "..........",
        "..........",
        "..........",
        "....a.....",
        "..........",
        ".....a....",
        "..........",
        "..........",
        "..........",
        "..........",
    )
    private val oneFrequencyThreeAntennas = listOf(
        "..........",
        "..........",
        "..........",
        "....a.....",
        "........a.",
        ".....a....",
        "..........",
        "..........",
        "..........",
        "..........",
    )

    @Test
    fun `one frequency and one antenna gives no antinodes`() {
        val antennaMap = Day08.AntennaMap.fromInput(oneFrequencyOneAntenna)
        val antinodes = antennaMap.uniqueAntinodes(Day08.ResonantFrequenciesModel)
        assertEquals(0, antinodes.size)
    }

    @Test
    fun `one frequency and two antennas gives two antinodes`() {
        val antennaMap = Day08.AntennaMap.fromInput(oneFrequencyTwoAntennas)
        val antinodes = antennaMap.uniqueAntinodes(Day08.ResonantFrequenciesModel)
        assertEquals(2, antinodes.size)
        assertTrue(antinodes.contains(Coordinate(3, 1)))
        assertTrue(antinodes.contains(Coordinate(6, 7)))
    }

    @Test
    fun `one frequency and three antennas gives four antinodes`() {
        val antennaMap = Day08.AntennaMap.fromInput(oneFrequencyThreeAntennas)
        val antinodes = antennaMap.uniqueAntinodes(Day08.ResonantFrequenciesModel)
        assertEquals(4, antinodes.size)
        assertTrue(antinodes.contains(Coordinate(3, 1)))
        assertTrue(antinodes.contains(Coordinate(6, 7)))
        assertTrue(antinodes.contains(Coordinate(0, 2)))
        assertTrue(antinodes.contains(Coordinate(2, 6)))
    }

    @Test
    fun `test part one`() {
        assertEquals(14, Day08.partOne(testData))
    }

    @Test
    fun `test part two`() {
        assertEquals(34, Day08.partTwo(testData))
    }

}
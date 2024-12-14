package mpbostock

import mpbostock.Day01.HistoricLocations
import mpbostock.Day01.partOne
import mpbostock.Day01.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day01Test {
  private val testData = listOf(
    "3   4",
    "4   3",
    "2   5",
    "1   3",
    "3   9",
    "3   3"
  )

  @Test
  fun `historic locations are parsed correctly`() {
    val locations = HistoricLocations.fromInput(testData)
    assertEquals(listOf(3, 4, 2, 1, 3, 3), locations.leftList)
    assertEquals(listOf(4, 3, 5, 3, 9, 3), locations.rightList)
  }

  @Test
  fun `test part one`() {
    val partOne = partOne(testData)
    assertEquals(11, partOne)
  }

  @Test
  fun `test part two`() {
    val partTwo = partTwo(testData)
    assertEquals(31, partTwo)
  }

}
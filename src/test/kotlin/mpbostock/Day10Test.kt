package mpbostock


import mpbostock.Day10.TopographicMap
import mpbostock.Day10.partOne
import mpbostock.Day10.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day10Test {
  private val simpleExample = listOf(
    "0123",
    "1234",
    "8765",
    "9876",
  )

  private val twoTrailHeads = listOf(
    "...0...",
    "...1...",
    "...2...",
    "6543456",
    "7.....7",
    "8.....8",
    "9.....9",
  )

  private val testData = listOf(
    "89010123",
    "78121874",
    "87430965",
    "96549874",
    "45678903",
    "32019012",
    "01329801",
    "10456732",
  )

  private val thirteenRating = listOf(
    "..90..9",
    "...1.98",
    "...2..7",
    "6543456",
    "765.987",
    "876....",
    "987....",
  )

  @Test
  fun `walking from 0 gives one 9`() {
    assertEquals(1, TopographicMap.fromInput(simpleExample).trailHeads[0].score)
  }

  @Test
  fun `two trailheads gives score of two`() {
    assertEquals(2, TopographicMap.fromInput(twoTrailHeads).trailHeads[0].score)
  }

  @Test
  fun `test data has 9 trail heads`() {
    assertEquals(9, TopographicMap.fromInput(testData).trailHeads.size)
  }

  @Test
  fun `rating of 13`() {
    assertEquals(13, TopographicMap.fromInput(thirteenRating).trailHeads[0].rating)
  }

  @Test
  fun `test part one`() {
    assertEquals(36, partOne(testData))
  }

  @Test
  fun `test part two`() {
    assertEquals(81, partTwo(testData))
  }

}
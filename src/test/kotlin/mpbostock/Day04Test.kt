package mpbostock

import mpbostock.Day04.WordSearch
import mpbostock.Day04.partOne
import mpbostock.Day04.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day04Test {
  private val testData = listOf(
    "..X...",
    ".SAMX.",
    ".A..A.",
    "XMAS.S",
    ".X...."
  )
  private val fullTestData = listOf(
    "MMMSXXMASM",
    "MSAMXMSMSA",
    "AMXSXMAAMM",
    "MSAMASMSMX",
    "XMASAMXAMM",
    "XXAMMXXAMA",
    "SMSMSASXSS",
    "SAXAMASAAA",
    "MAMMMXMMMM",
    "MXMXAXMASX"
  )
  private val testNorth = listOf(
    "S",
    "A",
    "M",
    "X"
  )
  private val testSouth = listOf(
    "X",
    "M",
    "A",
    "S"
  )
  private val testEast = listOf(
    "XMAS"
  )
  private val testWest = listOf(
    "SAMX"
  )
  private val testNorthEast = listOf(
    "...S",
    "..A.",
    ".M..",
    "X..."
  )
  private val testSouthEast = listOf(
    "X...",
    ".M..",
    "..A.",
    "...S"
  )
  private val testNorthWest = listOf(
    "S...",
    ".A..",
    "..M.",
    "...X"
  )
  private val testSouthWest = listOf(
    "...X",
    "..M.",
    ".A..",
    "S..."
  )

  private val testCrossMas = listOf(
    "M.S",
    ".A.",
    "M.S"
  )

  private val testMultipleCrossMas = listOf(
    ".M.S......",
    "..A..MSMS.",
    ".M.S.MAA..",
    "..A.ASMSM.",
    ".M.S.M....",
    "..........",
    "S.S.S.S.S.",
    ".A.A.A.A..",
    "M.M.M.M.M.",
    ".........."
  )

  @Test
  fun `north example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testNorth, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `east example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testEast, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `south example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testSouth, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `west example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testWest, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `north east example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testNorthEast, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `south east example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testSouthEast, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `north west example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testNorthWest, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `south west example gives finds one XMAS`() {
    val wordSearch = WordSearch.fromInput(testSouthWest, startingChar = 'X')
    assertEquals(1, wordSearch.findXmas())
  }

  @Test
  fun `combined example gives finds four XMAS`() {
    val wordSearch = WordSearch.fromInput(testData, startingChar = 'X')
    assertEquals(4, wordSearch.findXmas())
  }

  @Test
  fun `cross mas example finds one cross MAS`() {
    val wordSearch = WordSearch.fromInput(testCrossMas, startingChar = 'A')
    assertEquals(1, wordSearch.findCrossMas())
  }

  @Test
  fun `cross mas example finds nine cross MAS`() {
    val wordSearch = WordSearch.fromInput(testMultipleCrossMas, startingChar = 'A')
    assertEquals(9, wordSearch.findCrossMas())
  }

  @Test
  fun `test part one`() {
    assertEquals(18, partOne(fullTestData))
  }

  @Test
  fun `test part two`() {
    assertEquals(9, partTwo(testMultipleCrossMas))
  }

}
package mpbostock


import mpbostock.Day11.PlutonianPebbles
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day11Test {
  private val testData = listOf("125 17")

  @Test
  fun `numDigits in long`() {
    val zero = 0L
    val one = 1L
    val hundred = 100L
    val thousand = 1_000L
    val million = 1_000_000L
    assertEquals(1, zero.numDigits())
    assertEquals(1, one.numDigits())
    assertEquals(3, hundred.numDigits())
    assertEquals(4, thousand.numDigits())
    assertEquals(7, million.numDigits())
  }

  @Test
  fun `long split in half`() {
    val thousand = 1_000L
    val million = 10_001_000L
    val random = 1234_5678_9012L
    assertEquals(listOf(10L, 0L), thousand.splitInHalf())
    assertEquals(listOf(1000L, 1000L), million.splitInHalf())
    assertEquals(listOf(123456L, 789012L), random.splitInHalf())
  }

  @Test
  fun `blink returns pebble multiplied by 2024`() {
    assertEquals(listOf(2024L), PlutonianPebbles.blink(1L))
  }

  @Test
  fun `blink returns 1 if pebble is 0`() {
    assertEquals(listOf(1L), PlutonianPebbles.blink(0L))
  }

  @Test
  fun `blink splits pebble if has even digits`() {
    assertEquals(listOf(1000L, 1000L), PlutonianPebbles.blink(10_001_000L))
  }

  @Test
  fun `number of pebbles after one blink is one`() {
    assertEquals(1, PlutonianPebbles.pebbleCounter(125L, 1))
    assertEquals(1, PlutonianPebbles.pebbleCounter(0L, 1))
  }

  @Test
  fun `number of pebbles after one blink is two if split`() {
    assertEquals(2, PlutonianPebbles.pebbleCounter(17L, 1))
    assertEquals(2, PlutonianPebbles.pebbleCounter(10_001_000L, 1))
  }

  @Test
  fun `number of pebbles after each blink is correct for test data`() {
    val expectedNumberOfPebbles = mapOf(
      1 to 3L,
      2 to 4L,
      3 to 5L,
      4 to 9L,
      5 to 13L,
      6 to 22L
    )
    expectedNumberOfPebbles.forEach { (numBlinks, expectedNumPebbles) ->
      assertEquals(expectedNumPebbles, PlutonianPebbles.fromInput(testData).blink(numBlinks))
    }
  }

  @Test
  fun `test part one`() {
    assertEquals(55312L, Day11.partOne(testData))
  }
}
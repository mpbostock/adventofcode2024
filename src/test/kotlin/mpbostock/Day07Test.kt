package mpbostock

import mpbostock.Day07.Equation
import mpbostock.Day07.Operator
import mpbostock.Day07.Operator.Add
import mpbostock.Day07.Operator.Multiply
import mpbostock.Day07.partOne
import mpbostock.Day07.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day07Test {
  private val testData = listOf(
    "190: 10 19",
    "3267: 81 40 27",
    "83: 17 5",
    "156: 15 6",
    "7290: 6 8 6 15",
    "161011: 16 10 13",
    "192: 17 8 14",
    "21037: 9 7 18 13",
    "292: 11 6 16 20",
  )

  @Test
  fun `operator combinations for 1 slot`() {
    assertEquals(
      listOf(
        listOf(Add),
        listOf(Multiply)
      ),
      Operator.combinations(1)
    )
  }

  @Test
  fun `operator combinations for 2 slots`() {
    assertEquals(
      listOf(
        listOf(Add, Add),
        listOf(Add, Multiply),
        listOf(Multiply, Add),
        listOf(Multiply, Multiply)
      ),
      Operator.combinations(2)
    )
  }

  @Test
  fun `operator combinations for 3 slots`() {

    assertEquals(
      listOf(
        listOf(Add, Add, Add),
        listOf(Add, Add, Multiply),
        listOf(Add, Multiply, Add),
        listOf(Add, Multiply, Multiply),
        listOf(Multiply, Add, Add),
        listOf(Multiply, Add, Multiply),
        listOf(Multiply, Multiply, Add),
        listOf(Multiply, Multiply, Multiply)
      ), Operator.combinations(3)
    )
  }

  @Test
  fun `equation can be true`() {
    assertTrue(Equation(190, listOf(10, 19)).canBeTrue())
  }

  @Test
  fun `equation cant be true`() {
    assertFalse(Equation(156, listOf(15, 6)).canBeTrue())
    assertFalse(Equation(7290, listOf(6, 8, 6, 15)).canBeTrue())
  }

  @Test
  fun `equation can be true with concatenation`() {
    assertTrue(Equation(156, listOf(15, 6)).canBeTrue(withConcatenation = true))
    assertTrue(Equation(7290, listOf(6, 8, 6, 15)).canBeTrue(withConcatenation = true))
  }

  @Test
  fun `equation cant be true with concatenation`() {
    assertFalse(Equation(83, listOf(17, 5)).canBeTrue(withConcatenation = true))
    assertFalse(Equation(161011, listOf(16, 10, 13)).canBeTrue(withConcatenation = true))
  }

  @Test
  fun `test part one`() {
    assertEquals(3749, partOne(testData))
  }

  @Test
  fun `test part two`() {
    assertEquals(11387, partTwo(testData))
  }

}
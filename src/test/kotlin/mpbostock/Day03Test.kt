package mpbostock

import mpbostock.Day03.MulInstruction
import mpbostock.Day03.partOne
import mpbostock.Day03.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day03Test {
    private val testData = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    private val testDataMultiple = listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)", "+mul(32,64]then(mul(11,8)mul(8,5))")
    private val testDataDoDont = listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64]", "(mul(11,8)undo()?mul(8,5))")

    @Test
    fun `mul instructions are parsed correctly`() {
        val expectedMulInstructions = listOf(
            MulInstruction(2, 4),
            MulInstruction(5, 5),
            MulInstruction(11, 8),
            MulInstruction(8, 5),
        )
        assertEquals(expectedMulInstructions, MulInstruction.fromInput(listOf(testData)))
    }
    @Test
    fun `mul instructions are parsed correctly multiple lines`() {
        val expectedMulInstructions = listOf(
            MulInstruction(2, 4),
            MulInstruction(5, 5),
            MulInstruction(11, 8),
            MulInstruction(8, 5),
        )
        assertEquals(expectedMulInstructions, MulInstruction.fromInput(testDataMultiple))
    }
    @Test
    fun `mul instructions are parsed correctly from do dont`() {
        val expectedMulInstructions = listOf(
            MulInstruction(2, 4),
            MulInstruction(5, 5, enabled = false),
            MulInstruction(11, 8, enabled = false),
            MulInstruction(8, 5),
        )
        assertEquals(expectedMulInstructions, MulInstruction.fromInput(testDataDoDont))
    }

    @Test
    fun `test part one`() {
        assertEquals(161, partOne(MulInstruction.fromInput(listOf(testData))))
    }

    @Test
    fun `test part two`() {
        assertEquals(48, partTwo(MulInstruction.fromInput(testDataDoDont)))
    }

}
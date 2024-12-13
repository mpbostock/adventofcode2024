package mpbostock


import mpbostock.Day09.File
import mpbostock.Day09.FreeSpace
import mpbostock.Day09.WholeFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Day09Test {
    private val testData = listOf("2333133121414131402")

    @Test
    fun `whole file is filled fully with space`() {
        val wholeFile = WholeFile(1, arrayOf(File(1), File(1)))
        assertTrue(arrayOf(FreeSpace, FreeSpace) contentEquals wholeFile.fillWithSpace().blocks)
    }

    @Test
    fun `space is partially filled with file`() {
        val freeSpace = Day09.ContiguousFreeSpace(arrayOf(FreeSpace, FreeSpace, FreeSpace))
        val wholeFile = WholeFile(1, arrayOf(File(1), File(1)))

        assertTrue(arrayOf(File(1), File(1), FreeSpace) contentEquals freeSpace.fillWithFile(wholeFile).blocks)
    }

    @Test
    fun `remaining space is filled with file`() {
        val freeSpace = Day09.ContiguousFreeSpace(arrayOf(File(1), File(1), FreeSpace))
        val wholeFile = WholeFile(2, arrayOf(File(2)))

        assertTrue(arrayOf(File(1), File(1), File(2)) contentEquals freeSpace.fillWithFile(wholeFile).blocks)
    }

    @Test
    fun `test part one`() {
        assertEquals(1928, Day09.partOne(testData))
    }

    @Test
    fun `test part two`() {
        assertEquals(2858, Day09.partTwo(testData))
    }

}
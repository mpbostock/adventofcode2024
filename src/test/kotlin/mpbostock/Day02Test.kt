package mpbostock

import mpbostock.Day02.RedNoseReactorReport
import mpbostock.Day02.partOne
import mpbostock.Day02.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day02Test {
    private val testData = listOf(
        "7 6 4 2 1",
        "1 2 7 8 9",
        "9 7 6 2 1",
        "1 3 2 4 5",
        "8 6 4 4 1",
        "1 3 6 7 9",
    )

    @Test
    fun `red nose reactor reports are parsed correctly`() {
        val expectedReports = listOf(
            RedNoseReactorReport(listOf(7, 6, 4, 2, 1)),
            RedNoseReactorReport(listOf(1, 2, 7, 8, 9)),
            RedNoseReactorReport(listOf(9, 7, 6, 2, 1)),
            RedNoseReactorReport(listOf(1, 3, 2, 4, 5)),
            RedNoseReactorReport(listOf(8, 6, 4, 4, 1)),
            RedNoseReactorReport(listOf(1, 3, 6, 7, 9))
        )
        assertEquals(expectedReports, testData.map { RedNoseReactorReport.fromInput(it) })
    }

    @Test
    fun `red nose reactor reports are safe`() {
        val expectedReports = listOf(
            RedNoseReactorReport(listOf(7, 6, 4, 2, 1)),
            RedNoseReactorReport(listOf(1, 3, 6, 7, 9))
        )
        for (report in expectedReports) {
            assertTrue(report.isSafe())
        }
    }

    @Test
    fun `red nose reactor reports are unsafe`() {
        val expectedReports = listOf(
            RedNoseReactorReport(listOf(1, 2, 7, 8, 9)),
            RedNoseReactorReport(listOf(9, 7, 6, 2, 1)),
            RedNoseReactorReport(listOf(1, 3, 2, 4, 5)),
            RedNoseReactorReport(listOf(8, 6, 4, 4, 1))
        )
        for (report in expectedReports) {
            assertFalse(report.isSafe())
        }
    }

    @Test
    fun `red nose reactor reports are safe with damping`() {
        val expectedReports = listOf(
            RedNoseReactorReport(listOf(7, 6, 4, 2, 1)),
            RedNoseReactorReport(listOf(1, 3, 6, 7, 9)),
            RedNoseReactorReport(listOf(1, 3, 2, 4, 5)),
            RedNoseReactorReport(listOf(8, 6, 4, 4, 1))
        )
        for (report in expectedReports) {
            assertTrue(report.isSafeWithDamping())
        }
    }

    @Test
    fun `red nose reactor reports are unsafe with damping`() {
        val expectedReports = listOf(
            RedNoseReactorReport(listOf(1, 2, 7, 8, 9)),
            RedNoseReactorReport(listOf(9, 7, 6, 2, 1)),
        )
        for (report in expectedReports) {
            assertFalse(report.isSafeWithDamping())
        }
    }

    @Test
    fun `test part one`() {
        assertEquals(2, partOne(testData.map { RedNoseReactorReport.fromInput(it) }))
    }

    @Test
    fun `test part two`() {
        assertEquals(4, partTwo(testData.map { RedNoseReactorReport.fromInput(it) }))
    }

}
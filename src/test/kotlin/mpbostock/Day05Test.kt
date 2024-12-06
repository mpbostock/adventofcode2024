package mpbostock

import mpbostock.Day05.PrintQueue
import mpbostock.Day05.partOne
import mpbostock.Day05.partTwo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day05Test {
    private val testData = listOf(
        "47|53",
        "97|13",
        "97|61",
        "97|47",
        "75|29",
        "61|13",
        "75|53",
        "29|13",
        "97|29",
        "53|29",
        "61|53",
        "97|53",
        "61|29",
        "47|13",
        "75|47",
        "97|75",
        "47|61",
        "75|61",
        "47|29",
        "75|13",
        "53|13",
        "",
        "75,47,61,53,29",
        "97,61,53,29,13",
        "75,29,13",
        "75,97,47,61,53",
        "61,13,29",
        "97,13,75,29,47",
    )

    @Test
    fun `correct middle pages are returned`() {
        val printQueue = PrintQueue.fromInput(testData)
        assertEquals(listOf(61, 53, 29), printQueue.correctUpdatesMiddlePages())
    }

    @Test
    fun `correct fixed middle pages are returned`() {
        val printQueue = PrintQueue.fromInput(testData)
        assertEquals(listOf(47, 29, 47), printQueue.incorrectUpdatesFixedMiddlePages())
    }

    @Test
    fun `update order is fixed`() {
        val printQueue = PrintQueue.fromInput(testData)
        val fixed1 = printQueue.pageUpdates[3].fixed
        val fixed2 = printQueue.pageUpdates[4].fixed
        val fixed3 = printQueue.pageUpdates[5].fixed
        assertEquals(listOf(97,75,47,61,53), fixed1.pagesToProduce)
        assertEquals(listOf(61,29,13), fixed2.pagesToProduce)
        assertEquals(listOf(97,75,47,29,13), fixed3.pagesToProduce)
    }

    @Test
    fun `test part one`() {
        assertEquals(143, partOne(PrintQueue.fromInput(testData)))
    }

    @Test
    fun `test part two`() {
        assertEquals(123, partTwo(PrintQueue.fromInput(testData)))
    }

}
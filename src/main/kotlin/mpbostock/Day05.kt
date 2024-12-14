package mpbostock

object Day05 {
  fun partOne(input: PrintQueue): Int = input.correctUpdatesMiddlePages().sum()

  fun partTwo(input: PrintQueue): Int = input.incorrectUpdatesFixedMiddlePages().sum()

  private val input = PrintQueue.fromInput(FileIO.readInput("day05input.txt") { s -> s })

  data class PageOrderingRule(val before: Int, val after: Int) {
    companion object {
      fun fromInput(input: List<String>): List<PageOrderingRule> = input.map {
        val split = it.split("|")
        PageOrderingRule(split[0].toInt(), split[1].toInt())
      }
    }
  }

  data class Update(
    val pagesToProduce: List<Int>,
    private val afterMap: Map<Int, List<Int>>
  ) {
    val matchesExpectedOrder: Boolean
      get() = expectedOrder == pagesToProduce

    private val expectedOrder: List<Int> by lazy {
      pagesToProduce
        .map { (it to afterMap.getOrDefault(it, emptyList()).intersect(pagesToProduce.toSet())) }
        .sortedByDescending { it.second.size }
        .map { it.first }
    }

    val fixed: Update
      get() = copy(pagesToProduce = expectedOrder)

    val middlePage: Int get() = pagesToProduce[pagesToProduce.size / 2]

    companion object {
      fun fromInput(
        input: List<String>,
        afterMap: Map<Int, List<Int>>
      ): List<Update> = input.map { row ->
        Update(row.split(",").map { it.toInt() }, afterMap)
      }
    }
  }

  data class PrintQueue(val pageUpdates: List<Update>) {
    fun correctUpdatesMiddlePages(): List<Int> =
      pageUpdates.filter { update -> update.matchesExpectedOrder }.map { it.middlePage }

    fun incorrectUpdatesFixedMiddlePages(): List<Int> =
      pageUpdates.filter { update -> !update.matchesExpectedOrder }.map { it.fixed.middlePage }

    companion object {
      fun fromInput(input: List<String>): PrintQueue {
        val split = input.joinToString("\n").split("\n\n")
        val rules = PageOrderingRule.fromInput(split[0].split("\n"))
        val afterMap = rules.groupBy { it.before }.mapValues { before -> before.value.map { it.after } }
        return PrintQueue(Update.fromInput(split[1].split("\n"), afterMap))
      }
    }
  }

  @JvmStatic
  fun main(args: Array<String>) {
    val partOneSolution = partOne(input)
    println(partOneSolution)

    val partTwoSolution = partTwo(input)
    println(partTwoSolution)
  }
}
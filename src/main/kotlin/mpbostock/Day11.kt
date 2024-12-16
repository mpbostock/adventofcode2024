package mpbostock

object Day11 {
  fun partOne(input: List<String>): Long = PlutonianPebbles.fromInput(input).blink(25)

  fun partTwo(input: List<String>): Long = PlutonianPebbles.fromInput(input).blink(75)

  private val input = FileIO.readInput("day11input.txt") { s -> s }

  class PlutonianPebbles(private val startingPebbles: List<Long>) {
    fun blink(n: Int): Long = startingPebbles.sumOf { pebbleCounter(it, n) }

    companion object {
      fun fromInput(input: List<String>): PlutonianPebbles {
        return PlutonianPebbles(input[0].split(' ').map { it.toLong() })
      }

      val blink: (pebble: Long) -> List<Long> = run {
        val cache = mutableMapOf<Long, List<Long>>()
        return@run { pebble: Long ->
          cache.getOrPut(pebble) {
            when {
              pebble == 0L -> listOf(1L)
              pebble.numDigits() % 2 == 0 -> pebble.splitInHalf()
              else -> listOf(pebble * 2024L)
            }
          }
        }
      }

      val pebbleCounter: (pebble: Long, numBlinks: Int) -> Long = run {
        val cache = mutableMapOf<Pair<Long, Int>, Long>()
        return@run { pebble, numBlinks ->
          cache.getOrPut(pebble to numBlinks) {
            when {
              numBlinks == 0 -> 1L
              else -> blink(pebble).sumOf { pebbleCounter(it, numBlinks - 1) }
            }
          }
        }
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
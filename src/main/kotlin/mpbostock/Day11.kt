package mpbostock

object Day11 {
  fun partOne(input: List<String>): Long = PlutonianPebbles.fromInput(input).blink(25)

  fun partTwo(input: List<String>): Long = PlutonianPebbles.fromInput(input).blink(75)

  private val input = FileIO.readInput("day11input.txt") { s -> s }

  class PlutonianPebbles(private val startingPebbles: List<Long>) {
    fun blink(n: Int): Long = startingPebbles.sumOf { stoneCounter(it, n) }

    companion object {
      fun fromInput(input: List<String>): PlutonianPebbles {
        return PlutonianPebbles(input[0].split(' ').map { it.toLong() })
      }

      val blink: (stone: Long) -> List<Long> = run {
        val cache = mutableMapOf<Long, List<Long>>()
        return@run { stone: Long ->
          cache.getOrPut(stone) {
            when {
              stone == 0L -> listOf(1L)
              stone.numDigits() % 2 == 0 -> stone.splitInHalf()
              else -> listOf(stone * 2024L)
            }
          }
        }
      }

      val stoneCounter: (stone: Long, numBlinks: Int) -> Long = run {
        val cache = mutableMapOf<Pair<Long, Int>, Long>()
        return@run { stone, numBlinks ->
          cache.getOrPut(stone to numBlinks) {
            when {
              numBlinks == 0 -> 1L
              else -> blink(stone).sumOf { stoneCounter(it, numBlinks - 1) }
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
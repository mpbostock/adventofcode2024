package mpbostock

import java.util.function.BinaryOperator

object Day07 {
  fun partOne(input: List<String>): Long =
    Equation.fromInput(input).filter { it.canBeTrue() }.sumOf { it.result }

  fun partTwo(input: List<String>): Long =
    Equation.fromInput(input).filter { it.canBeTrue(withConcatenation = true) }.sumOf { it.result }

  private val input = FileIO.readInput("day07input.txt") { s -> s }

  enum class Operator(private val operator: BinaryOperator<Long>) : BinaryOperator<Long> by operator {
    Add({ a, b -> a + b }),
    Multiply({ a, b -> a * b }),
    Concatenate({ a, b -> "$a$b".toLong() });

    companion object {
      private fun operators(withConcatenation: Boolean): Array<Operator> =
        if (withConcatenation) values() else arrayOf(Add, Multiply)

      fun combinations(n: Int, withConcatenation: Boolean = false): List<List<Operator>> =
        (1..n).fold(listOf(listOf())) { acc, _ ->
          acc.flatMap { combination ->
            operators(withConcatenation).map { operator -> combination + operator }
          }
        }
    }
  }

  data class Equation(val result: Long, val values: List<Long>) {
    fun canBeTrue(withConcatenation: Boolean = false): Boolean =
      Operator.combinations(values.size - 1, withConcatenation)
        .map { it.zip(values.drop(1)) }
        .any {
          it.fold(values.first()) { total, (operator, value) ->
            operator.apply(total, value)
          } == result
        }

    companion object {
      fun fromInput(input: List<String>): List<Equation> = input.map { row ->
        val split = row.split(":")
        Equation(split[0].toLong(), split[1].trim().split(" ").map { it.toLong() })
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
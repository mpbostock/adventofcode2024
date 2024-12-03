package mpbostock

object Day03 {
    fun partOne(input: List<MulInstruction>): Int = input.sumOf { it.a * it.b }

    fun partTwo(input: List<MulInstruction>): Int = input.filter { it.enabled }.sumOf { it.a * it.b }

    private val input = MulInstruction.fromInput(FileIO.readInput("day03input.txt") { s -> s })

    data class MulInstruction(val a: Int, val b: Int, val enabled: Boolean = true) {
        companion object {
            private val regex = """(mul\((\d+),(\d+)\)+)|(do\(\))|(don't\(\))""".toRegex()
            fun fromInput(input: List<String>): List<MulInstruction> {
                var enabled = true
                val mulInstructions = mutableListOf<MulInstruction>()
                input.flatMap { regex.findAll(it) }.forEach { match ->
                    when(match.value) {
                        "do()" -> enabled = true
                        "don't()" -> enabled = false
                        else -> mulInstructions.add(
                            MulInstruction(match.groupValues[2].toInt(), match.groupValues[3].toInt(), enabled)
                        )
                    }
                }
                return mulInstructions
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
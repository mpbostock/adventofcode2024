package mpbostock

object Day09 {
  fun partOne(input: List<String>): Long = DiskMap(input[0]).getReorderedChecksum()

  fun partTwo(input: List<String>): Long = DiskMap(input[0]).getReorderedChecksumContiguous()

  private val input = FileIO.readInput("day09input.txt") { s -> s }

  sealed interface Block
  data class File(val id: Int) : Block
  object FreeSpace : Block
  sealed interface Contiguous : Block {
    val blocks: Array<Block>
  }

  class WholeFile(val id: Int, override val blocks: Array<Block>) : Contiguous {
    fun fillWithSpace(): Contiguous {
      for (index in blocks.indices) {
        blocks[index] = FreeSpace
      }
      return this
    }
  }

  class ContiguousFreeSpace(override val blocks: Array<Block>) : Contiguous {
    fun fillWithFile(file: WholeFile): Contiguous {
      val indexOfFirstSpace = blocks.indexOfFirst { it is FreeSpace }
      for (index in file.blocks.indices) {
        blocks[indexOfFirstSpace + index] = file.blocks[index]
      }
      return this
    }
  }

  class DiskMap(private val diskMap: String) {

    private fun getBlocks() = diskMap.foldIndexed((0 to emptyArray<Block>())) { index, acc, c ->
      val amount = c.digitToInt()
      when {
        index.isOdd() -> (acc.first to acc.second + FreeSpace.repeat(amount))
        else -> (acc.first + 1 to acc.second + File(acc.first).repeat(amount))
      }
    }.second

    private fun reorderBlocks(blocks: Array<Block>): Array<Block> {
      tailrec fun reorder(blocks: Array<Block>): Array<Block> {
        val firstSpaceIndex = blocks.indexOfFirst { it is FreeSpace }
        val lastFileIndex = blocks.indexOfLast { it is File }
        if (firstSpaceIndex > lastFileIndex) return blocks
        val file = blocks[lastFileIndex]
        blocks[firstSpaceIndex] = file
        blocks[lastFileIndex] = FreeSpace
        return reorder(blocks)
      }
      return reorder(blocks)
    }

    fun getReorderedChecksum(): Long {
      return getChecksum(reorderBlocks(getBlocks()))
    }

    private fun getContiguous() = diskMap.foldIndexed((0 to emptyArray<Contiguous>())) { index, acc, c ->
      val amount = c.digitToInt()
      when {
        index.isOdd() -> (acc.first to acc.second + ContiguousFreeSpace(FreeSpace.repeat(amount)))
        else -> (acc.first + 1 to acc.second + WholeFile(acc.first, File(acc.first).repeat(amount)))
      }
    }.second

    private fun reorderContiguous(contiguous: Array<Contiguous>): Array<Block> {
      tailrec fun reorder(
        blocks: Array<Contiguous>,
        filesToMoveOrderedByIdDescending: List<WholeFile>
      ): Array<Contiguous> {
        if (filesToMoveOrderedByIdDescending.isEmpty()) return blocks
        val highestIdFile = filesToMoveOrderedByIdDescending.first()
        val highestIdFileIndex = blocks.indexOf(highestIdFile)
        val firstSpaceIndexBigEnoughForFile =
          blocks.indexOfFirst { it.blocks.filterIsInstance<FreeSpace>().size >= highestIdFile.blocks.size }
        if (firstSpaceIndexBigEnoughForFile in 1 until highestIdFileIndex) {
          val space = blocks[firstSpaceIndexBigEnoughForFile] as ContiguousFreeSpace
          blocks[firstSpaceIndexBigEnoughForFile] = space.fillWithFile(highestIdFile)
          blocks[highestIdFileIndex] = highestIdFile.fillWithSpace()
        }
        return reorder(blocks, filesToMoveOrderedByIdDescending.drop(1))
      }
      return reorder(contiguous, contiguous.filterIsInstance<WholeFile>().sortedByDescending { it.id })
        .flatMap { it.blocks.asIterable() }.toTypedArray<Block>()
    }

    fun getReorderedChecksumContiguous(): Long {
      return getChecksum(reorderContiguous(getContiguous()))
    }

    private fun getChecksum(reorderedBlocks: Array<Block>) =
      reorderedBlocks.withIndex().filter { it.value is File }.fold(0L) { acc, indexedBlock ->
        acc + indexedBlock.index * (indexedBlock.value as File).id
      }

    private fun Block.repeat(n: Int) = (1..n).map { this }.toTypedArray()
  }

  @JvmStatic
  fun main(args: Array<String>) {
    val partOneSolution = partOne(input)
    println(partOneSolution)

    val partTwoSolution = partTwo(input)
    println(partTwoSolution)
  }
}
fun main() {
  fun parseInput(input: List<String>): List<Int?> {
    return input.map { s -> s.toIntOrNull() }
  }

  fun part1(input: List<String>): Int {
    var ans = 0
    val parsedInput = parseInput(input)
    for (i in 0 until input.size - 1) {
      if (parsedInput[i + 1]!! > parsedInput[i]!!) {
        ans++
      }
    }
    return ans
  }

  fun part2(input: List<String>): Int {
    if (input.size <= 3) return 0

    val parsedInput = parseInput(input)
    var ans = 0
    var windowSum = 0
    for (i in 0..2) {
      windowSum += parsedInput[i]!!
    }

    for (i in 3 until input.size) {
      val nextWindowSum = windowSum + parsedInput[i]!! - parsedInput[i - 3]!!
      if (nextWindowSum > windowSum) {
        ans++
      }
      windowSum = nextWindowSum
    }
    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day01_test")
  check(part1(testInput) == 7)
  check(part2(testInput) == 5)

  val input = readInput("Day01")
  println(part1(input))
  println(part2(input))
}

fun main() {
  fun intervalLessThan(numbers: List<Int>, interval: Int): Int {
    return numbers.windowed(interval).count{ xs -> xs.first() < xs.last() }
  }

  fun part1(input: List<Int>): Int {
    return intervalLessThan(input, 2)
  }

  fun part2(input: List<Int>): Int {
    return intervalLessThan(input, 4)
  }

  val testInput = readInputLinesAsInts("Day01_test")
  check(part1(testInput) == 7)
  check(part2(testInput) == 5)

  val input = readInputLinesAsInts("Day01")
  println(part1(input))
  println(part2(input))
}

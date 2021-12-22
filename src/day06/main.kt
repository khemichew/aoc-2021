package day06

import readInputLine
import parseLineWithCommaAsInts

fun main() {
  fun lanternFish(input: List<Int>, iterations: Int): Long {
    val count = (0..8).map { input.count { n -> n == it}.toLong() }.toMutableList()
    for (i in 0 until iterations) {
      val create = count.removeFirst()
      count[6] += create
      count.add(create)
    }
    return count.sum()
  }

  fun part1(input: List<Int>): Long = lanternFish(input, 80)

  fun part2(input: List<Int>): Long = lanternFish(input, 256)

  val testInput = readInputLine("day06/test_input")
  val sanitiseTestInput = parseLineWithCommaAsInts(testInput)
  check(part1(sanitiseTestInput) == 5934L)
  check(part2(sanitiseTestInput) == 26984457539)

  val input = readInputLine("day06/input")
  val sanitiseInput = parseLineWithCommaAsInts(input)
  println(part1(sanitiseInput))
  println(part2(sanitiseInput))
}

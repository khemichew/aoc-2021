package day06

import readInputLine

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
  val sanitiseTestInput = testInput.split(",".toRegex()).filterNot(String::isBlank).map(String::toInt)
  check(part1(sanitiseTestInput) == 5934L)
  check(part2(sanitiseTestInput) == 26984457539)

  val input = readInputLine("day06/input")
  val sanitiseInput = input.split(",".toRegex()).filterNot(String::isBlank).map(String::toInt)
  println(part1(sanitiseInput))
  println(part2(sanitiseInput))
}

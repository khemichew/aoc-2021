package day07

import parseLineWithCommaAsInts
import readInputLine
import java.util.*
import kotlin.math.abs

fun main() {
  fun fuel(distance: Int): Int = distance * (distance + 1) / 2

  fun absoluteDistance(x: Int, numbers: SortedMap<Int, Int>): Int {
    return numbers.map { (k, v) -> v * abs(x - k) }.sum()
  }

  fun part1(input: List<Int>): Int {
    val numbers = input.groupBy { it }.mapValues { it.value.size }.toSortedMap()
    var before = 0
    var after = numbers.values.sum()
    var found: Int? = null

    // Find median
    for ((key, value) in numbers) {
      after -= value
      before += value
      if (after - before <= 0) {
        found = key
        break
      }
    }
    return absoluteDistance(found!!, numbers)
  }

  fun part2(input: List<Int>): Int {
    val mean = input.sum() / input.size

    return input.sumOf { fuel(abs(it - mean)) }
      .coerceAtMost(input.sumOf { fuel(abs(it - mean - 1)) })
  }

  val testInput = readInputLine("day07/test_input")
  val sanitiseTestInput = parseLineWithCommaAsInts(testInput)
  check(part1(sanitiseTestInput) == 37)
  check(part2(sanitiseTestInput) == 168)

  val input = readInputLine("day07/input")
  val sanitiseInput = parseLineWithCommaAsInts(input)
  println(part1(sanitiseInput))
  println(part2(sanitiseInput))
}

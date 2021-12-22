package day09

import readInputLines

fun main() {
  fun getHeights(input: List<String>): MutableMap<Pair<Int, Int>, Int> {
    val matrix = input.map { it.toCharArray().map(Character::getNumericValue) }
    return matrix.flatMapIndexed{r, line -> line.mapIndexed{c, value -> Pair(r, c) to value} }.toMap().toMutableMap()
  }

  fun neighbours(p: Pair<Int, Int>, heights: Map<Pair<Int, Int>, Int>): List<Pair<Int, Int>> {
    val row = p.first
    val col = p.second
    val candidates = listOf(Pair(row-1, col), Pair(row, col-1), Pair(row+1, col), Pair(row, col+1))
    return candidates.filter { heights.containsKey(it) }
  }

  fun lowPoints(heights: Map<Pair<Int, Int>, Int>): Map<Pair<Int, Int>, Int> {
    return heights.filterKeys { neighbours(it, heights).all { p -> heights[it]!! < heights[p]!! } }
  }

  fun countBasins(p: Pair<Int, Int>, heights: MutableMap<Pair<Int, Int>, Int>): Int {
    if(heights[p] == 9) return 0
    heights[p] = 9
    return 1 + neighbours(p, heights).sumOf { countBasins(it, heights) }
  }

  fun part1(heights: Map<Pair<Int, Int>, Int>): Int {
    return lowPoints(heights).values.sumOf { it+1 }
  }

  fun part2(heights: MutableMap<Pair<Int, Int>, Int>): Int {
    return lowPoints(heights).keys.map { countBasins(it, heights) }.sorted().takeLast(3).reduce(Int::times)
  }

  val testInput = getHeights(readInputLines("day09/test_input"))
  check(part1(testInput) == 15)
  check(part2(testInput) == 1134)

  val input = getHeights(readInputLines("day09/input"))
  println(part1(input))
  println(part2(input))
}

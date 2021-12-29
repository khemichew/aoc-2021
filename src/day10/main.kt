package day10

import readInputLines

fun main() {
  fun left(): Map<Char, Char> {
    return mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
  }

  fun right(): Map<Char, Char> {
    return mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
  }

  fun illegalScore(): Map<Char, Int> {
    return mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
  }

  fun incompleteScore(): Map<Char, Int> {
    return mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
  }

  fun part1(chunks: List<String>): Int {
    val left = left()
    val right = right()
    val scoreTable = illegalScore()
    val res = mutableMapOf<Char, Int>()

    for (chunk in chunks) {
      val stack = ArrayDeque<Char>()
      for (symbol in chunk) {
        if (symbol in left) {
          stack.addFirst(symbol)
        } else {
          val counterpart = stack.removeFirstOrNull()
          if (counterpart == null || counterpart != right[symbol]) {
            res[symbol] = (res[symbol] ?: 0) + 1
            break
          }
        }
      }
    }
    return res.map { (k, v) -> scoreTable[k]!! * v }.sum()
  }

  fun part2(chunks: List<String>): Long {
    val left = left()
    val right = right()
    val scoreTable = incompleteScore()
    val scores = mutableListOf<Long>()

    for (chunk in chunks) {
      val stack = ArrayDeque<Char>()
      var illegal = false

      for (symbol in chunk) {
        if (symbol in left) {
          stack.addFirst(symbol)
        } else {
          val counterpart = stack.removeFirstOrNull()
          if (counterpart == null || counterpart != right[symbol]) {
            illegal = true
            break
          }
        }
      }

      if (!illegal) {
        var totalPoint: Long = 0
        while (stack.isNotEmpty()) {
          totalPoint *= 5
          totalPoint += scoreTable[left[stack.removeFirst()]]!!
        }
        scores.add(totalPoint)
      }
    }

    return scores.sorted()[scores.size / 2]
  }

  val testInput = readInputLines("day10/test_input")
  check(part1(testInput) == 26397)
  check(part2(testInput) == 288957L)

  val input = readInputLines("day10/input")
  println(part1(input))
  println(part2(input))
}

package day08

import readInputLines

fun main() {
  fun hashValue(s: String): String {
    return s.toCharArray().sorted().joinToString("")
  }

  fun decode(pattern: List<String>): Map<String, Int> {
    // up = 1, topLeft = 2, btmLeft = 3, btm = 4, btmRight = 5, topRight = 6, middle = 7
    val patternToInt = mutableMapOf<String, Int>()
    val charToSegment = mutableMapOf<Int, Char>()

    val one = pattern.find { it.length == 2 }
    val four = pattern.find { it.length == 4 }
    val seven = pattern.find { it.length == 3 }
    val eight = pattern.find { it.length == 7 }

    patternToInt[hashValue(one!!)] = 1
    patternToInt[hashValue(four!!)] = 4
    patternToInt[hashValue(seven!!)] = 7
    patternToInt[hashValue(eight!!)] = 8

    charToSegment[1] = seven.toSet().minus(one.toSet()).first()

    val twoThreeFive = pattern.filter { it.length == 5 }
    val zeroSixNine = pattern.filter { it.length == 6 }

    // Find 6
    val (six, zeroNine) = zeroSixNine.partition {
      it.toSet().minus(one.toSet()).size == 5
    }
    patternToInt[hashValue(six.first())] = 6

    charToSegment[6] = one.toSet().minus(six.first().toSet()).first()
    charToSegment[5] =
      one.toSet().minus(charToSegment[6].toString().toSet()).first()

    // Find 2
    val (two, threeFive) = twoThreeFive.partition {
      it.toSet().minus(four.toSet()).size == 3
    }
    patternToInt[hashValue(two.first())] = 2

    // Find 3, 5
    val (three, five) = threeFive.partition {
      it.toSet().minus(one.toSet()).size == 3
    }
    patternToInt[hashValue(three.first())] = 3
    patternToInt[hashValue(five.first())] = 5

    // Find 0, 9
    val (zero, nine) = zeroNine.partition {
      four.toSet().contains(eight.toSet().minus(it.toSet()).first())
    }
    patternToInt[hashValue(zero.first())] = 0
    patternToInt[hashValue(nine.first())] = 9

    return patternToInt
  }

  fun part1(input: List<List<String>>): Int {
    val output =
      input.map { it[1].split(" ".toRegex()).filterNot(String::isBlank) }
    return output.sumOf { it.count { s -> s.length in listOf(2, 3, 4, 7) } }
  }

  fun part2(input: List<List<String>>): Int {
    val patternOutput = input.map {
      it.map { s ->
        s.split(" ".toRegex()).filterNot(String::isBlank)
      }
    }
    var sum = 0
    for (line in patternOutput) {
      val patternToInt = decode(line[0])
      var res = 0
      for (output in line[1]) {
        res *= 10
        res += patternToInt[hashValue(output)]!!
      }
      sum += res
    }

    return sum
  }

  val testInput =
    readInputLines("day08/test_input").map { it.split("\\|".toRegex()) }
  check(part1(testInput) == 26)
  check(part2(testInput) == 61229)

  val input = readInputLines("day08/input").map { it.split("\\|".toRegex()) }
  println(part1(input))
  println(part2(input))
}

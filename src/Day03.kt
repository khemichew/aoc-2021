fun main() {
  fun mostFreqBit(input: List<String>): String {
    val half = input.size / 2.toDouble()
    return transpose(input)
      .map{ s -> if (s.count{ c -> c == '1'} >= half) '1' else '0' }
      .joinToString("")
  }

  fun reduceByMostFreqBit(input: List<String>): String {
    var res = input
    for (i in input[0].indices) {
      if (res.size == 1) break
      val bitmap = mostFreqBit(res)
      res = res.filter{ s -> s[i] == bitmap[i] }
    }
    return res.first()
  }

  fun reduceByLeastFreqBit(input: List<String>): String {
    var res = input
    for (i in input[0].indices) {
      if (res.size == 1) break
      val bitmap = flipBits(mostFreqBit(res))
      res = res.filter{ s -> s[i] == bitmap[i] }
    }
    return res.first()
  }

  fun part1(input: List<String>): Int {
    val gamma = mostFreqBit(input)
    val epsilon = flipBits(gamma)
    return gamma.toInt(2) * epsilon.toInt(2)
  }

  fun part2(input: List<String>): Int {
    val o2 = reduceByMostFreqBit(input)
    val co2 = reduceByLeastFreqBit(input)
    return o2.toInt(2) * co2.toInt(2)
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day03_test")
  check(part1(testInput) == 198)
  check(part2(testInput) == 230)

  val input = readInput("Day03")
  println(part1(input))
  println(part2(input))
}

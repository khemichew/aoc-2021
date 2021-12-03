fun main() {
  fun parseInput(input: List<String>): List<Pair<String, Int>> {
    val instructions = input.map { s -> s.split(" ") }
    return instructions.map{ s -> Pair(s[0], s[1].toInt()) }
  }

  fun part1(input: List<String>): Int {
    val instructions = parseInput(input)
    var pos = 0
    var depth = 0
    for ((direction, value) in instructions) {
      when (direction) {
        "forward" -> pos += value
        "up" -> depth -= value
        "down" -> depth += value
      }
    }
    return pos * depth
  }

  fun part2(input: List<String>): Int {
    val instructions = parseInput(input)
    var pos = 0
    var depth = 0
    var aim = 0
    for ((direction, value) in instructions) {
      when (direction) {
        "forward" -> { pos += value; depth += aim * value }
        "up" -> aim -= value
        "down" -> aim += value
      }
    }
    return pos * depth
  }

  val testInput = readInput("Day02_test")
  check(part1(testInput) == 150)
  check(part2(testInput) == 900)

  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}

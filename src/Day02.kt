fun main() {
  fun parseInput(input: List<String>): List<Pair<String, Int?>> {
    val instructions = input.map { s -> s.split(" ") }
    return instructions.map{ s -> Pair(s[0], s[1].toIntOrNull()) }
  }

  fun part1(input: List<String>): Int {
    val instructions = parseInput(input)
    var pos = 0
    var depth = 0
    for ((direction, value) in instructions) {
      if (direction == "forward") {
        pos += value!!
      } else if (direction == "up") {
        depth -= value!!
      } else if (direction == "down") {
        depth += value!!
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
      if (direction == "forward") {
        pos += value!!
        depth += aim * value
      } else if (direction == "up") {
        aim -= value!!
      } else if (direction == "down") {
        aim += value!!
      }
    }
    return pos * depth
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day02_test")
  check(part1(testInput) == 150)
  check(part2(testInput) == 900)

  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}

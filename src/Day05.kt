import kotlin.math.sign

class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
  override fun toString(): String {
    return "Line[($x1, $y1), ($x2, $y2)]"
  }
}

fun parseLine(line: String): Line {
  val pairs = line.split("->").map { s -> s.trim().split(",") }
  val coordinates = pairs.map { x -> Pair(x[0].toInt(), x[1].toInt()) }
  return Line(
    coordinates[0].first,
    coordinates[0].second,
    coordinates[1].first,
    coordinates[1].second
  )
}

fun main() {

  fun countStraightLines(lines: List<Line>): Int {
    val visitCount = hashMapOf<Pair<Int, Int>, Int>()

    for (line in lines) {
      val dx = (line.x2 - line.x1).sign
      val dy = (line.y2 - line.y1).sign
      var p = Pair(line.x1, line.y1)
      while (p != Pair(line.x2 + dx, line.y2 + dy)) {
        visitCount[p] = visitCount.getOrDefault(p, 0) + 1
        p = Pair(p.first + dx, p.second + dy)
      }
    }

    return visitCount.values.count { it > 1 }
  }

  fun part1(input: List<Line>): Int {
    return countStraightLines(input.filter { l -> l.x1 == l.x2 || l.y1 == l.y2 })
  }

  fun part2(input: List<Line>): Int {
    return countStraightLines(input)
  }

  val testInput = readInputLines("Day05_test")
  val testLines = testInput.map { s -> parseLine(s) }
  check(part1(testLines) == 5)
  check(part2(testLines) == 12)

  val input = readInputLines("Day05")
  val lines = input.map { s -> parseLine(s) }
  println(part1(lines))
  println(part2(lines))
}

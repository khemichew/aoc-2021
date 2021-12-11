class Bingo (private val numbers: List<List<Int>>) {
  private val isMarked: Array<Array<Boolean>> = Array(5) { Array(5) { false } }

  fun hasWon(): Boolean {
    // Check horizontal lines
    for (i in 0 until 5) {
      var marked = true
      for (j in 0 until 5) {
        if (!isMarked[i][j]) {
          marked = false
          break
        }
      }
      if (marked) return true
    }

    // Check vertical lines
    for (i in 0 until 5) {
      var marked = true
      for (j in 0 until 5) {
        if (!isMarked[j][i]) {
          marked = false
          break
        }
      }
      if (marked) return true
    }

    return false
  }

  fun markIfExists(value: Int) {
    for (i in 0 until 5) {
      for (j in 0 until 5) {
        if (numbers[i][j] == value) {
          isMarked[i][j] = true
        }
      }
    }
  }

  fun sumUnmarkedNumbers(): Int {
    var sum = 0
    for (i in 0 until 5) {
      for (j in 0 until 5) {
        if (!isMarked[i][j]) sum += numbers[i][j]
      }
    }
    return sum
  }

  override fun toString(): String {
    return numbers.toString()
  }
}

fun main() {

  fun parseBingoEntries(input: List<String>): List<Bingo> {
    val bingoEntries = mutableListOf<Bingo>()

    for (i in 0..input.size - 5 step 6) {
      val matrix = readLinesAsIntMatrix(input.subList(i, i + 5))
      bingoEntries.add(Bingo(matrix))
    }

    return bingoEntries
  }

  fun part1(input: List<String>): Int {
    val order = input[0].split(",").map(String::toInt)
    val bingoEntries = parseBingoEntries(input.subList(2, input.size))

    for (drawn in order) {
      for (bingo in bingoEntries) {
        bingo.markIfExists(drawn)
      }

      val res = bingoEntries.filter(Bingo::hasWon)
      if (res.isNotEmpty()) {
        return res.first().sumUnmarkedNumbers() * drawn
      }
    }

    return 0 // NOT REACHED
  }

  fun part2(input: List<String>): Int {
    val order = input[0].split(",").map(String::toInt)
    var bingoEntries = parseBingoEntries(input.subList(2, input.size))

    for (drawn in order) {
      for (bingo in bingoEntries) {
        bingo.markIfExists(drawn)
      }

      val (won, playing) = bingoEntries.partition(Bingo::hasWon)
      bingoEntries = playing
      if (playing.isEmpty()) {
        return won.first().sumUnmarkedNumbers() * drawn
      }
    }

    return 0 // NOT REACHED
  }

  val testInput = readInputLines("Day04_test")
  check(part1(testInput) == 4512)
  check(part2(testInput) == 1924)

  val input = readInputLines("Day04")
  println(part1(input))
  println(part2(input))
}

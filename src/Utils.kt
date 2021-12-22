import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads a line from the given input txt file.
 */
fun readInputLine(name: String) = File("src", "$name.txt").readText()

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String) = File("src", "$name.txt").readLines()

fun readInputLinesAsInts(name: String) = readInputLines(name).map(String::toInt)

fun splitWhitespace(line: String) = line.split("\\s+".toRegex()).filterNot(String::isBlank)

fun parseLineWithCommaAsInts(line: String) = line.split(",".toRegex()).filterNot(String::isBlank).map(String::toInt)

fun parseLineAsInts(line: String) = splitWhitespace(line).map(String::toInt)

fun parseLinesAsIntMatrix(lines: List<String>) = lines.map(::parseLineAsInts)

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Transposes a list of strings.
 */
fun transpose(strings: List<String>): List<String> {
  val transposed = mutableListOf<String>()
  val charArrays = strings.map{ s -> s.toCharArray() }
  for (col in charArrays[0].indices) {
    val builder = StringBuilder()
    for (row in charArrays.indices) {
      builder.append(charArrays[row][col])
    }
    transposed.add(builder.toString())
  }
  return transposed
}

fun flipBits(string: String): String = string.toCharArray().map{ c -> if (c == '1') '0' else '1'}.joinToString("")
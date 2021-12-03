import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

fun readInputAsInts(name: String) = readInput(name).map { s -> s.toInt() }

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
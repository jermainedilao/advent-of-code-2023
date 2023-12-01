package day1

import openFile

fun main() {
    openFile("day1/Day1Input").readText().trim().split("\n")
        .sumOf(::findFirstAndLastDigits)
        .let(::println)
}

private fun findFirstAndLastDigits(string: String): Int {
    val first = getFirstDigit(string)
    val last = getLastDigit(string)
    return "$first$last".toInt()
}

private fun getFirstDigit(string: String): String {
    val firstWordDigitKey = string.findAnyOf(numbersMap.keys)?.second
    val firstDigit = string.firstOrNull(Character::isDigit)
    return when {
        firstWordDigitKey != null && firstDigit != null -> {
            if (string.indexOf(firstWordDigitKey) < string.indexOf(firstDigit)) {
                numbersMap[firstWordDigitKey]
            } else {
                firstDigit
            }
        }
        firstWordDigitKey != null && firstDigit == null -> {
            numbersMap[firstWordDigitKey]
        }
        else -> {
            firstDigit
        }
    }.toString()
}

private fun getLastDigit(string: String): String {
    val lastWordDigitKey = string.findLastAnyOf(numbersMap.keys)?.second
    val lastDigit = string.lastOrNull(Character::isDigit)
    return when {
        lastWordDigitKey != null && lastDigit != null -> {
            if (string.lastIndexOf(lastWordDigitKey) > string.lastIndexOf(lastDigit)) {
                numbersMap[lastWordDigitKey]
            } else {
                lastDigit
            }
        }
        lastWordDigitKey != null && lastDigit == null -> {
            numbersMap[lastWordDigitKey]
        }
        else -> {
            lastDigit
        }
    }.toString()
}

private val numbersMap = mapOf(
    "zero" to 0,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)
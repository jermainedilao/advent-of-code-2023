package day1

import openFile
import java.lang.Character.isDigit

fun main() {
    openFile("day1/Day1Input").readText().trim().split("\n")
        .sumOf { "${it.first(::isDigit)}${it.last(::isDigit)}".toInt() }
        .let(::println)
}

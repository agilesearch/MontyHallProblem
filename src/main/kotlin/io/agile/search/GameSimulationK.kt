package io.agile.search

import io.agile.search.domain.GameK
import io.agile.search.domain.MAX_DOORS
import kotlin.random.Random

private const val MATCHES = 1000000

fun main(args: Array<String>) {
    var wins = 0
    for (i in 0 until MATCHES) {
        val game = GameK()
        game.userChoice(Random.nextInt(MAX_DOORS))
        game.getWrongDoor()
        if (game.isUserWinner()) {
            wins++
        }
    }
    println("Not changing the door made him win $wins of $MATCHES matches!")
    wins = 0
    for (i in 0 until MATCHES) {
        val game = GameK()
        game.userChoice(Random.nextInt(MAX_DOORS))
        game.getWrongDoor()
        game.swapDoor()
        if (game.isUserWinner()) {
            wins++
        }
    }
    println("Changing the door made him win $wins of $MATCHES matches!")
}

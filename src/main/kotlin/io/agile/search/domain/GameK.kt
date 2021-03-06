package io.agile.search.domain

import kotlin.random.Random

const val MAX_DOORS = 3
private const val DOOR_NOT_EXIST = "Door not exist!"
private const val THE_DOOR_HAS_ALREADY_BEEN_SHOWN = "The door has already been shown!"
private const val USER_NEEDS_TO_CHOOSE_THE_PORT_FIRST = "User needs to choose the port first!"
private const val PRESENTER_NEEDS_TO_REMOVE_A_DOOR_FIRST = "Presenter needs to remove a door first!"

class GameK() {
    private var doorUser: DoorK? = null
    private val doorWinner = DoorK(Random.nextInt(MAX_DOORS), true)
    private val doors = hashSetOf(doorWinner)

    init {
        var door: DoorK
        do {
            door = DoorK(Random.nextInt(MAX_DOORS), false)
            doors.add(door)
        } while (doors.size < MAX_DOORS)
    }

    private fun validateRemoveDoor() {
        if (doors.size > 2) {
            throw IllegalStateException(PRESENTER_NEEDS_TO_REMOVE_A_DOOR_FIRST)
        }
    }

    fun userChoice(doorNumber: Int) {
        doorUser = doors.firstOrNull { it.doorNumber == doorNumber } ?: throw NoSuchElementException(DOOR_NOT_EXIST)
    }

    fun swapDoor() {
        validateRemoveDoor()
        doorUser = doors.firstOrNull { it !== doorUser } ?: throw NoSuchElementException(DOOR_NOT_EXIST)
    }

    fun getWrongDoor(): DoorK {
        when {
            doorUser == null -> throw IllegalStateException(USER_NEEDS_TO_CHOOSE_THE_PORT_FIRST)
            doors.size < MAX_DOORS -> throw IllegalStateException(THE_DOOR_HAS_ALREADY_BEEN_SHOWN)
        }
        val wrongDoor: DoorK = doors.firstOrNull { !it.winner && it !== doorUser } ?: throw NoSuchElementException()
        doors.remove(wrongDoor)
        return wrongDoor
    }

    fun isUserWinner(): Boolean {
        validateRemoveDoor()
        return doorWinner === doorUser
    }
}

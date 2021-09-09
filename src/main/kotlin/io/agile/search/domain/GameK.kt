package io.agile.search.domain


import java.util.*
import kotlin.random.Random

const val MAX_DOORS = 3
private const val DOOR_NOT_EXIST = "Door not exist!"
private const val THE_DOOR_HAS_ALREADY_BEEN_SHOWN = "The door has already been shown!"
private const val USER_NEEDS_TO_CHOOSE_THE_PORT_FIRST = "User needs to choose the port first!"
private const val PRESENTER_NEEDS_TO_REMOVE_A_DOOR_FIRST = "Presenter needs to remove a door first!"

class GameK() {
    private var doorUser: DoorK? = null
    private val doors = HashSet<DoorK>()
    private val doorWinner = DoorK(Random.nextInt(MAX_DOORS), true)

    init {
        doors.add(doorWinner)
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
        doorUser = doors.stream()
            .filter { it.doorNumber == doorNumber }
            .findFirst().orElseThrow { NoSuchElementException(DOOR_NOT_EXIST) }
    }

    fun swapDoor() {
        validateRemoveDoor()
        doorUser = doors.stream()
            .filter{ it !== doorUser }
            .findFirst().orElseThrow { NoSuchElementException(DOOR_NOT_EXIST) }
    }

    fun getWrongDoor(): DoorK {
        if (Objects.isNull(doorUser)) {
            throw IllegalStateException(USER_NEEDS_TO_CHOOSE_THE_PORT_FIRST)
        }
        if (doors.size < MAX_DOORS) {
            throw IllegalStateException(THE_DOOR_HAS_ALREADY_BEEN_SHOWN)
        }
        val wrongDoor: DoorK = doors.stream()
            .filter{ !it.winner && it !== doorUser }
            .findFirst().orElseThrow{ NoSuchElementException() }
        doors.remove(wrongDoor)
        return wrongDoor
    }

    fun isUserWinner(): Boolean {
        validateRemoveDoor()
        return doorWinner === doorUser
    }
}

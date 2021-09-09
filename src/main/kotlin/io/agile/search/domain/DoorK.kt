package io.agile.search.domain

data class DoorK(val doorNumber: Int, val winner: Boolean) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DoorK

        if (doorNumber != other.doorNumber) return false

        return true
    }

    override fun hashCode(): Int {
        return doorNumber
    }
}
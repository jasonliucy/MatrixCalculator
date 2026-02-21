package matrixcalculator

data class Vector(
    private val vector: List<Double>,
) {
    init {
        if (vector.isEmpty()) {
            throw IllegalArgumentException()
        }
    }

    val length: Int
        get() = vector.size

    operator fun get(index: Int): Double = vector[index]

    infix operator fun plus(other: Vector): Vector =
        if (this.length == other.length) {
            Vector(this.vector.zip(other.vector, Double::plus))
        } else {
            throw UnsupportedOperationException()
        }

    infix operator fun times(scalar: Double): Vector = Vector(vector.map { it * scalar })

    infix fun dot(other: Vector): Double =
        if (this.length == other.length) {
            this.vector.zip(other.vector, Double::times).sum()
        } else {
            throw UnsupportedOperationException()
        }

    override fun toString(): String = vector.joinToString(", ", "(", ")")

    operator fun iterator(): Iterator<Double> = vector.iterator()
}

operator fun Double.times(other: Vector) = other * this

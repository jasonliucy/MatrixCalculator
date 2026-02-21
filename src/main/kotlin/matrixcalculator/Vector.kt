package matrixcalculator

data class Vector(
    private val vector: List<Double>,
) : Iterable<Double> {
    init {
        if (vector.isEmpty()) {
            throw IllegalArgumentException()
        }
    }

    val length: Int
        get() = vector.size

    operator fun get(index: Int): Double = vector[index]

    operator fun plus(other: Vector): Vector =
        if (this.length == other.length) {
            Vector(this.vector.zip(other.vector, Double::plus))
        } else {
            throw UnsupportedOperationException()
        }

    operator fun times(scalar: Double): Vector = Vector(vector.map { it * scalar })

    infix fun dot(other: Vector): Double =
        if (this.length == other.length) {
            this.vector.zip(other.vector, Double::times).sum()
        } else {
            throw UnsupportedOperationException()
        }

    infix fun outer(other: Vector): Matrix = Matrix(listOf(this)).transpose() * Matrix(listOf(other))

    infix fun perpendicular(other: Vector): Boolean = this dot other == 0.0

    override fun toString(): String = vector.joinToString(", ", "(", ")")

    override operator fun iterator(): Iterator<Double> = vector.iterator()
}

operator fun Double.times(other: Vector) = other * this

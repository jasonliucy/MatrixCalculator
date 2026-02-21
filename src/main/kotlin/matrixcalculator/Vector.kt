package matrixcalculator

import kotlin.math.acos
import kotlin.math.sqrt

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

    operator fun set(
        index: Int,
        n: Double,
    ) {
        (vector as MutableList<Double>)[index] = n
    }

    operator fun plus(other: Vector): Vector =
        if (this.length == other.length) {
            Vector(this.vector.zip(other.vector, Double::plus))
        } else {
            throw UnsupportedOperationException()
        }

    operator fun minus(other: Vector): Vector =
        if (this.length == other.length) {
            Vector(this.vector.zip(other.vector, Double::minus))
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

    infix fun cross(other: Vector): Vector =
        if (this.length == 3 && other.length == 3) {
            Vector(
                listOf(
                    this[1] * other[2] - this[2] * other[1],
                    this[2] * other[0] - this[0] * other[2],
                    this[0] * other[1] - this[1] * other[0],
                ),
            )
        } else {
            throw UnsupportedOperationException()
        }

    infix fun perpendicular(other: Vector): Boolean = this dot other == 0.0

    fun magnitude(): Double = sqrt(vector.sumOf { it * it })

    infix fun angleWith(other: Vector): Double =
        acos(
            (this dot other) /
                (this.magnitude() * other.magnitude()),
        )

    override fun toString(): String = vector.joinToString(", ", "(", ")")

    override operator fun iterator(): Iterator<Double> = vector.iterator()
}

operator fun Double.times(other: Vector) = other * this

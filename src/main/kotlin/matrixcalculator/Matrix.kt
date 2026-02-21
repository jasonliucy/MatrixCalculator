package matrixcalculator

data class Matrix(
    private val matrix: List<Vector>,
) : Iterable<Vector> {
    init {
        if (matrix.isEmpty() || matrix.any { it.length != matrix[0].length }) {
            throw IllegalArgumentException()
        }
    }

    val numRows: Int
        get() = matrix.size

    val numColumns: Int
        get() = matrix[0].length

    operator fun get(row: Int): Vector = matrix[row]

    fun getRow(row: Int): Vector = this[row]

    fun getColumn(column: Int): Vector = Vector(matrix.map { it[column] })

    operator fun get(
        row: Int,
        column: Int,
    ): Double = matrix[row][column]

    operator fun plus(other: Matrix): Matrix =
        if (this.numRows == other.numRows && this.numColumns == other.numColumns) {
            Matrix(this.matrix.zip(other.matrix, Vector::plus))
        } else {
            throw UnsupportedOperationException()
        }

    operator fun minus(other: Matrix): Matrix =
        if (this.numRows == other.numRows && this.numColumns == other.numColumns) {
            Matrix(this.matrix.zip(other.matrix, Vector::minus))
        } else {
            throw UnsupportedOperationException()
        }

    operator fun times(other: Matrix): Matrix {
        if (this.numColumns == other.numRows) {
            return Matrix(
                matrix
                    .map { row ->
                        Vector(
                            (0..<other.numColumns)
                                .map { column -> row dot other.getColumn(column) },
                        )
                    },
            )
        } else {
            throw UnsupportedOperationException()
        }
    }

    operator fun times(scalar: Double): Matrix = Matrix(matrix.map { it * scalar })

    fun transpose(): Matrix = Matrix((0..<numColumns).map { getColumn(it) })

    override fun toString(): String {
        val columnWidth: List<Int> =
            (0..<numColumns).map { column ->
                (0..<numRows).maxOf { row ->
                    this[row, column].toString().length
                }
            }

        return matrix.joinToString("\n") { vector ->
            (0..<numColumns)
                .joinToString(" ", "[ ", " ]") { column ->
                    vector[column]
                        .toString()
                        .padStart(columnWidth[column])
                }
        }
    }

    override operator fun iterator(): Iterator<Vector> = matrix.iterator()
}

operator fun Double.times(other: Matrix): Matrix = other * this

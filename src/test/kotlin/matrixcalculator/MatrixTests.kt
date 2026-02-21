package matrixcalculator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class MatrixTests {
    @Test
    fun `exception - empty matrix`() {
        try {
            Matrix(emptyList())
            fail("IllegalArgumentException was expected.")
        } catch (exception: IllegalArgumentException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `exception - different row lengths in matrix`() {
        try {
            Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0))))
            fail("IllegalArgumentException was expected.")
        } catch (exception: IllegalArgumentException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `get row`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 2.0, 3.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 0.0, 2.0, 3.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)),
                ),
            )
        assertEquals(Vector(listOf(1.0, 2.0, 3.0, 0.5, 1.0)), m1.getRow(0))
        assertEquals(Vector(listOf(0.0, 1.0, 0.0, 2.0, 3.0)), m1.getRow(1))
        assertEquals(Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)), m1.getRow(2))
        assertEquals(Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)), m1.getRow(3))
    }

    @Test
    fun `exception - negative row index`() {
        try {
            Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0, 1.0)))).getRow(-1)
            fail("IndexOutOfBoundsException was expected.")
        } catch (exception: IndexOutOfBoundsException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `exception - too large row index`() {
        try {
            Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0, 1.0)))).getRow(2)
            fail("IndexOutOfBoundsException was expected.")
        } catch (exception: IndexOutOfBoundsException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `get column`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 2.0, 3.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 0.0, 2.0, 3.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)),
                ),
            )
        assertEquals(Vector(listOf(1.0, 0.0, 1.0, 2.0)), m1.getColumn(0))
        assertEquals(Vector(listOf(2.0, 1.0, 0.0, 0.0)), m1.getColumn(1))
        assertEquals(Vector(listOf(3.0, 0.0, 1.0, 1.0)), m1.getColumn(2))
        assertEquals(Vector(listOf(0.5, 2.0, 2.0, 1.0)), m1.getColumn(3))
        assertEquals(Vector(listOf(1.0, 3.0, 4.0, 1.0)), m1.getColumn(4))
    }

    @Test
    fun `get entry`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 2.0, 3.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 0.0, 2.0, 3.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 8.0)),
                ),
            )
        assertEquals(1.0, m1[0, 0])
        assertEquals(2.0, m1[2, 3])
        assertEquals(8.0, m1[3, 4])
    }

    @Test
    fun `exception - negative column index`() {
        try {
            Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0, 1.0)))).getColumn(-1)
            fail("IndexOutOfBoundsException was expected.")
        } catch (exception: IndexOutOfBoundsException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `exception - too large column index`() {
        try {
            Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0, 1.0)))).getColumn(2)
            fail("IndexOutOfBoundsException was expected.")
        } catch (exception: IndexOutOfBoundsException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `matrix addition`() {
        val m1 = Matrix(listOf(Vector(listOf(1.0, 2.0)), Vector(listOf(3.0, 4.0))))
        val m2 = Matrix(listOf(Vector(listOf(9.0, 7.0)), Vector(listOf(5.0, 3.0))))
        val sum = Matrix(listOf(Vector(listOf(10.0, 9.0)), Vector(listOf(8.0, 7.0))))
        assertEquals(sum, m1 + m2)
    }

    @Test
    fun `exception - add matrices with different row counts`() {
        val m1 = Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0, 1.0))))
        val m2 = Matrix(listOf(Vector(listOf(1.0, 1.0))))
        try {
            m1 + m2
            fail("UnsupportedOperationException was expected")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `exception - add matrices with different column counts`() {
        val m1 = Matrix(listOf(Vector(listOf(1.0, 1.0))))
        val m2 = Matrix(listOf(Vector(listOf(1.0, 1.0, 1.0))))
        try {
            m1 + m2
            fail("UnsupportedOperationException was expected")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `matrix multiplication`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 2.0, 3.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 0.0, 2.0, 3.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)),
                ),
            )

        val m2 =
            Matrix(
                listOf(
                    Vector(listOf(2.0, 3.0)),
                    Vector(listOf(1.0, 2.0)),
                    Vector(listOf(4.0, 1.0)),
                    Vector(listOf(0.0, 1.0)),
                    Vector(listOf(1.0, 3.0)),
                ),
            )

        val product =
            Matrix(
                listOf(
                    Vector(listOf(17.0, 13.5)),
                    Vector(listOf(4.0, 13.0)),
                    Vector(listOf(10.0, 18.0)),
                    Vector(listOf(9.0, 11.0)),
                ),
            )

        assertEquals(product, m1 * m2)
    }

    @Test
    fun `exception - multiply matrices with incompatible sizes`() {
        val m1 = Matrix(listOf(Vector(listOf(1.0, 1.0)), Vector(listOf(1.0, 1.0))))
        val m2 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 1.0, 1.0)),
                    Vector(listOf(1.0, 1.0, 1.0)),
                    Vector(listOf(1.0, 1.0, 1.0)),
                ),
            )
        try {
            m1 * m2
            fail("UnsupportedOperationException was expected")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `left multiply by scalar`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 2.0, 4.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 0.0, 2.0, 4.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)),
                ),
            )

        val scaled =
            Matrix(
                listOf(
                    Vector(listOf(.10, .20, .40, .05, .10)),
                    Vector(listOf(.00, .10, .00, .20, .40)),
                    Vector(listOf(.10, .00, .10, .20, .40)),
                    Vector(listOf(.20, .00, .10, .10, .10)),
                ),
            )

        assertEquals(scaled, 0.1 * m1)
    }

    @Test
    fun `right multiply by scalar`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.0, 2.0, 4.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 0.0, 2.0, 4.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2.0, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)),
                ),
            )

        val scaled =
            Matrix(
                listOf(
                    Vector(listOf(.10, .20, .40, .05, .10)),
                    Vector(listOf(.00, .10, .00, .20, .40)),
                    Vector(listOf(.10, .00, .10, .20, .40)),
                    Vector(listOf(.20, .00, .10, .10, .10)),
                ),
            )

        assertEquals(scaled, m1 * 0.1)
    }

    @Test
    fun `string representation`() {
        val m1 =
            Matrix(
                listOf(
                    Vector(listOf(1.46, 2.0, 4.0, 0.5, 1.0)),
                    Vector(listOf(0.0, 1.0, 100.0, 2.0, 4.0)),
                    Vector(listOf(1.0, 0.0, 1.0, 2020.12, 4.0)),
                    Vector(listOf(2.0, 0.0, 1.0, 1.0, 1.0)),
                ),
            )
        val stringRepresentation =
            """
            [ 1.46 2.0   4.0     0.5 1.0 ]
            [  0.0 1.0 100.0     2.0 4.0 ]
            [  1.0 0.0   1.0 2020.12 4.0 ]
            [  2.0 0.0   1.0     1.0 1.0 ]
            """.trimIndent()

        assertEquals(stringRepresentation, m1.toString())
    }

    @Test
    fun `iterate over matrix rows`() {
        val row1 = Vector(listOf(1.0, 2.0))
        val row2 = Vector(listOf(3.0, 4.0))
        val matrix = Matrix(listOf(row1, row2))
        val result = mutableListOf<Vector>()

        for (row in matrix) {
            result.add(row)
        }

        assertEquals(2, result.size)
        assertEquals(row1, result[0])
        assertEquals(row2, result[1])
    }
}

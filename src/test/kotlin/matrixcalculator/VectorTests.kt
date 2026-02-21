package matrixcalculator

import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class VectorTests {
    @Test
    fun `get from vector`() {
        val v = Vector(listOf(1.0, 2.0, 3.0))
        assertEquals(1.0, v[0])
        assertEquals(2.0, v[1])
        assertEquals(3.0, v[2])
    }

    @Test
    fun `scalar times vector`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val scaled = Vector(listOf(10.0, 20.0, 30.0))
        assertEquals(scaled, v1 * 10.0)
    }

    @Test
    fun `vector times scalar`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val scaled = Vector(listOf(10.0, 20.0, 30.0))
        assertEquals(scaled, 10.0 * v1)
    }

    @Test
    fun `dot product`() {
        val v1 = Vector(listOf(1.0, 0.0, 0.0))
        val v2 = Vector(listOf(0.0, 1.0, 0.0))
        assertEquals(0.0, v1 dot v2)
    }

    @Test
    fun `string representation`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0, 4.0, 5.0))
        assertEquals("(1.0, 2.0, 3.0, 4.0, 5.0)", v1.toString())
    }

    @Test
    fun `exception - empty vector`() {
        try {
            Vector(emptyList())
            fail("IllegalArgumentException was expected.")
        } catch (exception: IllegalArgumentException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `exception - lengths do not match in dot product`() {
        try {
            Vector(listOf(1.0, 2.0)) dot Vector(listOf(1.0, 2.0, 3.0))
            fail("UnsupportedOperationException was expected.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `exception - get at too large index`() {
        try {
            Vector(listOf(1.0, 2.0))[2]
            fail("IndexOutOfBoundsException was expected.")
        } catch (exception: IndexOutOfBoundsException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `addition works`() {
        val v1 = Vector(listOf(1.0, 0.0, 4.0))
        val v2 = Vector(listOf(2.0, 1.0, 0.0))
        val sum = Vector(listOf(3.0, 1.0, 4.0))
        assertEquals(sum, v1 + v2)
    }

    @Test
    fun `exception - lengths do not match in addition`() {
        try {
            Vector(listOf(1.0, 2.0)) + Vector(listOf(1.0, 2.0, 3.0))
            fail("UnsupportedOperationException was expected.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `subtraction works`() {
        val v1 = Vector(listOf(1.0, 0.0, 4.0))
        val v2 = Vector(listOf(2.0, 1.0, 0.0))
        val difference = Vector(listOf(-1.0, -1.0, 4.0))
        assertEquals(difference, v1 - v2)
    }

    @Test
    fun `exception - lengths do not match in subtraction`() {
        try {
            Vector(listOf(1.0, 2.0)) - Vector(listOf(1.0, 2.0, 3.0))
            fail("UnsupportedOperationException was expected.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `iterate over vector elements`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val result = mutableListOf<Double>()

        for (element in v1) {
            result.add(element)
        }

        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun `test outer product`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val v2 = Vector(listOf(4.0, 5.0))
        val m =
            Matrix(
                listOf(
                    Vector(listOf(4.0, 5.0)),
                    Vector(listOf(8.0, 10.0)),
                    Vector(listOf(12.0, 15.0)),
                ),
            )

        assertEquals(m, v1 outer v2)
    }

    @Test
    fun `test vectors are perpendicular`() {
        val v1 = Vector(listOf(1.0, 1.0))
        val v2 = Vector(listOf(-1.0, 1.0))

        assertTrue(v1 perpendicular v2)
    }

    @Test
    fun `test cross product`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val v2 = Vector(listOf(3.0, 4.0, 5.0))
        val v3 = Vector(listOf(-2.0, 4.0, -2.0))

        assertEquals(v3, v1 cross v2)
    }

    @Test
    fun `cannot perform cross product on vectors not of size 3`() {
        val v1 = Vector(listOf(1.0, 2.0))
        val v2 = Vector(listOf(3.0, 4.0))

        try {
            v1 cross v2
            fail("UnsupportedOperationException was expected.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `cannot perform cross product on vectors not of the same size`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val v2 = Vector(listOf(3.0, 4.0))

        try {
            v1 cross v2
            fail("UnsupportedOperationException was expected.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception was expected.
        }
    }

    @Test
    fun `test magnitude`() {
        assertEquals(10.0, Vector(listOf(6.0, 8.0)).magnitude(), 0.000001)
    }

    @Test
    fun `test angle between vectors`() {
        val v1 = Vector(listOf(1.0, 2.0, 3.0))
        val v2 = Vector(listOf(3.0, 4.0, 5.0))

        assertEquals(0.1862387659, v1 angleWith v2, 0.000001)
    }
}

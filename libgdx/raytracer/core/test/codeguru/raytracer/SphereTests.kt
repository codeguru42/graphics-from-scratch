package codeguru.raytracer

import com.badlogic.gdx.graphics.Color
import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class SphereTests {
    @Test
    fun testIntersect() {
        val s = Sphere(Point3(-2.0f, 0.0f, 4.0f), 1.0f, Color.GREEN)
        val p1 = Point3(-2.0f, 0.0f, 0.0f)
        val p2 = Point3(-2.0f, 0.0f, 1.0f)
        val actual = s.intersect(p1, p2)
        val expected = Pair(5.0f, 3.0f)
        assertThat(actual).isEqualTo(expected)
    }
}

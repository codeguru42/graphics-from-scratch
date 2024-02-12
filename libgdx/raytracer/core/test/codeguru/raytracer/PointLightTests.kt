package codeguru.raytracer

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class PointLightTests {
    @Test
    fun testGetIntensity() {
        val light = PointLight(0.6f, Point3(2.0f, 1.0f, 0.0f))
        val p = Point3(-2.0f, 0.0f, 3.0f)
        val n = Vector(0.0f, 0.0f, -1.0f)
        val v = Vector(0.0f, 0.0f, 0.0f)
        val s = -1
        val intensity = light.getIntensityAt(p, n, v, s)
        assertThat(intensity).isWithin(1.0e-5f).of(0.35301f)
    }
}

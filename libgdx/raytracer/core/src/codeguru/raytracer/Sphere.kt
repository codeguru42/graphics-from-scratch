package codeguru.raytracer

import com.badlogic.gdx.graphics.Color
import kotlin.math.sqrt

class Sphere(val center: Point, val radius: Float, val color: Color) {
    fun intersect(p1: Point, p2: Point): Pair<Float, Float> {
        val origin = Point(0.0f, 0.0f, 0.0f)
        val co = subtract(p1, this.center)
        val d = subtract(p2, origin)

        val a = dot(d, d)
        val b = 2 * dot(co, d)
        val c = dot(co, co) - this.radius * this.radius

        val discriminant = b * b - 4 * a * c
        if (discriminant < 0) {
            return Pair(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        }

        val t1 = (-b + sqrt(discriminant.toDouble()) / 2 * a)
        val t2 = (-b - sqrt(discriminant.toDouble()) / 2 * a)
        return Pair(t1.toFloat(), t2.toFloat())
    }
}

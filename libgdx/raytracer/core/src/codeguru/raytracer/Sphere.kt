package codeguru.raytracer

import com.badlogic.gdx.graphics.Color
import kotlin.math.sqrt

class Sphere(val center: Point3, val radius: Float, val color: Color) {
    fun intersect(p1: Point3, p2: Point3): Pair<Float, Float> {
        val co = subtract(p1, this.center)
        val d = subtract(p2, p1)

        val a = dot(d, d)
        val b = 2 * dot(co, d)
        val c = dot(co, co) - this.radius * this.radius

        val discriminant = b * b - 4 * a * c
        if (discriminant < 0) {
            return Pair(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        }

        val t1 = (-b + sqrt(discriminant.toDouble())) / (2 * a)
        val t2 = (-b - sqrt(discriminant.toDouble())) / (2 * a)
        return Pair(t1.toFloat(), t2.toFloat())
    }

    fun normalAt(p: Point3): Vector {
        return subtract(p, center).normalize()
    }
}

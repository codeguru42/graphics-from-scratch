package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

data class Sphere(
    val center: Point3,
    val radius: Float,
    val color: Color,
    val specular: Int = -1,
    val reflective: Float = 0.0f,
) {
    fun intersect(r: Ray): Pair<Float, Float> {
        val co = subtract(r.p, this.center)

        val a = dot(r.v, r.v)
        val b = 2 * dot(co, r.v)
        val c = dot(co, co) - this.radius * this.radius

        return solveQuadratic(a, b, c)
    }

    fun normalAt(p: Point3): Vector {
        return subtract(p, center).normalize()
    }
}

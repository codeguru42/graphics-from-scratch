package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

data class Sphere(
    val center: Point3,
    val radius: Float,
    val color: Color,
    val specular: Int = -1,
    val reflective: Float = 0.0f,
) {
    fun intersect(p1: Point3, p2: Vector): Pair<Float, Float> {
        val co = subtract(p1, this.center)

        val a = dot(p2, p2)
        val b = 2 * dot(co, p2)
        val c = dot(co, co) - this.radius * this.radius

        return solveQuadratic(a, b, c)
    }

    fun normalAt(p: Point3): Vector {
        return subtract(p, center).normalize()
    }
}

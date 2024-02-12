package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

data class Sphere(val center: Point3, val radius: Float, val color: Color, val specular: Int = -1) {
    fun intersect(p1: Point3, p2: Point3): Pair<Float, Float> {
        val co = subtract(p1, this.center)
        val d = subtract(p2, p1)

        val a = dot(d, d)
        val b = 2 * dot(co, d)
        val c = dot(co, co) - this.radius * this.radius

        return solveQuadratic(a, b, c)
    }

    fun normalAt(p: Point3): Vector {
        return subtract(p, center).normalize()
    }
}

package codeguru.raytracer

import kotlin.math.sqrt

fun lerp(x: Float, x1: Float, y1: Float, x2: Float, y2: Float): Float {
    val m = (y2 - y1) / (x2 - x1)
    return m * (x - x1) + y1
}

fun solveQuadratic(a: Float, b: Float, c: Float): Pair<Float, Float> {
    val discriminant = b * b - 4 * a * c
    if (discriminant < 0) {
        return Pair(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    }

    val t1 = (-b + sqrt(discriminant.toDouble())) / (2 * a)
    val t2 = (-b - sqrt(discriminant.toDouble())) / (2 * a)
    return Pair(t1.toFloat(), t2.toFloat())
}

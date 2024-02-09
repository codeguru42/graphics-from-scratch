package codeguru.raytracer

import kotlin.math.sqrt

data class Vector(val x: Float, val y: Float, val z: Float) {
    val length: Float = sqrt(dot(this, this))

    fun normalize(): Vector {
        return Vector(x / length, y / length, z / length)
    }
}

fun add(p: Point, v: Vector): Point {
    return Point(p.x + v.x, p.y + v.y, p.z + v.z)
}

fun subtract(p1: Point, p2: Point): Vector {
    return Vector(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z)
}

fun mul(c: Float, v: Vector): Vector {
    return Vector(c * v.x, c * v.y, c * v.z)
}

fun dot(v1: Vector, v2: Vector): Float {
    return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
}

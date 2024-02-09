package codeguru.raytracer

data class Vector(val x: Float, val y: Float, val z: Float)

fun subtract(p1: Point, p2: Point): Vector {
    return Vector(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z)
}

fun dot(v1: Vector, v2: Vector): Float {
    return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
}

package codeguru.raytracer

data class Ray(val p: Point3, val v: Vector) {
    fun evaluate(t: Float): Point3 {
        return add(p, mul(t, v))
    }
}

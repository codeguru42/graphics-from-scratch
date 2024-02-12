package codeguru.raytracer

abstract class Light(protected val intensity: Float) {
    abstract fun getIntensityAt(p: Point3, n: Vector): Float

    protected fun getDiffuseIntensityAt(n: Vector, l: Vector): Float {
        val nDotL = dot(n, l)
        if (nDotL > 0)
            return intensity * nDotL / (n.length * l.length)
        return 0.0f
    }
}

class AmbientLight(intensity: Float) : Light(intensity) {
    override fun getIntensityAt(p: Point3, n: Vector): Float {
        return intensity
    }
}

class PointLight(intensity: Float, private val position: Point3) : Light(intensity) {
    override fun getIntensityAt(p: Point3, n: Vector): Float {
        val l = subtract(position, p)
        return super.getDiffuseIntensityAt(n, l)
    }
}

class DirectionalLight(intensity: Float, private val direction: Vector) : Light(intensity) {
    override fun getIntensityAt(p: Point3, n: Vector): Float {
        val l = direction
        return super.getDiffuseIntensityAt(n, l)
    }
}

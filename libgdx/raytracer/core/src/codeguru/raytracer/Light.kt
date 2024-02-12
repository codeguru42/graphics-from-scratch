package codeguru.raytracer

abstract class Light(protected val intensity: Float) {
    protected fun getIntensityAt(p: Point3, n: Vector, l: Vector): Float {
        val nDotL = dot(n, l)
        if (nDotL > 0)
            return intensity * nDotL / (n.length * l.length)
        return 0.0f
    }

    abstract fun getDiffuseIntensityAt(p: Point3, n: Vector): Float
}

class AmbientLight(intensity: Float) : Light(intensity) {
    override fun getDiffuseIntensityAt(p: Point3, n: Vector): Float {
        return intensity
    }
}

class PointLight(intensity: Float, private val position: Point3) : Light(intensity) {
    override fun getDiffuseIntensityAt(p: Point3, n: Vector): Float {
        val l = subtract(position, p)
        return super.getIntensityAt(p, n, l)
    }
}

class DirectionalLight(intensity: Float, private val direction: Vector) : Light(intensity) {
    override fun getDiffuseIntensityAt(p: Point3, n: Vector): Float {
        val l = direction
        return super.getIntensityAt(p, n, l)
    }
}

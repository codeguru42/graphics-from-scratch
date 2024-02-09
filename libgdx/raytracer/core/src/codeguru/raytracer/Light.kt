package codeguru.raytracer

interface Light {
    fun getIntensityAt(p: Point3, n: Vector): Float
}

class AmbientLight(private val intensity: Float) : Light {
    override fun getIntensityAt(p: Point3, n: Vector): Float {
        return intensity
    }
}

class PointLight(private val intensity: Float, private val position: Point3) : Light {
    override fun getIntensityAt(p: Point3, n: Vector): Float {
        val l = subtract(position, p)
        val nDotL = dot(n, l)
        if (nDotL > 0)
            return intensity * nDotL / (n.length * l.length)
        return 0.0f
    }
}

class DirectionalLight(private val intensity: Float, private val direction: Vector) : Light {
    override fun getIntensityAt(p: Point3, n: Vector): Float {
        val l = direction
        val nDotL = dot(n, l)
        if (nDotL > 0)
            return intensity * nDotL / (n.length * l.length)
        return 0.0f
    }
}

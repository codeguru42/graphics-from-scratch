package codeguru.raytracer

import kotlin.math.pow

abstract class Light(protected val intensity: Float) {
    abstract val t_max: Float

    open fun getIntensityAt(p: Point3, n: Vector, v: Vector, s: Int): Float {
        val l = getLightVectorAt(p)!!
        return getDiffuseIntensityAt(n, l) + getSpecularIntensityAt(l, n, v, s)
    }

    abstract fun getLightVectorAt(p: Point3): Vector?

    protected fun getDiffuseIntensityAt(n: Vector, l: Vector): Float {
        val nDotL = dot(n, l)
        if (nDotL > 0)
            return intensity * nDotL / (n.length * l.length)
        return 0.0f
    }

    fun getSpecularIntensityAt(l: Vector, n: Vector, v: Vector, s: Int): Float {
        if (s == -1) {
            return 0.0f
        }

        val r = subtract(mul(2.0f * dot(n, l), n), l)
        val rDotV = dot(r, v)

        if (rDotV <= 0) {
            return 0.0f
        }

        val specularIntensity = (rDotV / (r.length * v.length)).toDouble().pow(s.toDouble())
        return intensity * specularIntensity.toFloat()
    }
}

class AmbientLight(intensity: Float) : Light(intensity) {
    override val t_max: Float = Float.POSITIVE_INFINITY

    override fun getIntensityAt(p: Point3, n: Vector, v: Vector, s: Int): Float {
        return intensity
    }

    override fun getLightVectorAt(p: Point3): Vector? {
        return null
    }
}

class PointLight(intensity: Float, private val position: Point3) : Light(intensity) {
    override val t_max: Float = 1.0f

    override fun getLightVectorAt(p: Point3): Vector {
        return subtract(position, p)
    }
}

class DirectionalLight(intensity: Float, private val direction: Vector) : Light(intensity) {
    override val t_max: Float = Float.POSITIVE_INFINITY

    override fun getLightVectorAt(p: Point3): Vector {
        return direction
    }
}

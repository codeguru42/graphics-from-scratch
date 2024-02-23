package codeguru.raytracer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import kotlin.math.min

class Raytracer : ApplicationAdapter() {
    private var BACKGROUND_COLOR = Color.WHITE

    private var canvasWidth: Int? = null
    private var canvasHeight: Int? = null
    private var viewportWidth: Float? = null
    private var viewportHeight: Float? = null
    private val d: Float = 1.0f
    private var canvas: Pixmap? = null
    private var batch: SpriteBatch? = null

    private val scene = Scene()

    override fun create() {
        canvasWidth = Gdx.graphics.width
        canvasHeight = Gdx.graphics.height

        val aspectRatio = canvasWidth!!.toFloat() / canvasHeight!!.toFloat()
        viewportHeight = 1.0f
        viewportWidth = viewportHeight!! * aspectRatio

        canvas = Pixmap(canvasWidth!!, canvasHeight!!, Pixmap.Format.RGB888)
        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        val origin = Point3(0.0f, 0.0f, 0.0f)
        for (x in 0..canvasWidth!!) {
            for (y in 0..canvasHeight!!) {
                val p = canvasToViewport(x, y)
                val color: Color = traceRay(
                    p1 = origin,
                    p2 = Vector(p),
                    tMin = 1.0f,
                    tMax = Float.POSITIVE_INFINITY,
                    recursionDepth = 3
                )
                canvas?.setColor(color)
                canvas?.drawPixel(x, y)
            }
        }

        val img = Texture(canvas)
        batch?.begin()
        batch?.draw(img, 0.0f, 0.0f)
        batch?.end()
    }

    private fun canvasToViewport(x: Int, y: Int): Point3 {
        val vx = lerp(
            x.toFloat(),
            0.0f,
            -viewportWidth!! / 2.0f,
            canvasWidth!!.toFloat(),
            viewportWidth!! / 2.0f
        )
        val vy = lerp(
            y.toFloat(),
            0.0f,
            viewportHeight!! / 2.0f,
            canvasHeight!!.toFloat(),
            -viewportHeight!! / 2.0f
        )
        return Point3(vx, vy, d)
    }

    private fun traceRay(
        p1: Point3,
        p2: Vector,
        tMin: Float,
        tMax: Float,
        recursionDepth: Int,
    ): Color {
        val (closestT, closestSphere: Sphere?) = closestIntersection(p1, p2, tMin, tMax)
        if (closestSphere == null) {
            return BACKGROUND_COLOR
        }

        val r = Ray(p1, p2)
        val p = r.evaluate(closestT)
        val color = Color(closestSphere.color)
        val n = closestSphere.normalAt(p)
        val intensity = computeLighting(
            p,
            n,
            negate(p2),
            closestSphere.specular
        )
        val localColor = color.mul(intensity)

        val reflective = closestSphere.reflective
        if (recursionDepth <= 0 || reflective <= 0.0f) {
            return localColor
        }

        val reflect = reflect(negate(p2), n)
        val reflectedColor = traceRay(p, reflect, 0.0f, Float.POSITIVE_INFINITY, recursionDepth - 1)
        return localColor.mul(1.0f - reflective).add(reflectedColor.mul(reflective))
    }

    private fun closestIntersection(
        p1: Point3,
        p2: Vector,
        tMin: Float,
        tMax: Float
    ): Pair<Float, Sphere?> {
        var closestT = Float.POSITIVE_INFINITY
        var closestSphere: Sphere? = null

        for (sphere in scene.spheres) {
            val (t1, t2) = sphere.intersect(p1, p2)
            val t = min(t1, t2)
            if (tMin < t && t < tMax && t < closestT) {
                closestT = t
                closestSphere = sphere
            }
        }
        return Pair(closestT, closestSphere)
    }

    private fun computeLighting(p: Point3, n: Vector, v: Vector, s: Int): Float {
        var i = 0.0f

        for (light in scene.lights) {
            val l = light.getLightVectorAt(p)
            if (l != null) {
                val (_, shadowSphere) = closestIntersection(
                    p,
                    l,
                    0.0f,
                    light.t_max
                )
                if (shadowSphere == null)
                    i += light.getIntensityAt(p, n, v, s)
            } else {
                i += light.getIntensityAt(p, n, v, s)
            }
        }

        return i
    }

    override fun dispose() {
        canvas?.dispose()
    }
}

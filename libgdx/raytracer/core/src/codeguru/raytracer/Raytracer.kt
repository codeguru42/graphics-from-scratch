package codeguru.raytracer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class Raytracer : ApplicationAdapter() {
    private var BACKGROUND_COLOR = Color.BLACK

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
        val origin = Point(0.0f, 0.0f, 0.0f)
        for (x in 0..canvasWidth!!) {
            for (y in 0..canvasHeight!!) {
                val p = canvasToViewport(x, y)
                val color: Color = traceRay(origin, p, 1.0f, Float.POSITIVE_INFINITY)
                canvas?.setColor(color)
                canvas?.drawPixel(x, y)
            }
        }

        val img = Texture(canvas)
        batch?.begin()
        batch?.draw(img, 0.0f, 0.0f)
        batch?.end()
    }

    private fun canvasToViewport(x: Int, y: Int): Point {
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
            -viewportHeight!! / 2.0f,
            canvasHeight!!.toFloat(),
            viewportHeight!! / 2.0f
        )
        return Point(vx, vy, d)
    }

    private fun traceRay(p1: Point, p2: Point, tMin: Float, tMax: Float): Color {
        var closestT = Float.POSITIVE_INFINITY
        var closestSphere: Sphere? = null

        for (sphere in scene.spheres) {
            val (t1, t2) = sphere.intersect(p1, p2)
            if (tMin < t1 && t1 < tMax && t1 < closestT) {
                closestT = t1
                closestSphere = sphere
            }
            if (tMin < t2 && t2 < tMax && t2 < closestT) {
                closestT = t2
                closestSphere = sphere
            }
        }
        if (closestSphere == null) {
            return BACKGROUND_COLOR
        }
        return closestSphere.color
    }

    override fun dispose() {
        canvas?.dispose()
    }
}

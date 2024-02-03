package codeguru.raytracer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class Raytracer : ApplicationAdapter() {
    private var canvasWidth: Int? = null
    private var canvasHeight: Int? = null
    private var viewportWidth: Float? = null
    private var viewportHeight: Float? = null
    private val d: Float = 10.0f
    private var canvas: Pixmap? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        canvasWidth = Gdx.graphics.width
        canvasHeight = Gdx.graphics.height

        var aspectRatio = canvasWidth!!.toFloat() / canvasHeight!!.toFloat()
        viewportHeight = 100.0f
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
        return Point(x * viewportWidth!! / canvasWidth!!, y * viewportHeight!! / canvasHeight!!, d)
    }

    private fun traceRay(p1: Point, p2: Point, tMin: Float, tMax: Float): Color {
        return Color.BLUE
    }

    override fun dispose() {
        canvas?.dispose()
    }
}

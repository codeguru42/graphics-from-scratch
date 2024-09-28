package codeguru.rasterizer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Rasterizer : ApplicationAdapter() {
    private var canvasWidth: Int? = null
    private var canvasHeight: Int? = null
    private var viewportWidth: Float? = null
    private var viewportHeight: Float? = null
    private var canvas: Canvas? = null

    override fun create() {
        canvasWidth = Gdx.graphics.width
        canvasHeight = Gdx.graphics.height

        val aspectRatio = canvasWidth!!.toFloat() / canvasHeight!!.toFloat()
        viewportHeight = 480.0f
        viewportWidth = viewportHeight!! * aspectRatio

        canvas = Canvas(canvasWidth!!, canvasHeight!!)
    }

    override fun render() {
        ScreenUtils.clear(1.0f, 1.0f, 1.0f, 1.0f)

        val p1 = worldToCanvas(Point2(-200, -100))
        val p2 = worldToCanvas(Point2(240, 120))
        canvas?.drawLine(p1, p2, Color.WHITE)
        canvas?.render()
    }

    override fun dispose() {
        canvas?.dispose()
    }

    private fun worldToCanvas(p: Point2): Point2 {
        val vw = viewportWidth!!
        val vh = viewportHeight!!
        val cw = canvasWidth!!.toFloat()
        val ch = canvasHeight!!.toFloat()

        val x = lerp(p.x.toFloat(), -vw / 2.0f, 0.0f, vw / 2.0f, cw)
        val y =
            lerp(p.y.toFloat(), -vh / 2.0f, ch, vh / 2.0f, 0.0f)
        return Point2(x.toInt(), y.toInt())
    }
}

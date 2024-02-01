package codeguru.raytracer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class Raytracer : ApplicationAdapter() {
    private val VIEWPORT_WIDTH: Float = 80.0f
    private val VIEWPORT_HEIGHT: Float = 60.0f
    private val CANVAS_WIDTH = 640
    private val CANVAS_HEIGHT = 480

    private var camera: Camera? = null
    private var canvas: Pixmap? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        camera = OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)
        canvas = Pixmap(CANVAS_WIDTH, CANVAS_HEIGHT, Pixmap.Format.RGB888)
        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        canvas?.setColor(Color(1.0f, 0.0f, 0.0f, 1.0f))
        canvas?.drawCircle(0, 0, 480)
        canvas?.setColor(Color(0.0f, 1.0f, 0.0f, 1.0f))
        canvas?.drawRectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT)
        canvas?.setColor(Color(0.0f, 0.0f, 1.0f, 1.0f))
        canvas?.drawLine(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT)

        val img = Texture(canvas)
        batch?.begin()
        batch?.draw(img, 0.0f, 0.0f)
        batch?.end()
    }

    override fun dispose() {
        canvas?.dispose()
    }
}

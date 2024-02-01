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
    private var canvas: Pixmap? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        canvasWidth = Gdx.graphics.width
        canvasHeight = Gdx.graphics.height
        canvas = Pixmap(canvasWidth!!, canvasHeight!!, Pixmap.Format.RGB888)
        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        canvas?.setColor(Color(1.0f, 0.0f, 0.0f, 1.0f))
        canvas?.drawCircle(0, 0, 480)
        canvas?.setColor(Color(0.0f, 1.0f, 0.0f, 1.0f))
        canvas?.drawRectangle(0, 0, canvasWidth!!, canvasHeight!!)
        canvas?.setColor(Color(0.0f, 0.0f, 1.0f, 1.0f))
        canvas?.drawLine(0, 0, canvasWidth!!, canvasHeight!!)

        val img = Texture(canvas)
        batch?.begin()
        batch?.draw(img, 0.0f, 0.0f)
        batch?.end()
    }

    override fun dispose() {
        canvas?.dispose()
    }
}

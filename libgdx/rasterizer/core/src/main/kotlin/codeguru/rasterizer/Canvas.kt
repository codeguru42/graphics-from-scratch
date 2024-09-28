package codeguru.rasterizer

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Canvas(width: Int, height: Int) {
    private val batch: Batch = SpriteBatch()
    private val canvas: Pixmap = Pixmap(width, height, Pixmap.Format.RGB888)

    fun render() {
        val img = Texture(canvas)
        batch.begin()
        batch.draw(img, 0.0f, 0.0f)
        batch.end()
    }

    fun dispose() {
        batch.dispose()
        canvas.dispose()
    }

    fun drawLine(p0: Point2, p1: Point2, color: Color) {
        canvas.setColor(color)
        val a = (p1.y - p0.y).toFloat() / (p1.x - p0.x).toFloat()
        val b = p0.y - a * p0.x
        for (x in p0.x..p1.x) {
            val y = a * x + b
            canvas.drawPixel(x, y.toInt())
        }
    }
}

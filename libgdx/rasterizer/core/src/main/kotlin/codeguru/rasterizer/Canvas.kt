package codeguru.rasterizer

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap

class Canvas(width: Int, height: Int) {
    private var canvas: Pixmap = Pixmap(width, height, Pixmap.Format.RGB888)

    fun drawLine(p0: Point2, p1: Point2, color: Color) {
        canvas.setColor(color)
        val a = (p1.y - p0.y) / (p1.x - p0.x)
        val b = p0.y - a * p0.x
        for (x in p0.x..p1.x) {
            val y = a * x + b
            canvas.drawPixel(x, y)
        }
    }
}

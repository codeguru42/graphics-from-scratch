package codeguru.rasterizer

fun lerp(x: Float, x1: Float, y1: Float, x2: Float, y2: Float): Float {
    val m = (y2 - y1) / (x2 - x1)
    return m * (x - x1) + y1
}

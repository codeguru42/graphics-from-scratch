package codeguru.raytracer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils

class Raytracer : ApplicationAdapter() {
    private val VIEWPORT_WIDTH: Float = 80.0f
    private val VIEWPORT_HEIGHT: Float = 60.0f

    private var camera: Camera? = null

    override fun create() {
        camera = OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
    }

    override fun dispose() {
    }
}

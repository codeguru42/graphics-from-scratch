package codeguru.raytracer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class Raytracer : ApplicationAdapter() {
    private var camera: Camera? = null

    override fun create() {
        camera = OrthographicCamera()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
    }

    override fun dispose() {
    }
}

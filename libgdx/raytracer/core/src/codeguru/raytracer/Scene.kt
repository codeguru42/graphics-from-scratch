package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

class Scene {
    val spheres: List<Sphere> = listOf(
        Sphere(Point(0.0f, -1.0f, 3.0f), 1.0f, Color.RED),
        Sphere(Point(2.0f, 0.0f, 4.0f), 1.0f, Color.BLUE),
        Sphere(Point(-2.0f, 0.0f, 4.0f), 1.0f, Color.GREEN),
    )

    val lights: List<Light> = listOf(
        AmbientLight(0.2f),
        PointLight(0.6f, Point(2.0f, 1.0f, 0.0f)),
        DirectionalLight(0.2f, Vector(1.0f, 4.0f, 4.0f)),
    )
}

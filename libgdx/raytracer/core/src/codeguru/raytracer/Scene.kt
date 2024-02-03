package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

class Scene {
    val spheres: List<Sphere> = listOf(
        Sphere(Point(-50.0f, 0.0f, 0.0f), 25.0f, Color.RED),
        Sphere(Point(0.0f, 0.0f, 0.0f), 25.0f, Color.BLUE),
        Sphere(Point(50.0f, 0.0f, 0.0f), 25.0f, Color.GREEN),
    )
}

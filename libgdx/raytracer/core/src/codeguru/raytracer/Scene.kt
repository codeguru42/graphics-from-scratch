package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

class Scene {
    val spheres: List<Sphere> = listOf(
        Sphere(Point(0.0f, -1.0f, 3.0f), 1.0f, Color.RED),
        Sphere(Point(2.0f, 0.0f, 4.0f), 1.0f, Color.BLUE),
        Sphere(Point(-2.0f, 0.0f, 4.0f), 1.0f, Color.GREEN),
    )
}

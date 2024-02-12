package codeguru.raytracer

import com.badlogic.gdx.graphics.Color

class Scene {
    val spheres: List<Sphere> = listOf(
        Sphere(
            center = Point3(x = 0.0f, y = -1.0f, z = 3.0f),
            radius = 1.0f,
            color = Color.RED,
            specular = 500,
        ),
        Sphere(
            center = Point3(x = 2.0f, y = 0.0f, z = 4.0f),
            radius = 1.0f,
            color = Color.BLUE,
            specular = 500,
        ),
        Sphere(
            center = Point3(-2.0f, 0.0f, 4.0f),
            radius = 1.0f,
            color = Color.GREEN,
            specular = 10,
        ),
        Sphere(
            center = Point3(x = 0.0f, y = -5001.0f, z = 0.0f),
            radius = 5000.0f,
            color = Color.YELLOW,
            specular = 1000,
        ),
    )

    val lights: List<Light> = listOf(
        AmbientLight(
            intensity = 0.2f
        ),
        PointLight(
            intensity = 0.6f,
            position = Point3(2.0f, 1.0f, 0.0f)
        ),
        DirectionalLight(
            intensity = 0.2f,
            direction = Vector(1.0f, 4.0f, 4.0f)
        ),
    )
}

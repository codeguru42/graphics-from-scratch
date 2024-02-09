package codeguru.raytracer

interface Light

class AmbientLight(val intensity: Float) : Light
class PointLight(val intensity: Float, position: Point) : Light
class DirectionalLight(val intensity: Float, direction: Vector) : Light

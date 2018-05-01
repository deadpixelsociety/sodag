package com.thedeadpixelsociety.sodag.verlet

import com.badlogic.gdx.math.Vector2

data class Collision(
        val vertex: Vertex,
        val edge: Edge,
        val normal: Vector2 = Vector2(),
        val depth: Float = 0f
) {
    companion object {
        val NONE = Collision(Vertex(), Edge(Vertex(), Vertex()))
    }
}
package com.thedeadpixelsociety.sodag.verlet

import com.badlogic.gdx.math.Vector2

data class Vertex(
        val body: Body,
        val position: Vector2 = Vector2(0f, 0f),
        val lastPosition: Vector2 = Vector2(0f, 0f),
        val acceleration: Vector2 = Vector2(0f, 0f)
) {
    companion object {
        val EMPTY = Vertex(Body.EMPTY)
    }
}
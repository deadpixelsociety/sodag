package com.thedeadpixelsociety.sodag.verlet

import com.badlogic.gdx.math.Vector2

class Verlet {
    private val vertices = arrayListOf<Vertex>()
    private val edges = arrayListOf<Edge>()
    private val v0 = Vector2()
    private val v1 = Vector2()

    fun update(dt: Float) {
        
    }

    private fun updateEdge(edge: Edge, dt: Float) {
        v0.set(edge.b.position).sub(edge.a.position)
        val len = v0.len()
        val diff = edge.length - len
        v0.nor()
        v1.set(v0.x * diff * .5f, v0.y * diff * .5f)
        edge.a.position.add(v1)
        edge.b.position.sub(v1)
    }

    private fun updateVertex(vertex: Vertex, dt: Float) {
        v0.set(vertex.position)
        v1.set(vertex.position).sub(vertex.lastPosition).add(vertex.acceleration.scl(dt * dt))
        vertex.position.add(v1)
        vertex.lastPosition.set(v0)
    }
}
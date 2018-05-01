package com.thedeadpixelsociety.sodag.verlet

data class Edge(
        val body: Body,
        val a: Vertex,
        val b: Vertex,
        val length: Float = 1f
) {
    companion object {
        val EMPTY = Edge(Body.EMPTY, Vertex.EMPTY, Vertex.EMPTY)
    }
}
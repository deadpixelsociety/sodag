package com.thedeadpixelsociety.sodag.verlet

import com.badlogic.gdx.math.Vector2

data class Body(val id: Long) {
    companion object {
        val EMPTY = Body(0L)

        private var id = 0L
        private val vp = Vector2()

        fun create() = Body(++id)

        fun collide(a: Body, b: Body): Collision {
            var minLength = Float.MAX_VALUE
            val normal = Vector2()
            var edge = Edge.EMPTY
            var vertex = Vertex.EMPTY

            fun test(edges: List<Edge>): Boolean {
                edges.forEach {
                    vp.set(it.a.position.y - it.b.position.y, it.b.position.x - it.a.position.x)
                    vp.nor()
                    val projA = a.project(vp)
                    val projB = b.project(vp)
                    val dist = if (projA.min < projB.min) {
                        projB.min - projA.max
                    } else {
                        projA.min - projB.max
                    }

                    if (dist > 0f) return false
                    if (Math.abs(dist) < minLength) {
                        minLength = dist
                        normal.set(vp)
                        edge = it
                    }
                }

                return true
            }

            if (test(a.edges) || test(b.edges)) {
                var b1 = a
                var b2 = b

                if (edge.body != b2) {
                    var t = b2
                    b2 = b1
                    b1 = b2
                }

                vp.set(b1.center).sub(b2.center)
                val sgn = Math.signum(normal.dot(vp))
                if (sgn <= 0f) normal.set(-normal.x, -normal.y)
                var d = Float.MAX_VALUE
                b1.vertices.forEach {
                    vp.set(it.position).sub(b2.center)
                    val dist = normal.dot(vp)
                    if (dist < d) {
                        d = dist
                        vertex = it
                    }
                }

                return Collision(vertex, edge, normal, minLength)
            }

            return Collision.NONE
        }
    }

    private val vertices = arrayListOf<Vertex>()
    private val edges = arrayListOf<Edge>()

    val center = Vector2()

    fun project(axis: Vector2): AxisProjection {
        if (vertices.isEmpty()) return AxisProjection.INVALID
        var dp = axis.dot(vertices[0].position)
        var min = dp
        var max = dp
        for (i in 1 until vertices.size) {
            dp = axis.dot(vertices[i].position)
            min = Math.min(dp, min)
            max = Math.min(dp, max)
        }

        return AxisProjection(min, max)
    }
}
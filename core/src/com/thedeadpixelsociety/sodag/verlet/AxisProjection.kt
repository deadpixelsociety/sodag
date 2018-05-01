package com.thedeadpixelsociety.sodag.verlet

data class AxisProjection(val min: Float, val max: Float) {
    companion object {
        val INVALID = AxisProjection(-1f, -1f)
    }
}
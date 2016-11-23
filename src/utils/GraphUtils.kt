package utils

/**
 * Created by Тарас on 28.09.2016.
 */

import figures.Point

import java.util.ArrayList
import java.util.LinkedList

object GraphUtils {
    fun getXCoordinateArc(center: Int, degree: Double, radius: Int): Int {
        return (center + Math.sin(degree * (Math.PI / 180)) * radius).toInt()
    }

    fun getYCoordinateArc(center: Int, degree: Double, radius: Int): Int {
        return (center + Math.cos(degree * (Math.PI / 180)) * radius).toInt()
    }

    fun getArcPoints(startDegree: Int, endDegree: Int, center: Point, radius: Int): LinkedList<Point> {
        val points = LinkedList<Point>()

        val xCenter = center.x
        val yCenter = center.y

        var a = startDegree + 0.0

        while (a <= endDegree) {
            val x = GraphUtils.getXCoordinateArc(xCenter, a, radius)
            val y = GraphUtils.getYCoordinateArc(yCenter, a, radius)

            points.add(Point(x, y))
            a += 0.1
        }

        return points
    }

    @Throws(Exception::class)
    fun rotateMatrixFactory(axis: String, rotateDegree: Double): Array<DoubleArray> {
        when (axis) {
            "X" -> {
                return arrayOf(doubleArrayOf(1.0, 0.0, 0.0, 0.0), doubleArrayOf(0.0, Math.cos(rotateDegree), Math.sin(rotateDegree), 0.0), doubleArrayOf(0.0, -Math.sin(rotateDegree), Math.cos(rotateDegree), 0.0), doubleArrayOf(0.0, 0.0, 0.0, 1.0))
            }
            "Y" -> {
                return arrayOf(doubleArrayOf(Math.cos(rotateDegree), 0.0, -Math.sin(rotateDegree), 0.0), doubleArrayOf(0.0, 1.0, 0.0, 0.0), doubleArrayOf(Math.sin(rotateDegree), 0.0, Math.cos(rotateDegree), 0.0), doubleArrayOf(0.0, 0.0, 0.0, 1.0))
            }
            "Z" -> {
                return arrayOf(doubleArrayOf(Math.cos(rotateDegree), Math.sin(rotateDegree), 0.0, 0.0), doubleArrayOf(-Math.sin(rotateDegree), Math.cos(rotateDegree), 0.0, 0.0), doubleArrayOf(0.0, 0.0, 1.0, 0.0), doubleArrayOf(0.0, 0.0, 0.0, 1.0))
            }
            else -> {
                throw Exception()
            }
        }
    }
}

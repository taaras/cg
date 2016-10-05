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
}

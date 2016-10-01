package utils

/**
 * Created by Тарас on 28.09.2016.
 */

import figures.Point

import java.util.ArrayList
import java.util.LinkedList

object GraphUtils {
    fun getXCoordinateArc(center: Int, degree: Int, radius: Int): Int {
        return (center + Math.sin(degree * (Math.PI / 180)) * radius).toInt()
    }

    fun getYCoordinateArc(center: Int, degree: Int, radius: Int): Int {
        return (center + Math.cos(degree * (Math.PI / 180)) * radius).toInt()
    }

    fun getArcPoints(startDegree: Int, endDegree: Int, center: Point, radius: Int): LinkedList<Point> {
        val points = LinkedList<Point>()

        val xCenter = center.x
        val yCenter = center.y

        for (i in startDegree..endDegree) {
            val x = GraphUtils.getXCoordinateArc(xCenter, i, radius)
            val y = GraphUtils.getYCoordinateArc(yCenter, i, radius)

            points.add(Point(x, y))
        }

        return points
    }
}

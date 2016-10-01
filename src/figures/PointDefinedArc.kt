package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import java.util.*

class PointDefinedArc(radius: Int, center: Point, var startPoint: Point, var endPoint: Point) : Arc(radius, center, 0, 360) {

    override fun definePoints() {
        if (points == null) {
            super.definePoints()
        }

        val resultPoints = LinkedList<Point>()

        var i = 0
        while (i < points.size) {
            if (points[i] == startPoint) {
                while (points[i] != endPoint) {
                    resultPoints.add(Point(points[i].x, points[i].y))

                    i = if (i == 1) points.size - 1 else i - 1
                }

                break
            }
            i++
        }

        points = resultPoints
    }
}

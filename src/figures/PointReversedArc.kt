package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import java.util.*

class PointReversedArc(radius: Int, center: Point, protected var startPoint: Point, protected var endPoint: Point) : Arc(radius, center, 0, 360) {

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

                    i = if (i == points.size - 1) 1 else i + 1
                }

                break
            }
            i++
        }

        points = resultPoints
    }
}

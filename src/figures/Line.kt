package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import java.awt.*
import java.util.*

class Line(startPoint: Point, endPoint: Point) : Figure() {

    override var points: LinkedList<Point>
        get() {
            var pointList = LinkedList<Point>()
            pointList.add(startPoint)
            pointList.add(endPoint)
            return pointList
        }
        set(value) {
            points = value
        }

    var startPoint: Point
    var endPoint: Point

    init {
        this.startPoint = Point(startPoint.x, startPoint.y)
        this.endPoint = Point(endPoint.x, endPoint.y)
    }

    override fun definePoints() {
    }

    //refactor
    fun findIntersectionDot(inputPoints: List<Point>): List<Point> {
        val startX = startPoint.x
        val startY = startPoint.y
        val endX = endPoint.x
        val endY = endPoint.y
        val difX = endX - startX
        val difY = endY - startY

        val outPoints = ArrayList<Point>()

        for (point in inputPoints) {

            val left = Math.abs((0.0 + point.x - startX) / difX)
            val right = Math.abs((0.0 + point.y - startY) / difY)

            if (Math.abs(left - right) < 0.01) {
                outPoints.add(point)
            }
        }

        return outPoints
    }

    override fun draw(g: Graphics): List<Point> {
        definePoints()
        val g2d = createPainter(g)

        g2d.drawLine(startPoint.x, startPoint.y,
                endPoint.x, endPoint.y)

        val points = LinkedList<Point>()
        points.add(startPoint)
        points.add(endPoint)

        return points

    }

    override fun shiftDots(x: Int, y: Int) {
        // startPoint.setLocation(startPoint.getX() + x, startPoint.getY() + y);
        startPoint.x = startPoint.x + x
        startPoint.y = startPoint.y + y
        endPoint.x = endPoint.x + x
        endPoint.y = endPoint.y + y
        // endPoint.setLocation(endPoint.getX() + x, endPoint.getY() + y);
    }

    private fun createPainter(g: Graphics): Graphics2D {
        val g2d = g as Graphics2D

        g2d.stroke = BasicStroke(3f)
        g2d.color = Color.BLUE

        return g2d
    }
}

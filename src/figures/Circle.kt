package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import utils.GraphUtils

import java.awt.*
import java.util.*

open class Circle(open var radius: Int, center: Point) : Figure() {
    var center: Point
    override var points: LinkedList<Point>
        get() {
            if (false) {
                definePoints()
            }
            return super.points
        }
        set(value) {
            for(point in value)
                points.add(point)
        }

    init {
        this.center = Point(center.x, center.y)
    }

    override fun definePoints() {
        points = GraphUtils.getArcPoints(START_CIRCLE_DEGREE, END_CIRCLE_DEGREE, center, radius)
    }

    override fun draw(g: Graphics): List<Point> {
        if (points.isEmpty()) {
            definePoints()
        }

        for (i in 1..points.size - 1) {
            val g2d = createPainter(g)
            g2d.drawLine(points[i - 1].x, points[i - 1].y,
                    points[i].x, points[i].y)
        }

        return points
    }

    override fun shiftDots(x: Int, y: Int) {
        center.x = center.x + x
        center.y = center.y + y

        for (point in points) {
            point.x = point.x + x
            point.y = point.y + y
        }
    }

    private fun createPainter(g: Graphics): Graphics2D {
        val g2d = g as Graphics2D

        g2d.stroke = BasicStroke(3f)
        g2d.color = Color.BLUE

        return g2d
    }

    companion object {
        private val START_CIRCLE_DEGREE = 0
        private val END_CIRCLE_DEGREE = 360
    }
}

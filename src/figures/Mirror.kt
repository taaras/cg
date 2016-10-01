package figures

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.util.*

/**
 * Created by Тарас on 29.09.2016.
 */
class Mirror() : Figure() {

    constructor (points: LinkedList<Point>) : this(){
        this.points = points.clone() as LinkedList<Point>
    }

    override var points: LinkedList<Point>
        get() = super.points
        set(value) {
            for(point in value)
                points.add(point)
        }

    override fun definePoints() {
    }

    override fun draw(g: Graphics): List<Point> {
        if (points.isEmpty()) {
            definePoints()
        }

        for (i in 1..points!!.size - 1) {
            val g2d = createPainter(g)
            g2d.drawLine(points!![i - 1].x, points!![i - 1].y, points!![i].x, points!![i].y)
        }

        return points
    }

    private fun createPainter(g: Graphics): Graphics2D {
        val g2d = g as Graphics2D

        g2d.stroke = BasicStroke(3f)
        g2d.color = Color.BLUE

        return g2d
    }

    override fun shiftDots(x: Int, y: Int) {
        for (point in points!!) {
            point.x = point.x + x
            point.y = point.y + y
        }
    }
}
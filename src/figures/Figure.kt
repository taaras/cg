package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import java.awt.*
import java.util.*

abstract class Figure {



    open var points = LinkedList<Point>()
    set (points){
        val newPoints = LinkedList<Point>()

        for (point in points!!) {
            newPoints.add(Point(point.x, point.y))
        }

        this.points = newPoints
    }
    var shiftX: Int = 0
    var shiftY: Int = 0

    constructor(points: LinkedList<Point>) {
        this.points = points
    }

    abstract fun definePoints()

    abstract fun draw(g: Graphics): List<Point>

    fun mirror(line: Line): Mirror{
        if(points.isEmpty()){
            definePoints()
        }

        var newPoints = LinkedList<Point>()
        if (line.startPoint!!.x == line.endPoint!!.x){
            for(p in points){
                newPoints.add(Point(2*line.startPoint!!.x - p.x, p.y))
            }
            return Mirror(newPoints)
        }

        var k = (line.startPoint!!.y - line.endPoint!!.y)/(line.startPoint!!.x - line.endPoint!!.x)
        var b = line.endPoint!!.y - line.endPoint!!.x * k

        for(p in points){
            val cx = (-b + p.y + p.x / k)/(k + 1 / k)
            val cy = k * cx + b

            newPoints.add(Point(-p.y + 2 * cy, -p.x + 2 * cx))
        }
        return Mirror(newPoints)
    }

    fun moveDots(x: Int, y: Int) {
        if (points != null) {
            for (point in points!!) {
                //point.setBounds(point.getX() + x, point.getY() + y, 1 ,1);
                //point.setLocation(point.getX() + x, point.getY() + y);
            }
        }
    }

    fun getMovedDots(x: Int, y: Int): LinkedList<Point>? {
        if (points != null) {
            val movedDots = LinkedList<Point>()

            for (point in points!!) {
                movedDots.add(Point(point.x + x, point.y + y))
            }

            return movedDots
        }

        return null
    }

    abstract fun shiftDots(x: Int, y: Int)

    constructor()
}

package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import java.awt.*
import java.util.*

class Point(xCoordinate: Int, yCoordinate: Int) : Figure() {
    var x: Int = 0
    var y: Int = 0

    override var points: LinkedList<Point>
        get(){
            return object : LinkedList<Point>() {
                init {
                    add(this@Point)
                }
            }
        }
        set(value) {
            points = value
        }

    init {
        // setBounds(xCoordinate, yCoordinate, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        x = xCoordinate
        y = yCoordinate
    }

    override fun equals(obj: Any?): Boolean {
        if (obj !is Point) {
            return false
        }

        return (this.x == obj.x || this.x == obj.x - 1 || this.x == obj.x + 1) && (this.y == obj.y || this.y == obj.y + 1 || this.y == obj.y - 1)
    }

    override fun definePoints() {

    }

    override fun draw(g: Graphics): List<Point> {
        g.drawOval(x, y, 1, 1)
        return object : ArrayList<Point>() {
            init {
                add(Point(x, y))
            }
        }
    }

    override fun shiftDots(x: Int, y: Int) {
        this.x += x
        this.y += y
    }

    companion object {
        private val DEFAULT_WIDTH = 1
        private val DEFAULT_HEIGHT = 1
    }
}

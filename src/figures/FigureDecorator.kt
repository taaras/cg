package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import canvases.FigureCanvas
import utils.MatrixUtils

import java.awt.*
import java.util.*

class FigureDecorator(var canvas: FigureCanvas?) {
    private var figures: LinkedList<Figure>
    private val definedPoints: LinkedList<Point>

    init {
        this.figures = LinkedList<Figure>()
        this.definedPoints = LinkedList<Point>()
    }

    fun draw(g: Graphics) {
        for (figure in figures) {
            figure.draw(g)
        }
    }

    infix fun add(figure: Figure) {
        figures.add(figure)
    }

    fun add(list: Collection<Figure>){
        for(figure in list){
            figures.add(figure)
        }
    }

    fun addPoints(point: List<Point>) {
        definedPoints.addAll(point)
    }

    fun shift(x: Int, y: Int) {
        for (figure in figures) {
            figure.shiftDots(x, y)
        }

        for (point in definedPoints) {
            point.shiftDots(x, y)
        }
    }

    fun rotateXAxis(degree: Int) {
        for (figure in figures) {
            for (point in figure.points!!) {
                val newX = (Math.cos(degree * (Math.PI / 180)) * point.x - Math.sin(degree * (Math.PI / 180)) * point.y).toInt()

                val newY = (Math.sin(degree * (Math.PI / 180)) * point.x + Math.cos(degree * (Math.PI / 180)) * point.y).toInt()

                point.y = newY
                point.x = newX
            }
        }
    }

    fun rotatePoint(degree: Int, pointRotate: Point) {
        val pointValue = degree * (Math.PI / 180)

        for (figure in figures!!) {
            for (point in figure.points!!) {
                val first = arrayOf(doubleArrayOf(point.x.toDouble(), point.y.toDouble(), 1.0))
                val second = arrayOf(doubleArrayOf(1.0, 0.0, 0.0), doubleArrayOf(0.0, 1.0, 0.0), doubleArrayOf((-pointRotate.x).toDouble(), (-pointRotate.y).toDouble(), 1.0))
                val third = arrayOf(doubleArrayOf(Math.cos(pointValue), Math.sin(pointValue), 0.0), doubleArrayOf(-Math.sin(pointValue), Math.cos(pointValue), 0.0), doubleArrayOf(0.0, 0.0, 1.0))
                val fourth = arrayOf(doubleArrayOf(1.0, 0.0, 0.0), doubleArrayOf(0.0, 1.0, 0.0), doubleArrayOf(pointRotate.x.toDouble(), pointRotate.y.toDouble(), 1.0))

                val result1 = MatrixUtils.multiply(first, second)
                val result2 = MatrixUtils.multiply(result1, third)
                val result3 = MatrixUtils.multiply(result2, fourth)

                point.x = result3[0][0].toInt()
                point.y = result3[0][1].toInt()
            }
        }
    }

    val points: List<Point>
        get() {
            val points = ArrayList<Point>()

            for (figure in figures!!) {
                points.addAll(figure.points!!)
            }

            return points
        }

    fun add() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

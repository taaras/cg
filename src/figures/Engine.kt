package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import canvases.FigureCanvas
import utils.MatrixUtils

import java.awt.*
import java.util.*

class Engine(var canvas: FigureCanvas?) {
    private var figures = LinkedList<Figure>()
    private var grid = LinkedList<Line>()
    private var centerLines = LinkedList<Line>()

    private var transfomationMatrix = arrayOf(
            doubleArrayOf(0.7, 0.0, 0.0),
            doubleArrayOf(0.0, 1.2, 0.0),
            doubleArrayOf(0.0, 0.0, 1.0)
    )

    val points: List<Point>
        get() {
            val points = ArrayList<Point>()

            for (figure in figures) {
                points.addAll(figure.points)
            }

            return points
        }

    fun transform(){
        for(figure in figures){
            for(point in figure.points){
                var coordinates = arrayOf(doubleArrayOf(point.x+0.0, point.y+0.0, 1.0))
                var result = MatrixUtils.multiply(coordinates, transfomationMatrix)
                point.x = result[0][0].toInt()
                point.y = result[0][1].toInt()
            }
        }

        for(line in centerLines){
            for(point in line.points){
                var coordinates = arrayOf(doubleArrayOf(point.x+0.0, point.y+0.0, 1.0))
                var result = MatrixUtils.multiply(coordinates, transfomationMatrix)
                point.x = result[0][0].toInt()
                point.y = result[0][1].toInt()
            }
        }

        for(line in grid){
            for(point in line.points){
                var coordinates = arrayOf(doubleArrayOf(point.x+0.0, point.y+0.0, 1.0))
                var result = MatrixUtils.multiply(coordinates, transfomationMatrix)
                point.x = result[0][0].toInt()
                point.y = result[0][1].toInt()
            }
        }
    }

    fun reset(){
        figures = LinkedList<Figure>()
        grid = LinkedList<Line>()
        centerLines = LinkedList<Line>()
    }

    fun draw(g: Graphics) {
        transform()

        drawGrid(g)
        drawCenterlines(g)

        for (figure in figures) {
            figure.draw(g)
        }
    }

    private fun drawGrid(g: Graphics) {
        val g2d = g as Graphics2D
        g2d.color = Color.GRAY
        g2d.stroke = BasicStroke(0.1f)

        for(line in grid){
            val sp = line.startPoint
            val ep = line.endPoint
            g2d.drawLine(sp.x, sp.y, ep.x, ep.y)
        }
    }

    private fun drawCenterlines(g: Graphics) {
        val g2d = g as Graphics2D
        g2d.color = Color.BLACK
        val dash = floatArrayOf(10.0f)
        g2d.stroke = BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f)

        for(line in centerLines){
            val sp = line.startPoint
            val ep = line.endPoint
            g2d.drawLine(sp.x, sp.y, ep.x, ep.y)
        }
    }

    fun addCenterLine(line: Line){
        centerLines.add(line)
    }

    fun addGridLine(line: Line){
        grid.add(line)
    }

    infix fun add(figure: Figure) {
        figures.add(figure)
    }

    fun add(list: Collection<Figure>){
        for(figure in list){
            figures.add(figure)
        }
    }

/*    fun addPoints(point: List<Point>) {
        definedPoints.addAll(point)
    }

    fun shift(x: Int, y: Int) {
        for (figure in engine) {
            figure.shiftDots(x, y)
        }

        for (point in definedPoints) {
            point.shiftDots(x, y)
        }
    }

    fun rotateXAxis(degree: Int) {
        for (figure in engine) {
            for (point in figure.points) {
                val newX = (Math.cos(degree * (Math.PI / 180)) * point.x - Math.sin(degree * (Math.PI / 180)) * point.y).toInt()

                val newY = (Math.sin(degree * (Math.PI / 180)) * point.x + Math.cos(degree * (Math.PI / 180)) * point.y).toInt()

                point.y = newY
                point.x = newX
            }
        }
    }

    fun rotatePoint(degree: Int, pointRotate: Point) {
        val pointValue = degree * (Math.PI / 180)

        for (figure in engine) {
            for (point in figure.points) {
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
    }*/

    fun add() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

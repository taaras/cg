package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import canvases.FigureCanvas
import utils.GraphUtils
import utils.MatrixUtils

import java.awt.*
import java.util.*

class Engine(var canvas: FigureCanvas?) {
    private var figures = LinkedList<Figure>()
    private var grid = LinkedList<Line>()
    private var centerLines = LinkedList<Line>()

    val points: List<Point>
        get() {
            val points = ArrayList<Point>()

            for (figure in figures) {
                points.addAll(figure.points)
            }

            return points
        }

    fun affinedTransform(){
        val transfomationMatrix = arrayOf(
                doubleArrayOf(0.7, 0.0, 0.0),
                doubleArrayOf(0.0, 1.2, 0.0),
                doubleArrayOf(0.0, 0.0, 1.0)
        )

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


    fun defineGrid() {
        var i = 0
        while (i < canvas!!.height + 1) {
            addGridLine(Line(Point(0, i), Point(canvas!!.width, i)))
            i += FigureCanvas.gridStep
        }

        i = 0
        while (i < canvas!!.width) {
            addGridLine(Line(Point(i, 0), Point(i, canvas!!.height)))
            i += FigureCanvas.gridStep
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

    fun euclidianTransform(shiftX: Int, shiftY: Int, degree: Int, pointX: Int, pointY: Int) {
        shift(shiftX, shiftY)
        rotatePoint(degree, Point(pointX, pointY))
    }

    fun shift(x: Int, y: Int) {
        var pts = ArrayList<Point>()
        for (figure in figures) {
            figure.shiftDots(x, y)
            pts.addAll(figure.points)
        }

        for (point in pts) {
            point.shiftDots(x, y)
        }

        /* for (Point point : canvas.getMainCenterline().getAllPoints()) {
            point.shiftDots(x, y);
        }*/
    }

    fun proectiveTransform(a: Double, b: Double, c: Double, d: Double, e: Double, f: Double, w1: Double, w2: Double, w3: Double) {
        /*for (Figure figure : figures) {
            for (Point point : figure.getPoints()) {
                Double W = point.getX() * w1 + point.getY() * w2 + w3;

                Double newX = ( point.getX() * a + point.getY() * b  + c ) / W;
                Double newY = ( point.getX() * d + point.getY() * e + f ) / W;

                point.setX(newX.intValue());
                point.setY(newY.intValue());
            }
        }*/
        for (point in getTransformingPoints()) {
            val W = point.x * w1 + point.y * w2 + w3

            val newX = (point.x * a + point.y * b + c) / W
            val newY = (point.x * d + point.y * e + f) / W

            point.x = newX.toInt()
            point.y = newY.toInt()
        }
    }

    private fun getTransformingPoints(): ArrayList<Point> {
        var points = ArrayList<Point>()
        for(figure in figures){
            points.addAll(figure.points)
        }
        for(line in grid){
            points.addAll(line.points)
        }
        for(line in centerLines){
            points.addAll(line.points)
        }
        return points
    }

    fun rotatePoint(degree: Int, pointRotate: Point) {
        val pointValue = degree * (Math.PI / 180)

        for (figure in figures) {
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
    }

    fun rotateXAxis(degree: Int, axis: String) {
        for (figure in figures) {
            for (point in figure.points) {
                val first = arrayOf(doubleArrayOf(point.x.toDouble(), point.y.toDouble(), 0.0, 1.0))
                val rotateDegree = degree * (Math.PI / 180)

                var result = arrayOfNulls<DoubleArray>(0)
                try {
                    result = MatrixUtils.multiply(first, GraphUtils.rotateMatrixFactory(axis, rotateDegree)) as Array<DoubleArray?>
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                point.x = result[0]!![0].toInt()
                point.y = result[0]!![1].toInt()
            }
        }
    }

    fun affineTransform(a: Double, b: Double, c: Double, d: Double, e: Double, f: Double) {
        for (point in getTransformingPoints()) {
            val newX = point.x * a + point.y * b + c
            val newY = point.x * d + point.y * e + f
            point.x = newX!!.toInt()
            point.y = newY!!.toInt()
        }
    }

    fun affineTransformByAxis(x0: Int, y0: Int, x1: Int, y1: Int, x2: Int, y2: Int) {
        val newCenter = Point(x0, y0)
        val xDot = Point(x1, y1)
        val yDot = Point(x2, y2)

        val xVector = Point(xDot.x - newCenter.x, xDot.y - newCenter.y)
        val yVector = Point(yDot.x - newCenter.x, yDot.y - newCenter.y)

        val xVectorModule = Math.sqrt((xVector.x * xVector.x + xVector.y * xVector.y).toDouble())
        val yVectorModule = Math.sqrt((yVector.x * yVector.x + yVector.y * yVector.y).toDouble())

        val xRez = doubleArrayOf(xVector.x / xVectorModule, xVector.y / xVectorModule)
        val yRez = doubleArrayOf(yVector.x / yVectorModule, yVector.y / yVectorModule)

        for (point in getTransformingPoints()) {
            point.x = (newCenter.x.toDouble() + point.x * xRez[0] + point.y * yRez[0]).toInt()
            point.y = (newCenter.y.toDouble() + point.x * xRez[1] + point.y * yRez[1]).toInt()
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

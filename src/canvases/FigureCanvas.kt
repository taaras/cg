package canvases

/**
 * Created by Тарас on 28.09.2016.
 */

import figures.*
import figures.Point

import javax.swing.*
import java.awt.*
import java.awt.event.*
import java.lang.Math.*
import java.util.*

class FigureCanvas(var windoW_WIDTH: Int, var windoW_HEIGHT: Int, var mainComponent: Container) : JPanel() {
    var toolbaR_HEIGHT: Int = 0

    var highlighted = false

    var shiftX: Int = 0
    var shiftY: Int = 0
    var shiftDegree: Int = 0

    var canvasPoints: List<Point>? = null
    var figures = FigureDecorator(this)

    var rotate = false
    var rotateType: RotateType? = null
    var rotateDegree = 0
    var rotatePoint: Point? = null

    var R1 = 20
    var R2 = 40
    var R3 = 70
    var R4 = 90
    var R5 = 80
    var R6 = 30
    var A = 40
    var B = 100
    var C = 20
    var D = 70
    var F = 100
    var G = 170
    var H = 90
    var I = 20
    var J = 10
    var K = 60
    var L = 70
    var M = 40
    var N = 40
    var P = 30

    init {
        preferredSize = Dimension(windoW_WIDTH, windoW_HEIGHT)
        canvasPoints = java.util.ArrayList<Point>()

        addMouseListener(object : MouseListener {

            override fun mouseClicked(e: MouseEvent) {
                val optionPane = JOptionPane(" x : " + e.x + " y : " + e.y,
                        JOptionPane.INFORMATION_MESSAGE)

                val dialog = optionPane.createDialog("Info")
                dialog.isAlwaysOnTop = true
                dialog.isVisible = true
            }

            override fun mousePressed(e: MouseEvent) {

            }

            override fun mouseReleased(e: MouseEvent) {}

            override fun mouseEntered(e: MouseEvent) {
            }

            override fun mouseExited(e: MouseEvent) {
            }
        })
    }

    /*fun updateDimension(){
        R1 = k * 4
        R6 = k * 2
        R3 = k * 2
        A = k * 4
        B = k
        C = k * 2
        D = k * 3
        E = k * 2
        F = k * 3
        G = k * 6
        H = k * 2
        I = (k * 1.5).toInt()
    }*/

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        background = Color.WHITE

        drawDashedLine(g)
        //drawAxis(g)
        drawGrid(g)

        definePlotElements()

        if (rotate) {
            when (rotateType) {
                RotateType.AXIS -> {
                    figures.rotateXAxis(rotateDegree)
                }
                RotateType.POINT -> {
                    figures.rotatePoint(rotateDegree, rotatePoint!!)
                }
            }
        }

        if (highlighted) {
            val g2d = g as Graphics2D
            g2d.color = Color.RED
            g2d.stroke = BasicStroke(10f)
            for (point in figures.points) {
                g2d.drawOval(point.x, point.y, 1, 1)
            }
        }

        figures.shift(shiftX, shiftY)
        figures.draw(g)

    }

    private fun definePlotElements() {

        figures = FigureDecorator(this)

        //CENTER_POINT
        val centerPoint = Point(mainComponent.width / 2, mainComponent.height / 2)
        figures.add(centerPoint)

        //TRIANGLE
        val line1 = Line(Point(centerPoint.x, centerPoint.y - C + R3), Point(centerPoint.x + B/2, centerPoint.y - C + R3 - sqrt(B * B * 0.75).toInt()))
        val line2 = Line(line1.endPoint, Point(line1.endPoint.x - B, line1.endPoint.y))
        val line3 = Line(line2.endPoint, line1.startPoint)
        figures.add(line1)
        figures.add(line2)
        figures.add(line3)

        //RIGHT_ARCS
        val arc1 = Arc(R3, centerPoint, 0, 90)
        val arc2 = Arc(R4, centerPoint, 0, 90)
        arc1.definePoints()
        arc2.definePoints()
        val line4 = Line(arc1.points.first, arc2.points.first)
        val line5 = Line(arc1.points.last, arc2.points.last)
        figures.add(arc1)
        figures.add(arc2)
        figures.add(line4)
        figures.add(line5)

        val line6 = Line(Point(centerPoint.x - D, centerPoint.y + R4 + P), Point(centerPoint.x - D + F, centerPoint.y + R4 + P))
        figures.add(line6)

        val arc3 = Arc(R5, Point(line6.endPoint.x, line6.endPoint.y - R5), 0, 90)
        figures.add(arc3)

        arc3.definePoints()
        val line7 = Line(arc3.points.last, Point(arc3.points.last.x, arc3.points.last.y - G))
        figures.add(line7)

        val line13 = Line(Point(line7.endPoint.x - N, (line7.endPoint.y + M - A / sqrt(2.0)).toInt()), Point((line7.endPoint.x - N + A / sqrt(2.0)).toInt(), line7.endPoint.y + M))
        val line14 = Line(line13.endPoint, Point(line13.startPoint.x, (line13.endPoint.y + A / sqrt(2.0)).toInt()))
        val line15 = Line(line14.endPoint, Point((line14.endPoint.x - A / sqrt(2.0)).toInt(), line13.endPoint.y))
        val line16 = Line(line15.endPoint, line13.startPoint)
        figures.add(line13)
        figures.add(line14)
        figures.add(line15)
        figures.add(line16)

        val line8 = Line(line7.endPoint, Point(line7.endPoint.x - H, line7.endPoint.y))
        figures.add(line8)

        val line9 = Line(line8.endPoint, Point(line8.endPoint.x - J, line8.endPoint.y + I))
        figures.add(line9)

        val line10 = Line(line9.endPoint, Point(line9.endPoint.x - K, line9.endPoint.y))
        figures.add(line10)

        val arc4 = Arc(R6, Point(line10.endPoint.x, line10.endPoint.y + R6), 180, 270)
        figures.add(arc4)

        arc4.definePoints()
        val line11 = Line(arc4.points.last, Point(arc4.points.last.x, arc4.points.last.y + L))
        figures.add(line11)

        val l = sqrt(((line11.endPoint.x - line6.startPoint.x)*(line11.endPoint.x - line6.startPoint.x) + (line11.endPoint.y - line6.startPoint.y + R2) * (line11.endPoint.y - line6.startPoint.y + R2)).toDouble())
        val alpha1 = asin((R6 + K + J + H - R5 - F)/l)
        val alpha2 = acos(R2 / l)

        val arc5 = Arc(R2
                , Point(line6.startPoint.x, line6.startPoint.y - R2)
                , (180 + toDegrees(alpha1 + alpha2)).toInt()
                , 360)
        figures.add(arc5)

        arc5.definePoints()
        val line12 = Line(line11.endPoint, arc5.points.first)
        figures.add(line12)

        val circle = Circle(R1, Point(line6.startPoint.x, line6.startPoint.y - R2))
        figures.add(circle)
    }

    private fun drawAxis(g: Graphics) {
        val g2d = g as Graphics2D
        g2d.color = Color.GREEN
        g2d.font = Font("Courier New", Font.BOLD, 16)
        g2d.stroke = BasicStroke(11f)
        g2d.drawLine(3, 3, width / 2, 3)
        g2d.drawString("X", width / 2 + 5, 10)
        g2d.drawLine(3, 3, 3, height / 2)
        g2d.drawString("Y", 10, height / 2 + 5)
    }

    private fun drawDashedLine(g: Graphics) {
        val g2 = g as Graphics2D
        val dash = floatArrayOf(10.0f)
        g2.stroke = BasicStroke(2.5f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f)
        g2.drawLine(mainComponent.width / 2, mainComponent.height / 2, mainComponent.width / 2, mainComponent.height / 2 - 500)
        g2.drawLine(mainComponent.width / 2, mainComponent.height / 2, mainComponent.width / 2, mainComponent.height / 2 + 300)
        g2.drawLine(mainComponent.width / 2, mainComponent.height / 2, mainComponent.width / 2 + 300, mainComponent.height / 2)
        g2.drawLine(mainComponent.width / 2, mainComponent.height / 2, mainComponent.width / 2 - 300, mainComponent.height / 2)
    }

    private fun drawGrid(g: Graphics) {
        val g2d = g as Graphics2D
        g2d.color = Color.GRAY
        g2d.stroke = BasicStroke(0.1f)

        run {
            var i = 0
            while (i < height + 1) {
                g2d.drawLine(0, i, width, i)
                i += gridStep
            }
        }

        var i = 0
        while (i < width) {
            g2d.drawLine(i, 0, i, height)
            i += gridStep
        }
    }

    companion object {
        val gridStep = 10
    }
}



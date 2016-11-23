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

    var canvasPoints: List<Point>? = null
    var engine = Engine(this)

    var ed: EuclidData? = null
    var ad: AffineData? = null
    var pd: ProectiveData? = null

    var R1 = 80
    var R2 = 40
    var R3 = 40
    var A = 80
    var B = 20
    var C = 40
    var D = 60
    var E = 40
    var F = 60
    var G = 120
    var H = 40
    var I = 30
    var ALPHA = toDegrees(atan(3.0)).toInt()

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

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        background = Color.WHITE

        //defineCenterLines()
        //drawAxis(g)

        definePlotElements()

        when{
            ad != null -> {engine.affineTransformByAxis(ad!!.a, ad!!.b, ad!!.c, ad!!.d, ad!!.e, ad!!.f)
                            ad = null}
            ed != null -> {engine.euclidianTransform(ed!!.shiftX, ed!!.shiftY, ed!!.degree, ed!!.pointX, ed!!.pointY)
                            ed = null}
            pd != null -> {engine.proectiveTransform(pd!!.a, pd!!.b, pd!!.c, pd!!.d, pd!!.e, pd!!.f, pd!!.w1, pd!!.w2, pd!!.w3)
                            pd = null}
        }

        engine.draw(g)
    }

    private fun definePlotElements() {
        engine.reset()
        defineCenterLines()
        engine.defineGrid()
        val axis = Line(Point(mainComponent.width / 2, mainComponent.height / 2 - 300), Point(mainComponent.width / 2, mainComponent.height / 2 + 300))

        val drawing = formLeftPartOfDrawing()
        var mirror = LinkedList<Figure>()

        for(figure in drawing){
            mirror.add(figure.mirror(axis))
        }

        engine.add(drawing)
        engine.add(mirror)
    }

    private fun formLeftPartOfDrawing(): LinkedList<Figure>{

        var drawing = LinkedList<Figure>()

        //CENTER_POINT
        val centerPoint = Point(mainComponent.width / 2, mainComponent.height / 2)
        drawing.add(centerPoint)

        //CENTER_CIRCLE
        val centerCircle = Arc(R2, centerPoint, 180, 360)
        drawing.add(centerCircle)

        //BOTTOM_ARC
        val bottomArc = Arc(R1, centerPoint, -90, 0)
        drawing.add(bottomArc)

        //LINE_1
        val line1 = Line(Point(centerPoint.x - R1, centerPoint.y), Point(centerPoint.x - R1 - A, centerPoint.y))
        drawing.add(line1)

        //LINE_2
        val line2 = Line(line1.endPoint, Point(line1.endPoint.x, line1.endPoint.y + B - G - F - E))
        drawing.add(line2)

        //LINE_3
        val line3 = Line(line2.endPoint, Point(line2.endPoint.x + C, line2.endPoint.y - B))
        drawing.add(line3)

        //LINE_4
        val line4 = Line(line3.endPoint, Point(line3.endPoint.x + D, line3.endPoint.y))
        drawing.add(line4)

        //LINE_5
        val line5 = Line(line4.endPoint, Point(line4.endPoint.x, line4.endPoint.y + E))
        drawing.add(line5)

        //LINE_6
        val line6 = Line(line5.endPoint, Point(line5.endPoint.x - H, line5.endPoint.y))
        drawing.add(line6)

        //LINE_7
        val line7 = Line(line6.endPoint, Point(line6.endPoint.x, line6.endPoint.y + F))
        drawing.add(line7)

        val y = R3 * cos(toRadians(ALPHA.toDouble())) + I
        //println("R3 = $R3\ncos(ALPHA) = ${cos(ALPHA)}\nI = $I\ny = $y")
        val x = y / tan(toRadians(ALPHA.toDouble()))
        val z = R3 * sin(toRadians(ALPHA.toDouble()))

        //LINE_8
        val line8 = Line(line7.endPoint, Point((centerPoint.x - z - x).toInt(), line7.endPoint.y))
        drawing.add(line8)

        //LINE_9
        val line9 = Line(line8.endPoint, Point((line8.endPoint.x + x).toInt(), (line8.endPoint.y - y).toInt()))
        drawing.add(line9)

        //TOP_ARC
        val topArc = Arc(R3, Point(centerPoint.x, centerPoint.y - G - I), 180, 180 + ALPHA)
        drawing.add(topArc)

        return drawing
    }

    /*private fun drawAxis(g: Graphics) {
        val g2d = g as Graphics2D
        g2d.color = Color.GREEN
        g2d.font = Font("Courier New", Font.BOLD, 16)
        g2d.stroke = BasicStroke(11f)
        g2d.drawLine(3, 3, width / 2, 3)
        g2d.drawString("X", width / 2 + 5, 10)
        g2d.drawLine(3, 3, 3, height / 2)
        g2d.drawString("Y", 10, height / 2 + 5)
    }*/

    private fun defineCenterLines() {
        engine.addCenterLine(Line(Point(mainComponent.width / 2, mainComponent.height / 2), Point(mainComponent.width / 2, mainComponent.height / 2 - 500)))
        engine.addCenterLine(Line(Point(mainComponent.width / 2, mainComponent.height / 2), Point(mainComponent.width / 2, mainComponent.height / 2 + 300)))
        engine.addCenterLine(Line(Point(mainComponent.width / 2, mainComponent.height / 2), Point(mainComponent.width / 2 + 300, mainComponent.height / 2)))
        engine.addCenterLine(Line(Point(mainComponent.width / 2, mainComponent.height / 2), Point(mainComponent.width / 2 - 300, mainComponent.height / 2)))
    }

    data class EuclidData(val shiftX: Int, val shiftY: Int, val degree: Int, val pointX: Int, val pointY: Int)
    data class AffineData(val a: Int, val b: Int, val c: Int, val d: Int, val e: Int, val f: Int)
    data class ProectiveData(val a: Double, val b: Double, val c: Double, val d: Double, val e: Double, val f: Double, val w1: Double, val w2: Double, val w3: Double)

    companion object {
        val gridStep = 10
    }
}



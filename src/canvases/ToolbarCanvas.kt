package canvases

/**
 * Created by Тарас on 28.09.2016.
 */

import figures.Point
import figures.RotateType

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class ToolbarCanvas(width: Int, height: Int, private val mainComponent: Container, private val figureCanvas: FigureCanvas) : JPanel() {
    private var topHeight: TextField? = null
    private var botHeight: TextField? = null
    private var innerRadius: TextField? = null
    private var outerRadius: TextField? = null
    private var mainRadius: TextField? = null
    private var rebuildBtn: Button? = null
    private var centralArcDiff: TextField? = null
    private var moveNumber: TextField? = null
    private var rotateX: Button? = null
    private var degree: TextField? = null
    private var pointX: TextField? = null
    private var pointY: TextField? = null
    private var rotatePoint: Button? = null


    init {
        this.preferredSize = Dimension(width, height)
        initFields()
    }

    private fun initFields() {
        //Top length
        topHeight = TextField(5)
        val topHeightText = Label("H1")
        this.add(topHeightText)
        this.add(topHeight)

        //Bot length
        botHeight = TextField(5)
        val botHeightText = Label("H2")
        this.add(botHeightText)
        this.add(botHeight)

        //Inner radius
        innerRadius = TextField(5)
        val innerRadiusText = Label("R2")
        this.add(innerRadiusText)
        this.add(innerRadius)

        //Outer radius
        outerRadius = TextField(5)
        val outerRadiusText = Label("R1")
        this.add(outerRadiusText)
        this.add(outerRadius)

        //main radius
        mainRadius = TextField(5)
        val centralRadiusText = Label("R3")
        this.add(centralRadiusText)
        this.add(mainRadius)

        //central arcs diff
        centralArcDiff = TextField(5)
        val centralArcDiffText = Label("H3")
        this.add(centralArcDiffText)
        this.add(centralArcDiff)
        setValues()

        //Button
        rebuildBtn = Button("Update values")

        //Checkbox
        val Highlight = Label("Highlight")
        this.add(Highlight)
        val isHighlightedCheckbox = Checkbox()
        this.add(isHighlightedCheckbox)

        //moveNumber
        val Move = Label("Move")
        this.add(Move)
        moveNumber = TextField()
        moveNumber!!.text = "1"
        this.add(moveNumber)

        //
        val Degree = Label("Degree")
        this.add(Degree)
        degree = TextField("30")
        this.add(degree)

        rebuildBtn!!.addActionListener {
            //figureCanvas.setRotate(false);
            figureCanvas.canvasPoints?.slice(0..0)
            figureCanvas.topLength = Integer.valueOf(topHeight!!.text)!!
            figureCanvas.bottomLength = Integer.valueOf(botHeight!!.text)!!
            figureCanvas.innerCircleRadius = Integer.valueOf(innerRadius!!.text)!!
            figureCanvas.outerCircleRadius = Integer.valueOf(outerRadius!!.text)!!
            figureCanvas.centerRadius = Integer.valueOf(mainRadius!!.text)!!
            figureCanvas.secondArcLengthDif = Integer.valueOf(centralArcDiff!!.text)!!
            figureCanvas.highlighted = isHighlightedCheckbox.state
            mainComponent.repaint()
        }
        add(rebuildBtn)

        val moveLeft = Button("<")
        val moveRight = Button(">")
        val moveTop = Button("^")
        val moveBottom = Button("V")

        moveRight.addActionListener {
            figureCanvas.shiftX += moveNumber!!.text!!.toInt()
            mainComponent.repaint()
        }

        moveLeft.addActionListener {
            figureCanvas.shiftX = figureCanvas.shiftX - Integer.valueOf(moveNumber!!.text)!!
            mainComponent.repaint()
        }

        moveTop.addActionListener {
            figureCanvas.shiftY -= moveNumber!!.text!!.toInt()
            mainComponent.repaint()
        }

        moveBottom.addActionListener {
            figureCanvas.shiftY += moveNumber!!.text!!.toInt()
            mainComponent.repaint()
        }

        add(moveBottom)
        add(moveTop)
        add(moveLeft)
        add(moveRight)

        rotateX = Button("Rotate Axis")
        rotateX!!.addActionListener {
            figureCanvas.rotateType = RotateType.AXIS
            figureCanvas.rotateDegree = degree!!.text.toInt()
            figureCanvas.rotate = true
            mainComponent.repaint()
        }
        add(rotateX)

        rotatePoint = Button("Rotate point")

        pointX = TextField("100")

        pointY = TextField("100")

        rotatePoint!!.addActionListener {
            figureCanvas.rotateType = RotateType.POINT
            figureCanvas.rotateDegree = degree!!.text.toInt()
            figureCanvas.rotatePoint = Point(Integer.parseInt(pointX!!.text), Integer.parseInt(pointY!!.text))
            figureCanvas.rotate = true
            mainComponent.repaint()
        }

        add(rotatePoint)
        add(Label("x :"))
        add(pointX)
        add(Label("y :"))
        add(pointY)
    }


    private fun setValues() {
        topHeight!!.text += figureCanvas.topLength
        botHeight!!.text += figureCanvas.bottomLength
        innerRadius!!.text += figureCanvas.innerCircleRadius
        outerRadius!!.text += figureCanvas.outerCircleRadius
        mainRadius!!.text += figureCanvas.centerRadius
        centralArcDiff!!.text += figureCanvas.secondArcLengthDif
    }
}

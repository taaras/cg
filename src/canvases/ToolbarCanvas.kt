package canvases

/**
 * Created by Тарас on 28.09.2016.
 */

import javax.swing.*
import java.awt.*

class ToolbarCanvas(width: Int, height: Int, private val mainComponent: Container, private val figureCanvas: FigureCanvas) : JPanel() {

    private var r1 = JSpinner(SpinnerNumberModel(80, 70, 90, 1))
    private var r2 = JSpinner(SpinnerNumberModel(40, 30, 50, 1))
    private var r3 = JSpinner(SpinnerNumberModel(40, 30, 50, 1))
    private var a = JSpinner(SpinnerNumberModel(80, 70, 90, 1))
    private var b = JSpinner(SpinnerNumberModel(20, 10, 30, 1))
    private var c = JSpinner(SpinnerNumberModel(40, 30, 50, 1))
    private var d = JSpinner(SpinnerNumberModel(60, 50, 70, 1))
    private var e = JSpinner(SpinnerNumberModel(40, 30, 50, 1))
    private var f = JSpinner(SpinnerNumberModel(60, 50, 70, 1))
    private var g = JSpinner(SpinnerNumberModel(120, 110, 130, 1))
    private var h = JSpinner(SpinnerNumberModel(40, 30, 50, 1))
    private var i = JSpinner(SpinnerNumberModel(30, 20, 40, 1))
    private var alpha = JSpinner(SpinnerNumberModel(71.0, 61.0, 81.0, 1.0))

    init {
        this.preferredSize = Dimension(width, height)
        initFields()
    }

    private fun initFields() {

        //R1
        val l1 = JLabel("R1")
        l1.labelFor = r1
        r1.addChangeListener {
            figureCanvas.R1 = r1.value as Int
            mainComponent.repaint()
        }
        add(l1)
        add(r1)

        //R2
        val l2 = JLabel("R2")
        l2.labelFor = r2
        r2.addChangeListener {
            figureCanvas.R2 = r2.value as Int
            mainComponent.repaint()
        }
        add(l2)
        add(r2)

        //R3
        val l3 = JLabel("R3")
        l3.labelFor = r3
        r3.addChangeListener {
            figureCanvas.R3 = r3.value as Int
            mainComponent.repaint()
        }
        add(l3)
        add(r3)

        //
        val l4 = JLabel("A")
        l4.labelFor = a
        a.addChangeListener {
            figureCanvas.A = a.value as Int
            mainComponent.repaint()
        }
        add(l4)
        add(a)

        //
        val l5 = JLabel("B")
        l5.labelFor = b
        b.addChangeListener {
            figureCanvas.B = b.value as Int
            mainComponent.repaint()
        }
        add(l5)
        add(b)

        //
        val l6 = JLabel("C")
        l6.labelFor = c
        c.addChangeListener {
            figureCanvas.C = c.value as Int
            mainComponent.repaint()
        }
        add(l6)
        add(c)

        //
        val l7 = JLabel("D")
        l7.labelFor = d
        d.addChangeListener {
            figureCanvas.D = d.value as Int
            mainComponent.repaint()
        }
        add(l7)
        add(d)

        //
        val l8 = JLabel("E")
        l8.labelFor = e
        e.addChangeListener {
            figureCanvas.E = e.value as Int
            mainComponent.repaint()
        }
        add(l8)
        add(e)

        //
        val l9 = JLabel("F")
        l9.labelFor = f
        f.addChangeListener {
            figureCanvas.F = f.value as Int
            mainComponent.repaint()
        }
        add(l9)
        add(f)

        //
        val l10 = JLabel("G")
        l10.labelFor = g
        g.addChangeListener {
            figureCanvas.G = g.value as Int
            mainComponent.repaint()
        }
        add(l10)
        add(g)

        //
        val l11 = JLabel("H")
        l11.labelFor = h
        h.addChangeListener {
            figureCanvas.H = h.value as Int
            mainComponent.repaint()
        }
        add(l11)
        add(h)

        //
        val l12 = JLabel("I")
        l12.labelFor = i
        i.addChangeListener {
            figureCanvas.I = i.value as Int
            mainComponent.repaint()
        }
        add(l12)
        add(i)

        //
        val l13 = JLabel("  α")
        l13.labelFor = alpha
        alpha.addChangeListener {
            figureCanvas.ALPHA = (alpha.value as Double).toInt()
            mainComponent.repaint()
        }
        add(l13)
        add(alpha)





        /*//Top length
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
        add(rebuildBtn)*/
        /*rotateX = Button("Rotate Axis")
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
        add(pointY)*/
    }

    /*private fun setValues() {
        topHeight!!.text += figureCanvas.topLength
        botHeight!!.text += figureCanvas.bottomLength
        innerRadius!!.text += figureCanvas.innerCircleRadius
        outerRadius!!.text += figureCanvas.outerCircleRadius
        mainRadius!!.text += figureCanvas.centerRadius
        centralArcDiff!!.text += figureCanvas.secondArcLengthDif
    }*/
}

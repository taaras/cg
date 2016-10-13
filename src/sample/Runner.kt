package sample

/**
 * Created by Тарас on 28.09.2016.
 */

import canvases.FigureCanvas
import canvases.ToolbarCanvas

import javax.swing.*
import java.awt.*

class Runner : JFrame() {
    private val figureCanvas: FigureCanvas

    init {
        val cp = contentPane

        figureCanvas = FigureCanvas(CANVAS_WIDTH, CANVAS_HEIGHT + 1, cp)
        cp.add(figureCanvas, BorderLayout.NORTH)
        cp.add(ToolbarCanvas(CANVAS_WIDTH, TOOLBAR_HEIGHT, this, figureCanvas), BorderLayout.SOUTH)
        bounds = Rectangle(250, 50, CANVAS_WIDTH, CANVAS_HEIGHT)
        defaultCloseOperation = EXIT_ON_CLOSE
        pack()
        title = "Building detail"
        isVisible = true
    }

    companion object {
        val CANVAS_WIDTH = 490
        val CANVAS_HEIGHT = 400
        val TOOLBAR_HEIGHT = 100

        @JvmStatic fun main(args: Array<String>) {
            SwingUtilities.invokeLater { Runner() }
        }
    }
}

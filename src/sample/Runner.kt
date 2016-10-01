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
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        pack()
        title = "Building detail"
        isVisible = true
    }

    companion object {
        val CANVAS_WIDTH = 760
        val CANVAS_HEIGHT = 460
        val TOOLBAR_HEIGHT = 50

        @JvmStatic fun main(args: Array<String>) {
            SwingUtilities.invokeLater { Runner() }
        }
    }
}

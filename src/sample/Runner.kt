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
        val sp = JSplitPane(JSplitPane.VERTICAL_SPLIT, figureCanvas, ToolbarCanvas(CANVAS_WIDTH, TOOLBAR_HEIGHT, this, figureCanvas))
        sp.isOneTouchExpandable = true
        sp.dividerLocation = 800
        cp.add(sp)
        bounds = Rectangle(250, 50, CANVAS_WIDTH, CANVAS_HEIGHT)
        defaultCloseOperation = EXIT_ON_CLOSE
        pack()
        title = "Building detail"
        isVisible = true
    }

    companion object {
        val CANVAS_WIDTH = 540
        val CANVAS_HEIGHT = 500
        val TOOLBAR_HEIGHT = 80

        @JvmStatic fun main(args: Array<String>) {
            SwingUtilities.invokeLater { Runner() }
        }
    }
}

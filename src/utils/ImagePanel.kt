package utils

import java.awt.Graphics
import java.awt.Image
import java.awt.Toolkit
import java.io.IOException
import javax.swing.JPanel

/**
 * Created by Тарас on 06.10.2016.
 */
class ImagePanel : JPanel {

    private var image: Image? = null

    constructor(){
        try{
            image = Toolkit.getDefaultToolkit().getImage("src/img/dimension.png")
        }catch (ex: IOException){}
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g!!.drawImage(image, 0, 0, null)
    }

}
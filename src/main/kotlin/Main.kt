import javax.swing.JFrame
import java.awt.GraphicsEnvironment

var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice!!
var screenWidth = gd.displayMode.width
var screenHeight = gd.displayMode.height

fun main() {
    val frame = JFrame("Visual Sorter")
    frame.isUndecorated = true
    frame.isVisible = true
    frame.setSize(screenWidth, screenHeight)

    val canvas = SortingCanvas(100)
    canvas.setSize(screenWidth, screenHeight)

    frame.contentPane.add(canvas)

//    canvas.collectionOfBars.bubbleSort { bars ->
//        canvas.updateBarList(bars)
//        canvas.repaint()
//        //Thread.sleep(0)
//    }

        canvas.collectionOfBars.startMergeSort { bars ->
        Thread.sleep(10)
        canvas.updateBarList(bars)
        canvas.repaint()
    }


}

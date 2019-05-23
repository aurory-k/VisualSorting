import java.awt.GraphicsEnvironment
import javax.swing.JFrame

var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice!!
var screenWidth = gd.displayMode.width
var screenHeight = gd.displayMode.height

fun main() {
    val frame = JFrame("Visual Sorter")
    frame.isUndecorated = true
    frame.isVisible = true
    frame.setSize(screenWidth, screenHeight)

    val canvas = SortingCanvas(1000)
    canvas.setSize(screenWidth, screenHeight)

    frame.contentPane.add(canvas)

//    canvas.collectionOfBars.bubbleSort { bars ->
//        canvas.updateBarList(bars)
//        canvas.repaint()
//        //Thread.sleep(0)
//    }

//    canvas.collectionOfBars.startMergeSort { bars ->
//        Thread.sleep(5)
//        canvas.updateBarList(bars)
//        canvas.repaint()
//    }

    canvas.collectionOfBars.insertionSort { bars ->
        Thread.sleep(50)
        canvas.updateBarList(bars)
        canvas.repaint()
    }


}

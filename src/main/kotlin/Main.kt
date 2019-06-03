import sorter.bubbleSort
import sorter.insertionSort
import sorter.startMergeSort
import sorter.startQuickSort
import java.awt.Color
import java.awt.GraphicsEnvironment
import java.awt.GridLayout
import javax.swing.JFrame

var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice!!
var screenWidth = gd.displayMode.width
var screenHeight = gd.displayMode.height

fun main() {
    val frame = JFrame("Visual Sorter")
    frame.isUndecorated = true
    frame.isVisible = true
    frame.setSize(screenWidth, screenHeight)

    val canvas = SortingCanvas(4, 100, Color.BLUE)
    canvas.setSize(screenWidth / 2, screenHeight / 2)

    val canvas2 = SortingCanvas(4, 100, Color.GREEN)
    canvas2.setSize(screenWidth / 2, screenHeight / 2)

    val canvas3 = SortingCanvas(4, 100, Color.RED)
    canvas3.setSize(screenWidth / 2, screenHeight / 2)

    val canvas4 = SortingCanvas(4, 100, Color.MAGENTA)
    canvas4.setSize(screenWidth / 2, screenHeight / 2)

    frame.contentPane.add(canvas)
    frame.contentPane.add(canvas2)
    frame.contentPane.add(canvas3)
    frame.contentPane.add(canvas4)
    frame.layout = GridLayout(2, 2)

//    canvas.collectionOfBars.bubbleSort { bars ->
//        Thread.sleep(10)
//        canvas.updateBarList(bars)
//        canvas.repaint()
//    }

//    canvas.collectionOfBars.startMergeSort { bars ->
//        Thread.sleep(10)
//        canvas.updateBarList(bars)
//        canvas.repaint()
//    }

//    canvas.collectionOfBars.insertionSort { bars ->
//        Thread.sleep(10)
//        canvas.updateBarList(bars)
//        canvas.repaint()
//    }

    Thread {
        canvas.collectionOfBars.startQuickSort { bars ->
            Thread.sleep(10)
            canvas.updateBarList(bars)
            canvas.repaint()
        }
    }.start()
    Thread {
        canvas2.collectionOfBars.insertionSort { bars ->
            Thread.sleep(10)
            canvas2.updateBarList(bars)
            canvas2.repaint()
        }
    }.start()

    Thread {
        canvas3.collectionOfBars.startMergeSort { bars ->
            Thread.sleep(10)
            canvas3.updateBarList(bars)
            canvas3.repaint()
        }
    }.start()

    Thread {
        canvas4.collectionOfBars.bubbleSort { bars ->
            Thread.sleep(10)
            canvas4.updateBarList(bars)
            canvas4.repaint()
        }
    }.start()


}

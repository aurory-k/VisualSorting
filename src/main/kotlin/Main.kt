import sorter.bubbleSort
import sorter.insertionSort
import sorter.startMergeSort
import sorter.startQuickSort
import java.awt.Color
import java.awt.GraphicsEnvironment
import java.awt.GridLayout
import javax.swing.JFrame
import kotlin.random.Random

var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice!!
var screenWidth = gd.displayMode.width
var screenHeight = gd.displayMode.height

const val NUMBER_OF_SORTS: Int = 1 // Does not work with anything less
const val SLEEP_IN_MILLIS: Long = 5
const val SOUND_ON = true

fun main() {
    val frame = JFrame("Visual Sorter")
    frame.isUndecorated = true
    frame.isVisible = true
    frame.setSize(screenWidth, screenHeight)

    var listOfCanvases = listOf<SortingCanvas>()
    var listOfColors =
        listOf(Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.ORANGE, Color.LIGHT_GRAY)
    val masterCollectionOfBars = generateBars(1000)

    (0 until NUMBER_OF_SORTS).forEach { pass ->
        val copiedCollectionOfBars = mutableListOf<Bar>()
        masterCollectionOfBars.mapTo(copiedCollectionOfBars) {
            it.copy()
        }

        val color = listOfColors.random()
        listOfColors = listOfColors.minus(color)

        val canvas = SortingCanvas(
            copiedCollectionOfBars,
            color
        )
        when (pass) {
            0 -> canvas.sort = {
                canvas.collectionOfBars.startMergeSort { bars ->
                    Thread.sleep(SLEEP_IN_MILLIS)
                    canvas.updateBarList(bars)
                    canvas.repaint()
                }
            }
            1 -> canvas.sort = {
                canvas.collectionOfBars.startMergeSort { bars ->
                    Thread.sleep(SLEEP_IN_MILLIS)
                    canvas.updateBarList(bars)
                    canvas.repaint()
                }
            }
            2 -> canvas.sort = {
                canvas.collectionOfBars.insertionSort { bars ->
                    Thread.sleep(SLEEP_IN_MILLIS)
                    canvas.updateBarList(bars)
                    canvas.repaint()
                }
            }
            else -> canvas.sort = {
                canvas.collectionOfBars.bubbleSort { bars ->
                    Thread.sleep(SLEEP_IN_MILLIS)
                    canvas.updateBarList(bars)
                    canvas.repaint()
                }
            }
        }

        canvas.setSize(screenWidth / (NUMBER_OF_SORTS / 2).orOne(), screenHeight / (NUMBER_OF_SORTS / 2).orOne())

        listOfCanvases = listOfCanvases.plus(canvas)
        frame.contentPane.add(canvas)
    }

    frame.layout = GridLayout((NUMBER_OF_SORTS / 2).orOne(), (NUMBER_OF_SORTS / 2).orOne())

    listOfCanvases.forEach {
        Thread {
            it.sort(it.collectionOfBars)
        }.start()
    }
}

private fun generateBars(numberOfBars: Int): List<Bar> {
    val collectionOfBars = mutableListOf<Bar>()

    val canvasWidth = screenWidth.toDouble() / (NUMBER_OF_SORTS / 2).orOne()
    val canvasHeight = screenHeight.toDouble() / (NUMBER_OF_SORTS / 2).orOne()

    val barWidth = canvasWidth / numberOfBars.toDouble()
    (0..numberOfBars).forEach { _ ->
        val bar = Bar(barWidth, Random.nextDouble(100.0, (canvasHeight - 50.0)), Color.BLUE)
        collectionOfBars.add(bar)
    }

    return collectionOfBars
}

fun Int.orOne(): Int{
    return if (this < 1){
        1
    } else {
        this
    }
}


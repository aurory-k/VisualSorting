import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JPanel
import kotlin.random.Random.Default.nextDouble

class SortingCanvas(private val numberOfSorts: Int = 1, private val numberOfBars: Int = 500, private val color: Color = Color.BLUE) : JPanel() {

    var collectionOfBars = listOf<Bar>()

    private val canvasWidth = screenWidth.toDouble() / 2
    private val canvasHeight = screenHeight.toDouble() / 2

    init {
        val barWidth = canvasWidth / numberOfBars.toDouble()
        (0..numberOfBars).forEach { _ ->
            val bar = Bar(barWidth, nextDouble(100.0, (canvasHeight - 50.0)), color)
            collectionOfBars = collectionOfBars.plus(bar)
        }
    }

    fun updateBarList(newListOfBars: List<Bar>) {
        collectionOfBars = newListOfBars
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val graphics2d = g as Graphics2D

        collectionOfBars.forEachIndexed { index, bar ->
            val rect = Rectangle2D.Double(index * bar.width, canvasHeight - bar.height, bar.width, bar.height)
            graphics2d.color = bar.color
            graphics2d.fill(rect)
            graphics2d.color = Color.BLACK
            graphics2d.draw(rect)
        }
    }
}
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JPanel
import kotlin.random.Random.Default.nextDouble

class SortingCanvas(var collectionOfBars: List<Bar>, private val color: Color) : JPanel() {

    lateinit var sort: (List<Bar>) -> List<Bar>

    init {
        collectionOfBars.changeColor(color)
    }

    fun updateBarList(newListOfBars: List<Bar>) {
        collectionOfBars = newListOfBars
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val graphics2d = g as Graphics2D

        collectionOfBars.forEachIndexed { index, bar ->
            val rect = Rectangle2D.Double(index * bar.width, (screenHeight / (NUMBER_OF_SORTS / 2).orOne()) - bar.height, bar.width, bar.height)
            graphics2d.color = bar.color
            graphics2d.fill(rect)
            graphics2d.color = Color.BLACK
            graphics2d.draw(rect)
        }
    }
}

fun List<Bar>.changeColor(color: Color) {
    this.forEach {
        it.color = color
    }
}
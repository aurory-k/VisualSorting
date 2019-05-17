import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JPanel

class SortingCanvas(private val numberOfBars: Int) : JPanel() {

    var collectionOfBars = listOf<Bar>()

    init {
        val barWidth = screenWidth.toDouble() / numberOfBars.toDouble()
        (0..numberOfBars).forEach{ _ ->
            val bar = Bar(barWidth, (100..screenHeight-50).random(), Color.BLUE)
            collectionOfBars = collectionOfBars.plus(bar)
        }
    }

    fun updateBarList(newListOfBars : List<Bar>){
        collectionOfBars = newListOfBars
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val graphics2d = g as Graphics2D

        collectionOfBars.forEachIndexed { index, bar ->
            val rect = Rectangle2D.Double(index * bar.width, (screenHeight - bar.height).toDouble(), bar.width, bar.height.toDouble())
            graphics2d.color = bar.color
            graphics2d.fill(rect)
            graphics2d.color = Color.BLACK
            graphics2d.draw(rect)
        }
    }
}
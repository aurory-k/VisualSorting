import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.sound.midi.MidiSystem
import javax.swing.JPanel


val midiSynth = MidiSystem.getSynthesizer()

//get and load default instrument and channel lists
val instr = midiSynth.defaultSoundbank.instruments
val mChannels = midiSynth.channels

val instrument = midiSynth.loadInstrument(instr[0])//load an instrument

class SortingCanvas(var collectionOfBars: List<Bar>, private val color: Color) : JPanel() {

    lateinit var sort: (List<Bar>) -> List<Bar>

    init {
        midiSynth.open()
        collectionOfBars.changeColor(color)
    }

    fun updateBarList(newListOfBars: List<Bar>) {
        collectionOfBars = newListOfBars
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val graphics2d = g as Graphics2D

        collectionOfBars.forEachIndexed { index, bar ->
            val rect = Rectangle2D.Double(
                index * bar.width,
                (screenHeight / (NUMBER_OF_SORTS / 2).orOne()) - bar.height,
                bar.width,
                bar.height
            )
            graphics2d.color = bar.color
            graphics2d.fill(rect)
            graphics2d.color = Color.BLACK
            graphics2d.draw(rect)
        }
    }
}

fun playSound(height: Int) {
    if (SOUND_ON) {
        Thread {
            mChannels[0].noteOn((height / 80) + 40, 127)
            Thread.sleep(SLEEP_IN_MILLIS * 2)
            mChannels[0].noteOff((height / 80) + 40)
        }.start()
    }
}


fun List<Bar>.changeColor(color: Color) {
    this.forEach {
        it.color = color
    }
}
package sorter

import Bar
import mChannels
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.roundToInt

fun List<Bar>.bubbleSort(draw: (List<Bar>) -> Unit): List<Bar> {
    val sortedList = CopyOnWriteArrayList<Bar>()
    this.toCollection(sortedList)

    for (pass in 0 until sortedList.size - 1) {
        for (currentBar in 0 until sortedList.size - pass - 1) {
            sortedList.compareNextAndSwap(currentBar, draw)
        }
    }

    return sortedList
}

private fun MutableList<Bar>.compareNextAndSwap(
    currentBar: Int,
    draw: (List<Bar>) -> Unit
) {
    if (this[currentBar].height > this[currentBar + 1].height) {

        val temp = this[currentBar]

        this[currentBar] = this[currentBar + 1]
        this[currentBar + 1] = temp

        mChannels[0].noteOn(this[currentBar].height.roundToInt()%127, 100)
        draw(this)
        mChannels[0].noteOff(this[currentBar].height.roundToInt()%127)
    }
}
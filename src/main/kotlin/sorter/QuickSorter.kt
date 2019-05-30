package sorter

import Bar
import java.util.concurrent.CopyOnWriteArrayList

fun List<Bar>.startQuickSort(draw: (List<Bar>) -> Unit): List<Bar>{
    val mutableList = CopyOnWriteArrayList<Bar>()
    this.toCollection(mutableList)

    val sortedList = mutableList.quickSort(0, mutableList.size-1, draw).toList()
    draw(sortedList)

    return sortedList
}

private fun MutableList<Bar>.quickSort(leftIndex: Int, rightIndex: Int, draw: (List<Bar>) -> Unit): MutableList<Bar>{
    if(leftIndex > rightIndex){
        return this
    }

    val partition = this.partitionList(leftIndex, rightIndex, this[rightIndex], draw)
    this.quickSort(leftIndex, partition - 1, draw)
    this.quickSort(partition + 1, rightIndex, draw)

    return this
}

private fun MutableList<Bar>.partitionList(startingLeftIndex: Int, startingRightIndex: Int, pivot: Bar, draw: (List<Bar>) -> Unit): Int{
    var leftIndex = startingLeftIndex
    var rightIndex = startingRightIndex

    while (leftIndex < rightIndex){
        while(this[leftIndex].height < pivot.height){
            leftIndex++
        }
        while(rightIndex > 0 && this[rightIndex].height > pivot.height){
            rightIndex--
        }
        this.swap(leftIndex, rightIndex, draw)
        draw(this)
    }

    return this.indexOf(pivot)
}

private fun MutableList<Bar>.swap(leftIndex: Int, rightIndex: Int, draw: (List<Bar>) -> Unit){
    val temp = this[leftIndex]

    this[leftIndex] = this[rightIndex]
    this[rightIndex] = temp
    draw(this)
}
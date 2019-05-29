package sorter

import Bar
import java.util.concurrent.CopyOnWriteArrayList

lateinit var masterList: CopyOnWriteArrayList<Bar>

fun List<Bar>.startMergeSort(draw: (List<Bar>) -> Unit): List<Bar> {
    masterList = CopyOnWriteArrayList()
    this.toCollection(masterList)

    val sortedList = masterList.mergeSort(draw).toList()
    draw(sortedList)

    return sortedList
}

fun MutableList<Bar>.mergeSort(draw: (List<Bar>) -> Unit): MutableList<Bar> {
    if (this.size < 2) {
        return this
    }

    val (leftList, rightList) = this.splitList()

    return merge(leftList.mergeSort(draw), rightList.mergeSort(draw), draw)
}

fun merge(leftList: MutableList<Bar>, rightList: MutableList<Bar>, draw: (List<Bar>) -> Unit): MutableList<Bar> {
    val mutableList = listOf<Bar>().toMutableList()

    var leftIndex = 0
    var rightIndex = 0
    var newIndex = 0
    var insertIndex = -1

    while (leftIndex < leftList.size && rightIndex < rightList.size) {
        if (leftList[leftIndex].height < rightList[rightIndex].height) {
            if (insertIndex == -1)
                insertIndex = masterList.indexOf(leftList[0]).orMaxSize()

            insertIntoMasterList(mutableList, newIndex, leftList[leftIndex], insertIndex, draw)
            insertIndex++
            leftIndex++
        } else {
            if (insertIndex == -1)
                insertIndex = masterList.indexOf(leftList[0]).orMaxSize()

            insertIntoMasterList(mutableList, newIndex, rightList[rightIndex], insertIndex, draw)
            insertIndex++
            rightIndex++
        }
        newIndex++

    }

    while (leftIndex < leftList.size) {
        insertIntoMasterList(mutableList, newIndex, leftList[leftIndex], insertIndex, draw)
        insertIndex++
        leftIndex++
        newIndex++
    }

    while (rightIndex < rightList.size) {
        insertIntoMasterList(mutableList, newIndex, rightList[rightIndex], insertIndex, draw)
        insertIndex++
        rightIndex++
        newIndex++
    }

    return mutableList
}

private fun insertIntoMasterList(
    mutableList: MutableList<Bar>,
    newIndex: Int,
    bar: Bar,
    insertIndex: Int,
    draw: (List<Bar>) -> Unit
) {
    mutableList.add(newIndex, bar)
    masterList.remove(bar)
    masterList.add(insertIndex.orMaxSize(), bar)
    draw(masterList)
}

fun List<Bar>.splitList(): Pair<MutableList<Bar>, MutableList<Bar>> {
    val mutableList = this.toMutableList()

    val middle = (this.size - 1) / 2
    return Pair(mutableList.subList(0, middle + 1), mutableList.subList(middle + 1, mutableList.size))
}

fun Int.orMaxSize(): Int {
    return if (this > masterList.size) {
        masterList.size
    } else {
        this
    }

}



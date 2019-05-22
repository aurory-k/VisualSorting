import java.util.concurrent.CopyOnWriteArrayList

lateinit var masterList: CopyOnWriteArrayList<Bar>

fun List<Bar>.startMergeSort(draw: (List<Bar>) -> Unit): List<Bar> {
    masterList = CopyOnWriteArrayList()
    this.toCollection(masterList)
    val sortedList= masterList.mergeSort(draw).toList()
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

            mutableList.add(newIndex, leftList[leftIndex])
            masterList.remove(leftList[leftIndex])
            masterList.add(insertIndex.orMaxSize(), leftList[leftIndex])
            draw(masterList)
            insertIndex++
            leftIndex++
        } else {
            if (insertIndex == -1)
                insertIndex = masterList.indexOf(leftList[0]).orMaxSize()

            mutableList.add(newIndex, rightList[rightIndex])
            masterList.remove(rightList[rightIndex])
            masterList.add(insertIndex.orMaxSize(), rightList[rightIndex])
            draw(masterList)
            insertIndex++
            rightIndex++
        }
        newIndex++

    }

    while (leftIndex < leftList.size) {
        mutableList.add(newIndex, leftList[leftIndex])
        masterList.remove(leftList[leftIndex])
        masterList.add(insertIndex.orMaxSize(), leftList[leftIndex])
        draw(masterList)
        insertIndex++
        leftIndex++
        newIndex++
    }

    while (rightIndex < rightList.size) {
        mutableList.add(newIndex, rightList[rightIndex])
        masterList.remove(rightList[rightIndex])
        masterList.add(insertIndex.orMaxSize(), rightList[rightIndex])
        draw(masterList)
        insertIndex++
        rightIndex++
        newIndex++
    }
    //updateMasterList(mutableList, draw)
    return mutableList
}

private fun updateMasterList(
    mutableList: MutableList<Bar>,
    draw: (List<Bar>) -> Unit
) {
    var indexToInsertAt = masterList.indexOf(mutableList[0])

    mutableList.forEach {
        val indexInMaster = masterList.indexOf(it)
        masterList.removeAt(indexInMaster)
    }

    if (indexToInsertAt > masterList.size) {
        indexToInsertAt = masterList.size
    }
    masterList.addAll(indexToInsertAt, mutableList)
    draw(masterList)
}

fun CopyOnWriteArrayList<Bar>.check(bar: Bar): Int{
    var count = 0

    this.forEach{
        if(it == bar){
          count++
        }
    }

    return count
}

fun List<Bar>.splitList(): Pair<MutableList<Bar>, MutableList<Bar>> {
    val mutableList = this.toMutableList()

    val middle = (this.size - 1) / 2
    return Pair(mutableList.subList(0, middle + 1), mutableList.subList(middle + 1, mutableList.size))
}

fun Int.orMaxSize(): Int {
    return if(this > masterList.size){
        masterList.size
    } else {
        this
    }

}



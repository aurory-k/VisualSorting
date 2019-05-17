
fun List<Bar>.mergeSort(draw: (List<Bar>) -> Unit): List<Bar> {
    var startingList = this.toMutableList()
    var drawingList = this.toMutableList()
    draw(startingList)

    if (startingList.size < 2) {
        return startingList
    }

    val (leftList, rightList) = startingList.splitList()
    drawingList.addAll(leftList)
    drawingList.addAll(rightList)
    draw(drawingList)

    val mergedList = merge(leftList.mergeSort(draw).toMutableList(), rightList.mergeSort(draw).toMutableList(), draw)
    draw(mergedList)

    return mergedList
}

fun merge(leftList: MutableList<Bar>, rightList: MutableList<Bar>, draw: (List<Bar>) -> Unit): List<Bar> {
    val mutableList = listOf<Bar>().toMutableList()

    var leftIndex = 0
    var rightIndex = 0
    var newIndex = 0

    while (leftIndex < leftList.size && rightIndex < rightList.size) {
        if (leftList[leftIndex].height < rightList[rightIndex].height) {
            mutableList.add(newIndex++, leftList[leftIndex++])
        } else {
            mutableList.add(newIndex++, rightList[rightIndex++])
        }
        draw(mutableList)
    }

    while (leftIndex < leftList.size) {
        mutableList.add(newIndex++, leftList[leftIndex++])
        draw(mutableList)

    }

    while (rightIndex < rightList.size) {
        mutableList.add(newIndex++, rightList[rightIndex++])
        draw(mutableList)

    }

    return mutableList
}

fun List<Bar>.splitList(): Pair<MutableList<Bar>, MutableList<Bar>> {
    val mutableList = this.toMutableList()

    val middle = (this.size - 1) / 2
    return Pair(mutableList.subList(0, middle + 1), mutableList.subList(middle + 1, mutableList.size))
}



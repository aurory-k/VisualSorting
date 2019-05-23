import java.util.concurrent.CopyOnWriteArrayList

fun List<Bar>.insertionSort(draw: (List<Bar>) -> Unit) : List<Bar>{
    val mutableList = CopyOnWriteArrayList<Bar>()
    this.toCollection(mutableList)
    val sortedSublist = mutableListOf<Bar>()

    for(unsortedPass in 0 until mutableList.size){

        val indexToInsertAt = sortedSublist.findInsertIndex(mutableList[unsortedPass])

        val copiedBar = mutableList[unsortedPass].copy()
        mutableList.remove(copiedBar)
        mutableList.add(indexToInsertAt, copiedBar)
        sortedSublist.add(indexToInsertAt, copiedBar)
        draw(mutableList)
    }


    return mutableList.toList()
}

private fun MutableList<Bar>.findInsertIndex(barToInsert : Bar): Int {
    if(this.size == 0){
        return 0
    } else {
        for(pass in 0 until this.size){
            val currentBar = this[pass]
            val previousBar = this[(pass - 1).orZero()]

            if(barToInsert.height < currentBar.height){
                return pass
            } else if(barToInsert.height < currentBar.height && barToInsert.height >= previousBar.height){
                return pass
            }
        }
    }

    return this.size
}

private fun Int.orZero(): Int {
    return if(this < 0){
        0
    } else {
        this
    }
}

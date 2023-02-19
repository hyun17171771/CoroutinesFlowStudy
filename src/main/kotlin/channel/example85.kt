package channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers1() = produce<Int> {
    var x = 1
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.filterOdd(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        if ( i % 2 == 1) {
            send("${i}!")
        }
    }
}

fun CoroutineScope.filterEven(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        if (i % 2 == 0) {
            send("${i}!")
        }
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers1()
    val oddNumbers = filterOdd(numbers)
    val evenNumbers = filterEven(numbers)
    repeat(10) {
        //println(oddNumbers.receive())
        println(evenNumbers.receive())
    }
    coroutineContext.cancelChildren()
    println("완료")
}
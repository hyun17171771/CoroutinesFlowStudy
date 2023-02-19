package channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) : ReceiveChannel<Int> = produce {
    for(i in numbers) {
        if (i % prime != 0) {
            send(i)
        }
    }
}

fun main() = runBlocking {
    var numbers = numbersFrom(2) // RC, 3,4,5

    repeat(10) {
        val prime = numbers.receive() // 2
        println(prime)
        numbers = filter(numbers, prime) //위 recevie에서 2를 받았으므로 number는 3부터 있다
    }
    println("완료")
    coroutineContext.cancelChildren()
}
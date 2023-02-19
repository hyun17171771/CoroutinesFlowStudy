package channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

//파이프 라인 - 하나의 스트림을 프로듀서가 만들고 다른 코루틴에서 그 스트림을 읽어 새로운 스트림을 만드는 패턴

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.produceStringNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        send("${i}!")
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers()
    val stringNumber = produceStringNumbers(numbers)

    //위 확장 함수에 close가 없기 때문에 for문을 사용할 수 없다
    repeat(5) {
        println(stringNumber.receive()) //명시적 receive
    }
    println("완료")
}
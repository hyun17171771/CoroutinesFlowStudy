package channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//채널에서 더 이상 보낼 자료가 없으면 close 메서드를 이용해 채널을 닫을 수 있따

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
       for (x in 1..10) {
           channel.send(x)
       }
        //channel.close() //close가 없으면 아래 for문이 계속 돌기 때문에 무한루프 발생
    }
    repeat(10) {//close를 명시하지 않을 경우 반복횟수를 명시적으로 사용해야 한다
        println(channel.receive())
    }
/*    for (x in channel) { //receive 대신 for문을 통해 반복적으로 receive할 수 있다. close되면 for in은 자동 종료 된다.
        println(x)
    }*/
    println("완료")
}
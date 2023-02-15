import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

/**
 * 액터는 1973년 칼 휴이트가 만든 개념으로 액터가 독점적으로 자료를 가지며 그 자료를 다른 코루틴과 공유하지 않고 액터(자료 관리)를 통해서만 접근하게 만듬
 *
 */

sealed class CounterMsg //실드클래스로 외부에서 확장이 불 가능함
object IncCounter : CounterMsg() //싱글톤으로 인스턴슬르 만들 수 없음. 액터에게 값을 증가시키기 위한 신호로 쓰임
class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg()

fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0 // 액터 안에 상태를 캡슐화해두고 다른 코루틴이 접근하지 못하게 한다.

    for (msg in channel) { //외부에서 보내는 것은 채널을 통해서만 받을 수 있다 suspension point 값이 들어올 때 깨어남
       when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

fun main() = runBlocking<Unit> {
    val counter = counterActor()
    withContext(Dispatchers.Default) {
        massiveRun {
            counter.send(IncCounter) //suspension point
        }
    }
    val response = CompletableDeferred<Int>()
    counter.send(GetCounter(response)) //suspension point
    println("Counter = ${response.await()}") // suspension point 값을 받을 때 깨어나서 실행
    counter.close()
}
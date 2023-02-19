package channel

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * 생산자와 소비자는 광장히 일반적인 패턴이다. 채널을 이용해서 한 쪽에서 데이터를 만들고 다른 쪽에서 받는 것을 도와주는 확장 함수들이 있다
 * 1. produce 코루틴을 만ㄷ르고 채널을 제공한다
 * 2. consumeEach 채널에서 반복해서 데이터를 받아간다
 *
 * ProducerScope는 CoroutineScope 인터페이스와 SendChannel 인터페이스를 함께 상속받는다.
 * 그래서 코루틴 컨텍스트와 몇가지 채널 인터페이스를 같이 사용할 수 있는 특이한 스코프이다.
 *
 * produce를 사용하면 ProducerScope를 상속받는 ProducerCoroutine 코루틴을 얻게 된다.
 *
 * 참고사항
 * runBlocking은 BlockingCoroutine을 쓰는데 이는 AbstractCoroutine를 상속받고 있다.
 * 결국 코루틴 빌더는 코루틴을 만드는데 이들이 코루틴 스코프이기도 한 것이다.
 * AbstractCoroutine은 JobSupport, Job(인터페이스), Continuation(인터페이스), CoroutineScope(인터페이스)를 상속받고 있다.
 *
 * Continuation은 다음에 무엇을 할지, Job은 제어를 위한 정보와 제어, CoroutineScope는 컨텍스트(코루틴 실행을 위한 정보들) 제공의 역할을 한다.
 */

fun main() = runBlocking {
    val oneToTen = produce { // ProducerScope = CoroutineScope + SendChannel
        for (x in 1..10) {
            channel.send(x)
        }
    }

    oneToTen.consumeEach {
        println(it)
    }
    println("완료")
}
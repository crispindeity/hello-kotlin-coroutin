package day01

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("${i++}번째 출력!")
                nextPrintTime += 1_000L
            }

            if (!isActive) {
                throw CancellationException()
            }
        }
    }

    delay(100L)
    job.cancel()
}


fun lec04Example2(): Unit = runBlocking {
    val job = launch {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("${i++}번째 출력!")
                nextPrintTime += 1_000L
            }
        }
    }

    delay(100L)
    job.cancel()
}

fun lec04Example1(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread("Job 1")
    }
    
    val job2 = launch { 
        delay(1_000L)
        printWithThread("Job 2")
    }
    
    delay(100)
    job1.cancel()
}

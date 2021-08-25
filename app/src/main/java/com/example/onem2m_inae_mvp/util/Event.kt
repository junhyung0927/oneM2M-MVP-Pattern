package com.example.onem2m_inae_mvp.util

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) { //이벤트가 이미 처리 되었을 경우 null 반환
            null
        } else {
            hasBeenHandled = true //이벤트가 처리되었다고 표시한 후에 값을 반환
            content
        }
    }

    //이벤트의 처리 여부에 상관 없이 값을 반환
    fun peekCount(): T = content
}
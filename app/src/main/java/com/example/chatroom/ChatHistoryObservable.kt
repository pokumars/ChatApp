package com.example.chatroom

interface ChatHistoryObservable {
    fun register (observer: ChatHistoryObserver)
    fun deregister(observer: ChatHistoryObserver)
    fun notifyObserver()
}
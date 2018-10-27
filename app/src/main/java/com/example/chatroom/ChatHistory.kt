package com.example.chatroom

object ChatHistory:ChatHistoryObservable{
    var listOfObserver= mutableSetOf<ChatHistoryObserver>()
    var theMessageList= ArrayList<ChatMessage>()

    // not in use
    // var theMessageStrings = ArrayList<String>()

    override fun register(_observer: ChatHistoryObserver) {
        listOfObserver.add(_observer)
    }

    override fun deregister(_observer: ChatHistoryObserver) {
        listOfObserver.remove(_observer)
    }

    override fun notifyObserver() {
        for (i in listOfObserver){
            i.newMessage()
        }
    }
}
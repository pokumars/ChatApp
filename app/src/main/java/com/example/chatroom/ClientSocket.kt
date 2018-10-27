package com.example.chatroom
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.example.chatroom.Connector.ipAddress
import java.io.InputStream
import java.io.PrintStream
import java.lang.Exception
import java.net.Socket
import java.time.LocalDateTime
import java.util.*
object ClientSocket {
    //RECEIVES
    //ip address of my emulator
    val ipAddress = "10.0.2.2"
/*
    portnumber of this i use my phone
    val ipAddress = "192.168.43.178"
*/
    val portNumber = 30001
    var msg:String=""
    var userName: String=""
    var timeString:String=""


    @RequiresApi(Build.VERSION_CODES.O)
    fun connect() {

        try {
            val socketThread = Thread {

                val socket = Socket(ipAddress, portNumber)

                //gets what the server is sending your way
                val input = socket.getInputStream()

                receiver(input)
            }

            socketThread.start()
        } catch (e: Exception) {
            println("Exception${e.message}")
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun receiver(_input:InputStream):String{
        val scanner = Scanner(_input)
        lateinit var userInput:String
        while (true) {
            userInput= scanner.nextLine()

            //input comes in like this("2018-10-02T10:07:28.380% kojo# said hello man"), so we take
            // the part after the time and store it temporarily in var tempUserAndMsg before that as time
            var tempUserAndMsg=userInput.substringAfter("%")
            var temptime=userInput.substringBefore('%')

            //now We have this(" kojo# said hello man"). Take what comes before the '#' as username
            // and what comes after "said" as message and store it as smth of type ChatMessage
            timeString=temptime.substringAfter('T').trim().substringBeforeLast(':')

            userName=tempUserAndMsg.substringAfter("%").substringBefore('#')

            msg=tempUserAndMsg.substringAfter("said")
            //the order of the parameters of the var below is important or,
            // you need to specify which is which parameter
            var myChatMessage=ChatMessage(msg=msg,user = userName ,timeNow = timeString)

            //Add to List
            insert(myChatMessage)
            //ChatHistory.theMessageList.add(myChatMessage)
            //not in use
            // ChatHistory.theMessageStrings.add(userInput)


            Log.d("Tag",userInput)
        }
    }
    fun insert(_msg:ChatMessage){
        ChatHistory.theMessageList.add(_msg)
        ChatHistory.notifyObserver()
    }
}






/* 26 sept able to get message in log.d and also send message
class ClientSocket {
    //ip address of my emulator
    val ipAddress = "10.0.2.2"
    val portNumber = 30001
    fun connect() {

        try {
            val socketThread = Thread {

                val socket = Socket(ipAddress, portNumber)

                //gets what the server is sending your way
                val input = socket.getInputStream()
                //sends to server
                val output = PrintStream(socket.getOutputStream(), true)
                ChatIOHandler(input, output)

                var aUserTest = ":user ohe"
                output.println(aUserTest)
                output.println("cmoooon")

                val scanner = Scanner(input)
                while (true) {
                    var userInput = scanner.nextLine()



                    Log.d("Tag", userInput)
                }

            }

            socketThread.start()
        } catch (e: Exception) {
            println("Exception${e.message}")
        }
    }
}

*/



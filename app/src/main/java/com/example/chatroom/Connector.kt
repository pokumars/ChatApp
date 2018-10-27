package com.example.chatroom

import android.util.Log
import com.example.chatroom.ClientSocket.ipAddress
import com.example.chatroom.Connector.output
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

object Connector {
    //SENDS
    private const val TAG = "CONNECTOR"


    //ip address of my emulator
    val ipAddress = "10.0.2.2"
/*
    portnumber if i use my phone
    val ipAddress = "192.168.43.178"
*/    val portNumber = 30001
    lateinit var socket: Socket
    lateinit var input: Scanner
    lateinit var output: PrintStream

    fun connection() {
        try {
            linkUp()

        } catch (e: Exception) {
            Log.d(TAG, "connection failed")
        }
    }

    fun linkUp() {
        socket = Socket(ipAddress, portNumber)
        input = Scanner(socket.getInputStream())
        output = PrintStream(socket.getOutputStream())

        Log.d(TAG, "connected")
    }

    fun sendMessage(_msg: String) {
        thread {
            try {
                output.println(_msg)

            } catch (e: Exception) {
                Log.d(TAG, "failed send message")
            }
        }
    }
}

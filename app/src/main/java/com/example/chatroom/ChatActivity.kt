package com.example.chatroom

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity(),ChatHistoryObserver {
    //set adapter
    lateinit var adapter:MessageAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        //add to list of observers
        ChatHistory.register(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        ClientSocket.connect()

        adapter = MessageAdapter(this, ChatHistory.theMessageList)
        msg_list_view.adapter = adapter
        //adapter.notifyDataSetChanged()
        sendBtn.setOnClickListener {  send( myMessageET.text.toString())
        //adapter.notifyDataSetChanged()
        }
    }

    //logout  and option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = getMenuInflater()
        inflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.logout -> {
                logout()
                Toast.makeText(this, "logout selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.Lorem -> {
                Toast.makeText(this, "lorem selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.Ipsum -> {
                Toast.makeText(this, "ipsum selected", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //passes what was written to the connector object which then sends to the server
    fun send(_theMsg:String){
        if(!_theMsg.isEmpty()&&!_theMsg.isBlank()){
                    //uses singleton connector to send message
                    Connector.sendMessage(_theMsg)
                    myMessageET.setText("")
        }
    }
    override fun newMessage() {
        runOnUiThread {adapter.notifyDataSetChanged()}
    }
    fun logout(){
        //deregister from observer list, quit from server go to main activity and clear name from main activity textview
        send(":quit")
        ChatHistory.deregister(this)
        val logoutIntent: Intent= Intent(this,MainActivity::class.java)
        ChatHistory.theMessageList.clear()
        //clear the login textview
        intent.putExtra("clearName","")


        startActivity(logoutIntent)
    }


}

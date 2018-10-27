package com.example.chatroom

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    //Todo a mechanism for when the username is already in use. Fix the same ting in the server as well


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread { Connector.connection() }
        //thread { Connector.linkUp() }
        registerEnterBtnAndFxn()

    }

    fun registerEnterBtnAndFxn(){
        //if edittext is empty, display error message else take that as username
        landingEnterBtn.setOnClickListener {
            var user= loginUserNameET.text.toString()

            if(user.isBlank()||user.isEmpty()){
                errorPrompt()//Enter username to continue

            }else{
                acceptUsername()
            }
        }

        /*Todo: if you return to starting page it shoud deregister the old username or not allow you to use a different username
       Todo: else it ends up regustering the old name and any new name you add*/
    }

    fun errorPrompt(){
        //no username given so make error prompt visible
        ErrorTV.visibility= View.VISIBLE

    }
    fun afterLogout(){
        //clears textview upon logging out

        var clearUserName = intent.getStringExtra("clearName")
        if (clearUserName.isNullOrBlank()){
            clearUserName=""
        }
        loginUserNameET.setText(clearUserName)
    }

    fun acceptUsername(){
        //Take username and move to next intent
        val toChatFromLandingIntent:Intent= Intent(this,ChatActivity::class.java)

        var user= loginUserNameET.text.toString()
        Connector.sendMessage(":user ${user}")


        startActivity(toChatFromLandingIntent)

    }
}

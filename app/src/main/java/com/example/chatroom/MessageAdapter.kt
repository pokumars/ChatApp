package com.example.chatroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.chatroom.ClientSocket.msg


class MessageAdapter(private val context: Context,
                     private val dataSource: ArrayList<ChatMessage>):BaseAdapter( ){


    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* Finally, getView() creates a view to be used as a row in the list. Here you define what
         information shows and where it sits within the ListView. You also inflate a custom view
         from the XML layout defined in res/layout/list_item_recipe.xml — more on this in the next
          section.*/

        //msg is the name we give the data body we will get. it must then be split into
        // its parts to be able to display it. i.e time username and message
        val msg =getItem(position) as ChatMessage
        fun myMessage(_msg:ChatMessage):Boolean{
            if (User.userVar==msg.user) {return true}else{ return false}
        }

        if (myMessage(msg)){
            val myRowView= inflater.inflate(R.layout.other_msg_cell,parent,false)


            //finds the textview in the  layout for the listview and saves it as a value
            val msgTextView = myRowView.findViewById(R.id.txtMyMessage) as TextView
            val timeTextView = myRowView.findViewById(R.id.txtMyMessageTime) as TextView


            //sets the parts of the Chat message into their respective views
            msgTextView.text=msg.msg
            timeTextView.text=msg.timeNow


            return myRowView

        }else{
            val otherRowView= inflater.inflate(R.layout.other_msg_cell,parent,false)


            //finds the textview in the  layout for the listview and saves it as a value
            val userTextView = otherRowView.findViewById(R.id.txtOtherUser) as TextView
            val msgTextView = otherRowView.findViewById(R.id.txtOtherMessage) as TextView
            val timeTextView = otherRowView.findViewById(R.id.txtOtherMessageTime) as TextView


            //sets the parts of the Chat message into their respective views
            msgTextView.text=msg.msg
            userTextView.text=msg.user
            timeTextView.text=msg.timeNow


            return otherRowView
        }


    }

    override fun getItem(position: Int): Any {
        /*returns an item to be placed in a given position from
        the data source, specifically, Recipe objects obtained from dataSource.*/
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        /*This implements the getItemId() method that defines a unique ID for each row in the list.
         For simplicity, you just use the position of the item as its ID.*/
        return position.toLong()
    }


    override fun getCount(): Int {
        /*  lets ListView know how many items to display, or in other words, it returns the
    size of your data source.*/
        return dataSource.size
    }

}

/*
private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_OTHER_MESSAGE=2
class MessageAdapter(private val context: Context,
                     private val dataSource: ArrayList<ChatMessage>):BaseAdapter( ){

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* Finally, getView() creates a view to be used as a row in the list. Here you define what
         information shows and where it sits within the ListView. You also inflate a custom view
         from the XML layout defined in res/layout/list_item_recipe.xml — more on this in the next
          section.*/
        val rowView= inflater.inflate(R.layout.message_cell,parent,false)

        //finds the textview in the  layout for the listview and saves it as a value
        val msgTextView = rowView.findViewById(R.id.txtOtherMessage) as TextView
        val userTextView = rowView.findViewById(R.id.txtOtherUser) as TextView
        val timeTextView = rowView.findViewById(R.id.txtOtherMessageTime) as TextView

        //msg is the name we give the data body we will get. it must then be split into
        // its parts to be able to display it. i.e time username and message
        val msg =getItem(position) as ChatMessage

        //sets the parts of the Chat message into their respective views
        msgTextView.text=msg.msg
        userTextView.text=msg.user
        timeTextView.text=msg.timeNow

        return rowView
    }

    override fun getItem(position: Int): Any {
        /*returns an item to be placed in a given position from
        the data source, specifically, Recipe objects obtained from dataSource.*/
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        /*This implements the getItemId() method that defines a unique ID for each row in the list.
         For simplicity, you just use the position of the item as its ID.*/
        return position.toLong()
    }


    override fun getCount(): Int {
        /*  lets ListView know how many items to display, or in other words, it returns the
    size of your data source.*/
        return dataSource.size
    }

}
*/
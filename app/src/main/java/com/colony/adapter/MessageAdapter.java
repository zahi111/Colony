package com.colony.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colony.R;
import com.colony.model.Message;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    //user id
    private String userId;
    private Context context;

    //Tag for tracking self message
    private int SELF = 783;

    //ArrayList of messages object containing all the messages in the thread
    private ArrayList<Message> messages;

    //Constructor
    public MessageAdapter(Context context, ArrayList<Message> messages, String userId) {
        this.userId = userId;
        this.messages = messages;
        this.context = context;
    }

    //IN this method we are tracking the self message
    @Override
    public int getItemViewType(int position) {
        //getting message object of current position
        Message message = messages.get(position);

        //If its owner  id is  equals to the logged in user id
        if (message.getUserNumber().equals(userId)) {
            //Returning self
            return SELF;
        }
        //else returning position
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Creating view
        View itemView;
        //if view type is self
        if (viewType == SELF) {
            //Inflating the layout self
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_message_right, parent, false);
        } else {
            //else inflating the layout others
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_message_left, parent, false);
        }
        //returing the view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Adding messages to the views
        Message message = messages.get(position);
        holder.textViewMessage.setText(message.getMessage());
        holder.textViewTime.setText(message.getName() + ", " + message.getSentAt());
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    //Initializing views
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessage;
        public TextView textViewTime;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewMessage = (TextView) itemView.findViewById(R.id.textViewMessage);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewDate);
        }
    }
}
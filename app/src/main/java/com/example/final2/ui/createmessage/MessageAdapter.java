package com.example.final2.ui.createmessage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final2.GroupModel;
import com.example.final2.MessageModel;
import com.example.final2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{
    List<MessageModel> messageModelList;

    public MessageAdapter(List<MessageModel> messageModelList){
        this.messageModelList=messageModelList;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_createmessage_message,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageModel messageModel=messageModelList.get(position);
        holder.setData(messageModel);
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView messageName,messageDesctription;
        public MessageViewHolder(View itemView){
            super(itemView);
            messageName=itemView.findViewById(R.id.createmessage_name);
            messageDesctription=itemView.findViewById(R.id.createmessage_description);
        }
        public void setData(MessageModel messageModel){
            messageName.setText(messageModel.getName());
            messageDesctription.setText(messageModel.getDescription());
        }
    }
}

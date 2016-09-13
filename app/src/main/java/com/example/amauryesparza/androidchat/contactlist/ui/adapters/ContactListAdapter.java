package com.example.amauryesparza.androidchat.contactlist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amauryesparza.androidchat.R;
import com.example.amauryesparza.androidchat.entities.User;
import com.example.amauryesparza.androidchat.lib.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AmauryEsparza on 9/12/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<User> contactList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ContactListAdapter(OnItemClickListener clickListener, List<User> contactList, ImageLoader imageLoader) {
        this.clickListener = clickListener;
        this.contactList = contactList;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contactList.get(position);
        holder.setOnClickListener(user, clickListener);

        String isOnline = user.isOnline() ? "Online" : "Offline";
        int color = user.isOnline() ? Color.GREEN : Color.RED;
        holder.txtContactName.setText(user.getEmail());
        holder.txtContactStatus.setText(isOnline);
        holder.txtContactStatus.setTextColor(color);

        imageLoader.loadImage(holder.contactIcon, "");
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.contactIcon)
        CircleImageView contactIcon;
        @BindView(R.id.txtContactName)
        TextView txtContactName;
        @BindView(R.id.txtContactStatus)
        TextView txtContactStatus;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        private void setOnClickListener(final User user, final OnItemClickListener listener){
            //Click listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });
            //Long click listener
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(user);
                    return true;
                }
            });
        }
    }
}


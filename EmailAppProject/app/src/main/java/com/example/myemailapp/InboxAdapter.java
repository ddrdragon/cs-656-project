package com.example.myemailapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InboxAdapter extends ArrayAdapter<Inbox> {
    private int resourceId;

    public InboxAdapter(Context context, int textViewResourceId, List<Inbox> object) {
        super(context, textViewResourceId, object);
        resourceId = textViewResourceId;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Inbox inbox = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView subject = (TextView) view.findViewById(R.id.subject);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView from = (TextView) view.findViewById(R.id.from);
        subject.setText("Subject: " + inbox.getSubject());
        date.setText("Sent Date: " + inbox.getDate());
        from.setText("From: " + inbox.getFrom());
        return view;
    }
}

class Inbox {
    private String subject;
    private String date;
    private String from;
    private String content;

    public Inbox(String from, String subject, String date, String content) {
        this.subject = subject;
        this.date = date;
        this.from = from;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() { return from; }

    public String getContent() { return content; }

}

package com.example.myemailapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Inbox2Activity extends AppCompatActivity {

    TextView from, subject, content, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox2);

        Intent intent = getIntent();
        int p = intent.getIntExtra("position", 0);
        from = (TextView) findViewById(R.id.from_inbox2);
        subject = (TextView) findViewById(R.id.subject_inbox2);
        content = (TextView) findViewById(R.id.content_inbox2);
        date = (TextView) findViewById(R.id.date_inbox2);

        from.setText(Receiver.inboxList.get(p).getFrom());
        subject.setText(Receiver.inboxList.get(p).getSubject());
        content.setText(Receiver.inboxList.get(p).getContent());
        date.setText(Receiver.inboxList.get(p).getDate());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inbox2_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_inbox2: finish(); break;
            default:
        }
        return true;
    }
}

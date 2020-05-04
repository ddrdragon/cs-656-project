package com.example.myemailapp;

import android.content.Intent;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import java.io.IOException;

import static com.example.myemailapp.MainActivity.accountInfo;

public class NewEmail extends AppCompatActivity {

    private Button button;
    private EditText address_text, subject_text, message_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_email);

        address_text = (EditText) findViewById(R.id.Recipient);
        subject_text = (EditText) findViewById(R.id.Subject);
        message_text = (EditText) findViewById(R.id.Message);

        button = (Button) findViewById(R.id.Send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        accountInfo.setAddress(address_text.getText().toString());
                        accountInfo.setSubject(subject_text.getText().toString());
                        accountInfo.setMessage(message_text.getText().toString());

                        Sender s = new Sender(accountInfo);
                        Looper.prepare();
                        try {
                            s.sendMail();
                            Receiver r = new Receiver(accountInfo);
                            r.receive();
                            Toast.makeText(NewEmail.this, "Email Sent", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        catch (MessagingException | IOException e) {
                            Toast.makeText(NewEmail.this, "Invalid Addresses", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newemail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_newEmail: finish(); break;
            default:
        }
        return true;
    }
}

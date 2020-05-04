package com.example.myemailapp;

import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import javax.mail.MessagingException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText username_text, password_text;
    public static AccountInfo accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_text = (EditText) findViewById(R.id.username);
        password_text = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.log_in);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        accountInfo = new AccountInfo();
                        accountInfo.setUsername(username_text.getText().toString());
                        accountInfo.setPassword(password_text.getText().toString());

                        Receiver r = new Receiver(accountInfo);

                        Looper.prepare();
                        try {
                            r.receive();
                            Toast.makeText(MainActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent("InboxActivity"));
                        }
                        catch (MessagingException | IOException e) {
                            Toast.makeText(MainActivity.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
    }
}

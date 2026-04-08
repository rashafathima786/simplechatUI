package com.example.chatapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    ImageButton btnSend;
    List<Message> messageList;
    ChatAdapter adapter;

    String[] replies = {
            "Hey! 😊", "That's interesting!", "Tell me more 🤔",
            "Haha nice 😄", "I agree!", "Really? 👀", "Cool! 🔥"
    };
    int replyIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);
        btnSend = findViewById(R.id.btnSend);

        messageList = new ArrayList<>();
        adapter = new ChatAdapter(messageList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        addMessage("Hi there! 👋 Say something!", getTime(), false);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                if (!text.isEmpty()) {
                    addMessage(text, getTime(), true);
                    editText.setText("");

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String reply = replies[replyIndex % replies.length];
                            replyIndex++;
                            addMessage(reply, getTime(), false);
                        }
                    }, 1000);
                }
            }
        });
    }

    void addMessage(String text, String time, boolean isSent) {
        messageList.add(new Message(text, time, isSent));
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date());
    }
}
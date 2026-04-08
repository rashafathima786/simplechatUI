package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        List<String[]> contacts = Arrays.asList(
                new String[]{"👩", "Aishwarya", "Hey! What's up?"},
                new String[]{"👧", "Rasha", "Did you finish the project?"},
                new String[]{"👩‍💻", "Tejaswini", "The app looks great! 🔥"},
                new String[]{"🧑", "Rahul", "When's the submission?"},
                new String[]{"👦", "Arjun", "Let's test the chat!"}
        );

        RecyclerView recyclerView = findViewById(R.id.contactsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(contacts));
    }

    class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

        List<String[]> contacts;

        ContactsAdapter(List<String[]> contacts) {
            this.contacts = contacts;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_contact, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String[] contact = contacts.get(position);
            holder.avatar.setText(contact[0]);
            holder.name.setText(contact[1]);
            holder.lastMsg.setText(contact[2]);

            // Tap contact → open chat
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ContactsActivity.this, MainActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView avatar, name, lastMsg;
            ViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.txtAvatar);
                name = itemView.findViewById(R.id.txtName);
                lastMsg = itemView.findViewById(R.id.txtLastMsg);
            }
        }
    }
}
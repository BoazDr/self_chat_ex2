package com.example.self_chat_ex2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    MyAdapter mAdapter;
    ArrayList<String> allMessages;
    private static final String TEXT_VALUE = "textValue";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.plain_text_input);
        Button sendButton = findViewById(R.id.send_button);
        if (savedInstanceState == null) {
            allMessages = new ArrayList<>();
        }
        else {
            allMessages = savedInstanceState.getStringArrayList(TEXT_VALUE);
        }
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this, allMessages);
        recyclerView.setAdapter(mAdapter);

        //Toast init
        final Context context = getApplicationContext();
        final CharSequence errorText = "Empty message? Realy? try again...";
        final int duration = Toast.LENGTH_SHORT;

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString();
                if (item.isEmpty()){
                    Toast toast = Toast.makeText(context, errorText, duration);
                    toast.show();
                }
                else{
                    editText.setText("");
                    allMessages.add(item);
                    mAdapter.notifyItemInserted(allMessages.size());
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(TEXT_VALUE, allMessages);
    }
}
package com.haya.sharedpreferences_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences dataStore;
    private EditText editText;
    private TextView textWrite, textRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // "DataStore"という名前でインスタンスを生成
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);

        editText = findViewById(R.id.edit_text);
        textWrite = findViewById(R.id.text_write);
        textRead = findViewById(R.id.text_read);

        Button buttonWrite = findViewById(R.id.button_write);
        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                String text = editText.getText().toString();
                textWrite.setText(text);
                // 入力文字列を"input"に書き込む
                SharedPreferences.Editor editor = dataStore.edit();
                editor.putString("input", text);
                //editor.commit();
                editor.apply();
            }
        });

        Button buttonRead = findViewById(R.id.button_read);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "input"から読み出す、何もないときは"Nothing"を返す
                String str = dataStore.getString("input", "Nothing");
                if(!str.equals("Nothing")) {
                    textRead.setText(str);
                }
            }
        });
    }
}
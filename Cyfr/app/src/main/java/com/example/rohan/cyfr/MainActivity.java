package com.example.rohan.cyfr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    public String fun_encrypt(String message, String key){
        StringBuilder result = new StringBuilder();

        char[] codeList = message.toCharArray();
        char[] keyList  = key.toCharArray();

        int maxCount = key.length();
        int i = 0;
        for (Character code : codeList) {

            int keys = Character.getNumericValue(keyList[i]);

            if(keys % 2 == 0)
            {
                int res = code+keys;
                result.append((char)res);
            }
            else
            {
                int res = code-keys;
                result.append((char)res);
            }
            i++;
            if(i==maxCount)
            {
                i = 0;
            }
        }
        return result.toString();
    }

    public String fun_decrypt(String message, String key){
        StringBuilder result = new StringBuilder();

        char[] codeList = message.toCharArray();
        char[] keyList  = key.toCharArray();

        int maxCount = key.length();
        int i = 0;
        for (Character code : codeList) {

            int keys = Character.getNumericValue(keyList[i]);

            if(keys % 2 == 0)
            {
                int res = code-keys;
                result.append((char)res);
            }
            else
            {
                int res = code+keys;
                result.append((char)res);
            }
            i++;
            if(i==maxCount)
            {
                i = 0;
            }
        }
        return result.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText message_encrypt = (EditText) findViewById(R.id.encrMessage);
        final EditText message_decrypt = (EditText) findViewById(R.id.decrMessage);
        final EditText key_encrypt = (EditText) findViewById(R.id.encrKey);
        final EditText key_decrypt = (EditText) findViewById(R.id.decrKey);
        final TextView ans = (TextView) findViewById(R.id.textView12);
        Button decrypt = (Button) findViewById(R.id.decrButton);
        Button encrypt = (Button) findViewById(R.id.encrButton);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message1 = message_encrypt.getText().toString();
                String key1 = key_encrypt.getText().toString();

                ans.setText(fun_encrypt(message1, key1));
            }
            });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message2 = message_decrypt.getText().toString();
                String key2 = key_decrypt.getText().toString();

                ans.setText(fun_decrypt(message2, key2));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

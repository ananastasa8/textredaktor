package com.example.kazaco;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "document.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_open){

                EditText editor = findViewById(R.id.editText);
                File file = getExternalPath();
                if(!file.exists()) return true;
                try(FileInputStream fin =  new FileInputStream(file)) {
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    String text = new String (bytes);
                    editor.setText(text);
                }
                catch(IOException ex) {

                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return true;}
        else if(id == R.id.action_save)
        {
                try(FileOutputStream fos = new FileOutputStream(getExternalPath())) {
                    EditText textBox = findViewById(R.id.editText);
                    String text = textBox.getText().toString();
                    fos.write(text.getBytes());
                    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
                }
                catch(IOException ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
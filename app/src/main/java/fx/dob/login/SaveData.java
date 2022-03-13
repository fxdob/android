package fx.dob.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SaveData extends AppCompatActivity {

    EditText inputField;
    Button writeButton;
    TextView displayText;
    Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        inputField = findViewById(R.id.inputText);
        writeButton = findViewById(R.id.BtnWrite);
        displayText = findViewById(R.id.displayData);
        readButton = findViewById(R.id.BtnShow);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }

    public void writeFile(){
        String textToSave = inputField.getText().toString();
        try{
            FileOutputStream fos = openFileOutput("data.json", MODE_PRIVATE);
            fos.write(textToSave.getBytes(StandardCharsets.UTF_8));
            fos.close();

            //Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT.show());
            inputField.setText("");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {
            FileInputStream fis =  openFileInput("data.json");
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();

            String lines;
            while((lines = br.readLine()) != null){
                sb.append(lines + "\n");
            }
            displayText.setText(sb.toString());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
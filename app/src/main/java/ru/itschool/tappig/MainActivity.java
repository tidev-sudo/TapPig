package ru.itschool.tappig;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static  final String PREFS_NAME = "PigsPrefs";
    private static final String KEY_N_TAPS = "nTaps";

    int nTaps;
    TextView textTop;
    TextView textBottom;
    ImageView imgPig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textTop = findViewById(R.id.textTop);
        textBottom = findViewById(R.id.textBottom);
        imgPig = findViewById(R.id.imagePig);
        loadPigs();
        updateTextBottom();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        savePigs();
    }

    public void tap(View view) {

        nTaps++;
        updateTextBottom();

    }

    void updateTextBottom(){
        textBottom.setText("вы потрогали свинью " + nTaps + end());
    }

    String end() {
        if (nTaps % 10 > 1 && nTaps % 10 < 5 && nTaps / 10 % 10 != 1){
            return " раза";
        }
        return" раз";
    }

    public void savePigs(){

        SharedPreferences prefs = getSharedPreferences("PigsPrefs", Context.MODE_PRIVATE);
        prefs.edit().putInt("nun taps", nTaps).apply();
    }

    public void loadPigs(){
        SharedPreferences prefs = getSharedPreferences("PigsPrefs", Context.MODE_PRIVATE);
        nTaps = prefs.getInt("nun taps", 0);
    }
}
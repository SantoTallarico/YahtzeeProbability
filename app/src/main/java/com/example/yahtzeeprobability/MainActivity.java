package com.example.yahtzeeprobability;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.btnCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] rolls = new int[5];
                rolls[0] = Integer.parseInt(((EditText)(findViewById(R.id.inpNum1))).getText().toString());
                rolls[1] = Integer.parseInt(((EditText)(findViewById(R.id.inpNum2))).getText().toString());
                rolls[2] = Integer.parseInt(((EditText)(findViewById(R.id.inpNum3))).getText().toString());
                rolls[3] = Integer.parseInt(((EditText)(findViewById(R.id.inpNum4))).getText().toString());
                rolls[4] = Integer.parseInt(((EditText)(findViewById(R.id.inpNum5))).getText().toString());

                double[][] probabilities = ProbabilityEngine.YahtzeeProbabilities(rolls);

                ((TextView)(findViewById(R.id.txtProbability))).setText("" + probabilities[0][0] + ", " +
                        probabilities[0][1] + ", " + probabilities[0][2] + ", " + probabilities[0][3] + ", " +
                        probabilities[0][4] + ", " + probabilities[6][0] + ", " + probabilities[7][0] + ", " +
                        probabilities[11][0]);
            }
        });
    }


}

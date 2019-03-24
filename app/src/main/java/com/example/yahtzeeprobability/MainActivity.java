package com.example.yahtzeeprobability;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner die1 = (Spinner)findViewById(R.id.die1);
        Spinner die2 = (Spinner)findViewById(R.id.die2);
        Spinner die3 = (Spinner)findViewById(R.id.die3);
        Spinner die4 = (Spinner)findViewById(R.id.die4);
        Spinner die5 = (Spinner)findViewById(R.id.die5);
        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(this,
                new Integer[]{R.drawable.die1, R.drawable.die2, R.drawable.die3, R.drawable.die4, R.drawable.die5, R.drawable.die6});
        die1.setAdapter(adapter);
        die2.setAdapter(adapter);
        die3.setAdapter(adapter);
        die4.setAdapter(adapter);
        die5.setAdapter(adapter);

        final Button button = findViewById(R.id.btnCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] rolls = new int[5];
                rolls[0] = ((Spinner) findViewById(R.id.die1)).getSelectedItemPosition() + 1;
                rolls[1] = ((Spinner) findViewById(R.id.die2)).getSelectedItemPosition() + 1;
                rolls[2] = ((Spinner) findViewById(R.id.die3)).getSelectedItemPosition() + 1;
                rolls[3] = ((Spinner) findViewById(R.id.die4)).getSelectedItemPosition() + 1;
                rolls[4] = ((Spinner) findViewById(R.id.die5)).getSelectedItemPosition() + 1;

                double[][] probabilities = ProbabilityEngine.YahtzeeProbabilities(rolls);

                ((TextView)(findViewById(R.id.txtProbability))).setText("" + probabilities[0][0] + ", " +
                        probabilities[0][1] + ", " + probabilities[0][2] + ", " + probabilities[0][3] + ", " +
                        probabilities[0][4] + ", " + probabilities[6][0] + ", " + probabilities[7][0] + ", " +
                        probabilities[11][0]);
            }
        });
    }


}

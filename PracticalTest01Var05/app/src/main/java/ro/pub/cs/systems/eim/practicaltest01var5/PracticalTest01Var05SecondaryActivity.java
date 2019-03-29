package ro.pub.cs.systems.eim.practicaltest01var5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {
    private Button okButton;
    private TextView gainedTextView;
    String str = "";

    private class okClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            setResult(Activity.RESULT_OK, new Intent());
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(new okClickListener());
        gainedTextView = (TextView) findViewById(R.id.gained_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("message")) {
            str = "Gained ";
        }


        if (intent != null && intent.getExtras().containsKey("gain")) {
            str = str + " " + intent.getIntExtra("gain", -1);
        }

        gainedTextView.setText(str.toString());
    }
}

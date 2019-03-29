package ro.pub.cs.systems.eim.practicaltest01var5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {
    private EditText starEditText;
    private EditText zeroEditText;
    private EditText oneEditText;
    private CheckBox starCheckBox;
    private CheckBox zeroCheckBox;
    private CheckBox oneCheckBox;
    private Button playButton;
    private int scor = 0;
    int serviceStatus = 0;
    int count = 0;
    private IntentFilter intentFilter = new IntentFilter();

    Random random = new Random();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("data"));
            Toast.makeText(getApplicationContext(), intent.getStringExtra("data"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("scor", String.valueOf(scor));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("scor")) {
            Toast.makeText(this, "The activity returned with scor: " + savedInstanceState.getString("scor"), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "The activity returned with scor: " + String.valueOf(scor), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2017) {
            Toast.makeText(this, "The activity returned with scor: " + scor, Toast.LENGTH_LONG).show();
        }
    }

    private class PlayButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            count = 0;
            String number = "";
            String nr1 = "", nr2="", nr3="";
            int nr;
            if (starCheckBox.isChecked()) {
                number = number + " " + " 0";
                count++;
                nr1 = "*";
            }
            else {
                nr = random.nextInt(4);
                number = number + " " + String.valueOf(nr);
                nr1 = String.valueOf(nr);
            }
            if (zeroCheckBox.isChecked()) {
                count++;
                nr2 = "0";
                number = number + zeroEditText.getText().toString();
            }
            else {

                nr = random.nextInt(4);
                nr2 = String.valueOf(nr);
                number = number + " " + String.valueOf(nr);
            }

            if (oneCheckBox.isChecked()) {
                count++;
                nr3 = "1";
                number = number + " " + starEditText.getText().toString();
            } else {
                nr = random.nextInt(4);
                nr3 = String.valueOf(nr);
                number = number + " " + String.valueOf(nr);
            }
            Toast.makeText(getApplicationContext(), number, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
            if (nr1.equals(nr2) && nr2.equals(nr3)) {
                intent.putExtra("messsage", "Gained");
            }
            count = count * 10;
            scor = scor + count;
            if (scor > 0 && serviceStatus == 0) {
//                serviceStatus = 1;
                intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                intent.putExtra("data", scor);
                getApplicationContext().startService(intent);

            }
//            intent.putExtra("gain", count);
//            startActivityForResult(intent, 2017);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);
        if (savedInstanceState != null) {
            Toast.makeText(this, "The activity returned with scor: " + savedInstanceState.getString("scor"), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "The activity returned with scor: " + String.valueOf(scor), Toast.LENGTH_LONG).show();
        }

        starEditText = (EditText) findViewById(R.id.start_edit_text);
        zeroEditText = (EditText) findViewById(R.id.zero_edit_text);
        oneEditText = (EditText) findViewById(R.id.one_edit_text);

        starCheckBox = (CheckBox) findViewById(R.id.star_chech_box);
        zeroCheckBox = (CheckBox) findViewById(R.id.zero_chech_box);
        oneCheckBox = (CheckBox) findViewById(R.id.one_chech_box);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new PlayButtonListener());
        intentFilter.addAction("Victory");
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }
}

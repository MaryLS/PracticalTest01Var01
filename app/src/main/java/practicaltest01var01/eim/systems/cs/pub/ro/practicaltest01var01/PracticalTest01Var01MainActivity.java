package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private final static int SERVICE_STARTED = 1;
    private final static int SERVICE_STOPPED = 0;
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";
    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    private int serviceStatus = SERVICE_STOPPED;

    Button north;
    Button south;
    Button east;
    Button west;
    Button activity;
    TextView textAll;
    int numberPress;

    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.buttonNorth:
                    numberPress++;
                    textAll.setText(textAll.getText().toString() + "North, ");
                    break;
                case R.id.buttonSouth:
                    numberPress++;
                    textAll.setText(textAll.getText().toString() + "South, ");
                    break;
                case R.id.buttonEast:
                    numberPress++;
                    textAll.setText(textAll.getText().toString() + "East, ");
                    break;
                case R.id.buttonWest:
                    numberPress++;
                    textAll.setText(textAll.getText().toString() + "West, ");
                    break;
                case R.id.buttonActivity:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);

                    numberPress = 0;

                    String text = textAll.getText().toString();
                    textAll.setText("");

                    intent.putExtra("text", text);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);

                    break;
            }

            if (numberPress == 4){
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                intent.putExtra("text", textAll.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = SERVICE_STARTED;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        numberPress = 0;

        north = findViewById(R.id.buttonNorth);
        north.setOnClickListener(buttonClickListener);

        south = findViewById(R.id.buttonSouth);
        south.setOnClickListener(buttonClickListener);

        east = findViewById(R.id.buttonEast);
        east.setOnClickListener(buttonClickListener);

        west = findViewById(R.id.buttonWest);
        west.setOnClickListener(buttonClickListener);

        activity = findViewById(R.id.buttonActivity);
        activity.setOnClickListener(buttonClickListener);

        textAll = findViewById(R.id.textAll);

        for (int index = 0; index < ProcessingThread.actionTypes.length; index++) {
            intentFilter.addAction(ProcessingThread.actionTypes[index]);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("numberPress", Integer.toString(numberPress));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("numberPress")) {
            numberPress = Integer.parseInt(savedInstanceState.getString("numberPress"));
        } else {
            numberPress = 0;
        }
        Toast.makeText(this, "Number is " + numberPress, Toast.LENGTH_LONG).show();
        Log.d("number", "Number is " + numberPress);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(BROADCAST_RECEIVER_TAG, intent.getStringExtra(BROADCAST_RECEIVER_EXTRA));
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
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var01Service.class);
        stopService(intent);
        super.onDestroy();
    }
}

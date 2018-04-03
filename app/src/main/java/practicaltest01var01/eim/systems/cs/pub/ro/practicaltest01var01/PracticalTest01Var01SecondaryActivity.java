package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {

    Button register;
    Button cancel;
    TextView textAll;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.buttonRegister:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.buttonCancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_secondary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(buttonClickListener);

        cancel = findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(buttonClickListener);

        textAll = findViewById(R.id.text2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("text")) {
            String text = intent.getStringExtra("text");
            textAll.setText(text);
        }
    }

}

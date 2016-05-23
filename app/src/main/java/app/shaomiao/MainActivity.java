package app.shaomiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView makecode,shao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makecode  = (TextView) findViewById(R.id.makecode);
        shao  = (TextView) findViewById(R.id.shao);


        makecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MakeCodeActivity.class);
                startActivity(intent);
            }
        });

        shao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MipcaActivityCapture.class);
                startActivity(intent);
            }
        });
    }
}

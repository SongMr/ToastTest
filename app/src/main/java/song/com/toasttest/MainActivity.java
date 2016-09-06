package song.com.toasttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.normal).setOnClickListener(this);
        findViewById(R.id.custom).setOnClickListener(this);
        findViewById(R.id.diy).setOnClickListener(this);
        findViewById(R.id.diytime).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast toast = new Toast(this);
        switch (v.getId()){
            case R.id.normal:
                Toast.makeText(this,"BasicsToast",Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom:
                toast.setView(LayoutInflater.from(this).inflate(R.layout.custom_toast_layout, null, false));
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.diy:
                toast.setView(LayoutInflater.from(this).inflate(R.layout.custom_toast_layout, null, false));
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0 ,0);
                toast.show();
                break;
            case R.id.diytime:
                MyToast.makeText(this,"zidingyitime",8000).show();

        }
    }
}

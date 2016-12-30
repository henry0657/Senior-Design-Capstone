package htoups2.prototype;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class LogActivity extends AppCompatActivity {
    EditText ET_street, ET_lat, ET_long, ET_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ET_street = (EditText) findViewById(R.id.streetET);
        ET_lat = (EditText) findViewById(R.id.latitudeET);
        ET_long = (EditText) findViewById(R.id.longitudeET);
        ET_height = (EditText) findViewById(R.id.heightET);
    }

    public void OnPost(View view) {
        String street = ET_street.getText().toString();
        String latitude = ET_lat.getText().toString();
        String longitude = ET_long.getText().toString();
        String height = ET_height.getText().toString();

        String type = "post";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, street, latitude, longitude, height);


    }

    public void OnGet(View view) throws InterruptedException {

        String type = "get";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
        try {
            String JSON_string = backgroundWorker.get();

            if (JSON_string == null) {
                Toast.makeText(getApplicationContext(), "First Get JSON", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, DisplayListView.class);
                intent.putExtra("json_data", JSON_string);
                startActivity(intent);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}

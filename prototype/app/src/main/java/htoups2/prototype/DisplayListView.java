package htoups2.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DisplayListView extends AppCompatActivity {
    private String JSON_string;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private DataAdapter dataAdapter;
    private ListView listView;


    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView) findViewById(R.id.listview);
        dataAdapter = new DataAdapter(this,R.layout.row_layout);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Data dat = (Data) arg0.getItemAtPosition(position);
                try {
                    Intent intent  = new Intent(DisplayListView.this, MapsActivity.class);
                    intent.putExtra("height", dat.getHeight());
                    intent.putExtra("latitude", dat.getLatitude());
                    intent.putExtra("longitude", dat.getLongitude());
                    intent.putExtra("street",dat.getStreet());
                    intent.putExtra("timestamp",dat.getTimestamp());
                    startActivity(intent);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        JSON_string = getIntent().getExtras().getString("json_data");

        try {
        jsonObject = new JSONObject(JSON_string);
        jsonArray = jsonObject.getJSONArray("server_response");

        String street, timestamp;
            Double latitude;
            Double longitude;
            Double height;

            while(count<jsonArray.length())
            {
            JSONObject JO = jsonArray.getJSONObject(count);
                street = JO.getString("street");
                timestamp = JO.getString("timestamp");
                longitude = JO.getDouble("longitude");
                latitude = JO.getDouble("latitude");
                height = JO.getDouble("height");

            Data data = new Data(street,timestamp, latitude,longitude,height);
            dataAdapter.add(data);
            count++;

            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }




}



package htoups2.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 9/27/2016.
 */
public class DataAdapter extends ArrayAdapter {
    List list = new ArrayList();


    public DataAdapter(Context context, int resource) {
        super(context, resource);
    }



    public void add(Data object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row;
        row = convertView;
        DataHolder dataHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            dataHolder = new DataHolder();
            dataHolder.tx_street = (TextView) row.findViewById(R.id.streetTV);
            dataHolder.tx_latitude = (TextView) row.findViewById(R.id.latTV);
            dataHolder.tx_longitude = (TextView) row.findViewById(R.id.longTV);
            dataHolder.tx_timestamp = (TextView) row.findViewById(R.id.timestampTV);
            dataHolder.tx_height = (TextView) row.findViewById(R.id.heightTV);
            dataHolder.ix_state = (ImageView) row.findViewById(R.id.imageIV);
            row.setTag(dataHolder);
        }
        else {
            dataHolder = (DataHolder) row.getTag();
        }

        Data data = (Data) this.getItem(position);

        Data former;
        if(position != 0){
              former = (Data) this.getItem(position-1);
        }
        else  former = data;

        dataHolder.tx_street.setText(data.getStreet());
        dataHolder.tx_timestamp.setText(data.getTimestamp());
        dataHolder.tx_latitude.setText(data.getLatitude().toString());
        dataHolder.tx_longitude.setText(data.getLongitude().toString());
        dataHolder.tx_height.setText(data.getHeight().toString());
        if(data.getHeight() > 9.0){

            dataHolder.ix_state.setImageResource(R.mipmap.red_circle);

        }else if(data.getHeight() > 3.0)
            dataHolder.ix_state.setImageResource(R.mipmap.yellow_circle);
        else
            dataHolder.ix_state.setImageResource(R.mipmap.green_circle);

        return row;
    }

    static class DataHolder {
        TextView tx_street, tx_latitude,tx_longitude, tx_height,tx_timestamp;
        ImageView ix_state;
    }

}

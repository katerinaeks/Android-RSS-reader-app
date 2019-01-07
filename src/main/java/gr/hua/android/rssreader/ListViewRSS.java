package gr.hua.android.rssreader;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ListViewRSS extends AppCompatActivity {

    private ListView listview;
    private ArrayAdapter<String> adapter;
    private String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        listview = (ListView) findViewById(R.id.listview1);
        listview.setBackgroundColor(Color.BLACK);
        Bundle dataReceived = getIntent().getExtras(); //receive tou bundle tis activity pou me kalese
        data=new String[2];
        if (dataReceived != null) {
            data[0] = dataReceived.getString("Title");
            data[1] = dataReceived.getString("Description");
        }
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;// ListView Clicked item index
                String itemValue = (String) listview.getItemAtPosition(position);    // ListView Clicked item value
                Toast.makeText(getApplicationContext(),itemValue, Toast.LENGTH_SHORT).show();
            }

        });
    }
}

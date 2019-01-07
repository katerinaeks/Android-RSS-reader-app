package gr.hua.android.rssreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private EditText inputRSSUrl;
    private Button button1;
    private Bundle bundle;
    private parseXML parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inputRSSUrl=(EditText)findViewById(R.id.editTextURL);
        this.button1=(Button)findViewById(R.id.button1);
        this.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button Debug", "Inside onclickListener");
                if (MainActivity.this.inputRSSUrl.getText().toString().equals(""))
                MainActivity.this.showDialogBox("Warning Message","You must input a valid URL");
                else {
                    String url=MainActivity.this.inputRSSUrl.getText().toString();
                    parser = new parseXML(url);
                    parser.fetchXML();

                    while(parser.parsingComplete); //kathisterisi gia to thread tou obj pou kanei fetch to rss xml
                    MainActivity.this.bundle=new Bundle();
                    MainActivity.this.bundle.putString("Title", parser.getTitle());
                    MainActivity.this.bundle.putString("Description",parser.getDescription());

                    Intent startListView=new Intent(MainActivity.this,ListViewRSS.class);
                    startListView.putExtras(MainActivity.this.bundle);
                    startActivity(startListView);
                }
            }
        });
    }
    public void showDialogBox(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

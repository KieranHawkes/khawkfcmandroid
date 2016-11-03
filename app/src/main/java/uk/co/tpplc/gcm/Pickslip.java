package uk.co.tpplc.gcm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by khawk on 14/03/2016.
 */
public class Pickslip extends AppCompatActivity {
    private static final String TAG = "PICKSLIP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "INSIDE PICKSLIP ");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        String pick = extras.getString("PickDets");

        try {
            JSONObject dets = new JSONObject(pick);
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ListView lv = new ListView(this);
            for (int i = 0; i < dets.getJSONArray("Lines").length(); i++) {
                JSONObject line = dets.getJSONArray("Lines").getJSONObject(i);
                TextView lineText = new TextView(this);
                StringBuilder text = new StringBuilder();
                text.append(line.get("LineNo"));
                text.append(line.get("Description"));
                text.append(line.get("CoC"));
                text.append(line.get("Qty"));
                text.append(line.get("Weight"));
                text.append(line.get("prodCode"));
                text.append(line.get("Location"));
                lineText.setText(text.toString());
                lineText.setTextColor(Color.BLACK);

                ll.addView(lineText);
            }

            TextView extraText = new TextView(this);
            StringBuilder ext = new StringBuilder();
            ext.append(dets.get("ExtraText"));
            ext.append(dets.get("CocLine"));
            extraText.setText(ext.toString());
            extraText.setTextColor(Color.BLACK);
            ll.addView(extraText);

            setContentView(ll);
        } catch (JSONException e) {
            e.printStackTrace();
        }

/*
        WebView web = new WebView(this);
        final String mineType = "text/html";
        final String encoding = "UTF-8";
        web.loadDataWithBaseURL("", pick, mineType, encoding, "");
        setContentView(web);*/


      /*  TextView dets =  new TextView(this);
        dets.setText(pick);
        dets.setTextColor(Color.BLACK);
        setContentView(dets);*/

    }
}

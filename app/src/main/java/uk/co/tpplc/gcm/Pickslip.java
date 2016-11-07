package uk.co.tpplc.gcm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
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
            TableLayout ll = new TableLayout(this);
            ll.setOrientation(TableLayout.VERTICAL);
            TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
            tableRowParams.setMargins(8,2,8,2);
            TableRow headings = new TableRow(this);
            TextView hdr1 = new TextView(this);
            TextView hdr2 = new TextView(this);
            TextView hdr3 = new TextView(this);
            hdr1.setText(R.string.heading1);
            hdr2.setText(R.string.heading2);
            hdr3.setText(R.string.heading3);
            hdr1.setTypeface(null, Typeface.BOLD);
            hdr2.setTypeface(null, Typeface.BOLD);
            hdr3.setTypeface(null, Typeface.BOLD);
            headings.addView(hdr1,tableRowParams);
            headings.addView(hdr2,tableRowParams);
            headings.addView(hdr3,tableRowParams);
            ll.addView(headings,tableRowParams);

 //           ListView lv = new ListView(this);
            for (int i = 0; i < dets.getJSONArray("Lines").length(); i++) {
                JSONObject line = dets.getJSONArray("Lines").getJSONObject(i);
                TableRow tableRow = new TableRow(this);
                TextView prodCode = new TextView(this);
                prodCode.setText(line.get("prodCode").toString());
                TextView description = new TextView(this);
                description.setText(line.get("Description").toString());
                TextView location = new TextView(this);
                location.setText(line.get("Location").toString());
                tableRow.addView(prodCode,tableRowParams);
                tableRow.addView(description,tableRowParams);
                tableRow.addView(location,tableRowParams);
                ll.addView(tableRow,tableRowParams);

          /*      StringBuilder text = new StringBuilder();
                text.append(line.get("LineNo"));
                text.append(line.get("Description"));
                text.append(line.get("CoC"));
                text.append(line.get("Qty"));
                text.append(line.get("Weight"));
                text.append(line.get("prodCode"));
                text.append(line.get("Location"));
                lineText.setText(text.toString());
                lineText.setTextColor(Color.BLACK);

                ll.addView(lineText);*/
            }
/*
            TextView extraText = new TextView(this);
            StringBuilder ext = new StringBuilder();
            ext.append(dets.get("ExtraText"));
            ext.append(dets.get("CocLine"));
            extraText.setText(ext.toString());
            extraText.setTextColor(Color.BLACK);
            ll.addView(extraText);*/

            setContentView(ll);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

package uk.co.tpplc.gcm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
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
        setContentView(R.layout.pickslip);
        Log.d(TAG, "INSIDE PICKSLIP ");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String pick = extras.getString(getString(R.string.pickDetailElement));

        try {
            JSONObject dets = new JSONObject(pick);
            //           ListView lv = new ListView(this);
            TableLayout ll = (TableLayout)findViewById(R.id.pickTable);
            for (int i = 0; i < dets.getJSONArray(getString(R.string.LinesElement)).length(); i++) {
                JSONObject line = dets.getJSONArray(getString(R.string.LinesElement)).getJSONObject(i);
                TableRow tableRow = new TableRow(this);
                TextView prodCode = new TextView(this);
                prodCode.setText(line.get(getString(R.string.productCodeElement)).toString());
                TextView description = new TextView(this);
                description.setText(line.get(getString(R.string.descriptonElement)).toString());
                TextView location = new TextView(this);
                location.setText(line.get(getString(R.string.locationElement)).toString());
                location.setGravity(Gravity.RIGHT);
                tableRow.addView(prodCode);
                tableRow.addView(description);
                tableRow.addView(location);
                ll.addView(tableRow);

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

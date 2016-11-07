package uk.co.tpplc.gcm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity to display details of pickslips
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
            TableLayout ll = (TableLayout) findViewById(R.id.pickTable);
            for (int i = 0; i < dets.getJSONArray(getString(R.string.LinesElement)).length(); i++) {
                JSONObject line = dets.getJSONArray(getString(R.string.LinesElement)).getJSONObject(i);
                final View row = View.inflate(this, R.layout.pickrow, null);
                final TextView prodCode = (TextView) row.findViewById(R.id.prod_code_col);
                prodCode.setText(line.get(getString(R.string.productCodeElement)).toString());
                final TextView description = (TextView) row.findViewById(R.id.description_col);
                description.setText(line.get(getString(R.string.descriptonElement)).toString());
                final TextView location = (TextView) row.findViewById(R.id.location_col);
                location.setText(line.get(getString(R.string.locationElement)).toString());
                ll.addView(row);

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
            throw new RuntimeException(e);
        }

    }
}

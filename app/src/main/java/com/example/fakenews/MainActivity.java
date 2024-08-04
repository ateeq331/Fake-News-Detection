package com.example.fakenews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrdu;
    private Button btnPredict;
    private TextView textViewPredictionResult;
    String url = "https://ml-engineer-fakenewsdetect.hf.space/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrdu = findViewById(R.id.editTextUrdu);
        btnPredict = findViewById(R.id.btnPredict);
        textViewPredictionResult = findViewById(R.id.textViewPredictionResult);

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urduText = editTextUrdu.getText().toString();
                if (!urduText.isEmpty()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                        textViewPredictionResult.setText(response);

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("news", urduText); // Assuming 'text' is the key expected by the server
                            return params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(stringRequest);

                } else {
                    Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

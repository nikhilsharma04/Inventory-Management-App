package com.example.saket.inventorymanagement;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;



/**
 * Created by saket on 12/11/16.
 */
public class DataBaseActivity extends AppCompatActivity {


    private static final int RC_BARCODE_CAPTURE = 9001;
    private ImageView barCode;
    private static final String TAG = "Hello";
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        barCode = (ImageView) findViewById(R.id.read_barcode);
        barCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataBaseActivity.this, BarCode.class));
            }
        });
    }
}
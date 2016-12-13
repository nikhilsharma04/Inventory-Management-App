package com.example.saket.inventorymanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by saket on 12/14/16.
 */
public class BarCode extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    public ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(0);
        //mScannerView.setFlash(true);
        //mScannerView.setAutoFocus(true);

    }


        @Override
        public void onPause() {
            super.onPause();
            mScannerView.stopCamera();
        }


        @Override
        public void handleResult(Result result) {



            Toast.makeText(BarCode.this , result.getContents() , Toast.LENGTH_LONG).show();
           // mScannerView.resumeCameraPreview(this);
            finish();

        }

}

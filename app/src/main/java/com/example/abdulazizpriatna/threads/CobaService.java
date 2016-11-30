package com.example.abdulazizpriatna.threads;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Abdul Aziz Priatna on 30/11/2016.
 */

public class CobaService extends IntentService{

    Handler mHandler; //untuk komunikasi dengan UI thread

    public CobaService() {
        super("cobaservice"); //untuk debug saja
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //ambil data dari intent
        String pesan;
        Bundle extras = intent.getExtras();
        if(extras == null) {
            pesan= null;
        } else {
            pesan=extras.getString("PESAN");
        }


        mHandler.post(new DisplayToast(this, "Service mulai.... Pesan="+pesan));


        //proses background ada disini
        for (int i = 0; i<50;i++) {
            try {
                Thread.sleep(50);  //simulasikan proses panjang
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mHandler.post(new DisplayToast(this, "Service selesai"));
    }
}

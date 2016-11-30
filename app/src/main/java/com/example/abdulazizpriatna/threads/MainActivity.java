package com.example.abdulazizpriatna.threads;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    TextView tvHasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHasil = (TextView) findViewById(R.id.textView);
        pb = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void klikMulai(View v) {
        //jangan lupa set onClick di activity_main.xml dengan method ini
        //button ditekan
        pb.setMax(100);
        new KerjakanBackgroundTask().execute(); //tidak ada parameter
    }

    private class KerjakanBackgroundTask extends AsyncTask<Void, Integer, String> {

        //sebelum proses
        protected void onPreExecute() {
            pb.setProgress(0);
            tvHasil.setText("Mulai");
        }

        @Override
        protected String doInBackground(Void... voids) {
            String hasil="-";

            try {
                for (int i = 0; i<100;i++) {
                    Thread.sleep(50);  //delay 0.05 detik biar lama
                    //update user interface dengan publichProgress
                    //JANGAN ada update user interface langsung
                    publishProgress(i);
                    if (isCancelled()) {
                        hasil = "batal";
                        break; //user bisa cancel ditengah task
                    }
                }
                hasil = "Sukses";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return hasil;  //result

        }

        //progress
        protected void onProgressUpdate(Integer... progress) {
            //Update user interface disini karena ini berjalan di UI thread
            //Integer... (tiga titik) artinya parameter bisa inteteger lebih dari satu
            //dan bentuknya array
            //jangan panggil onProgress langsung, gunakan publichProgress!
            pb.setProgress(progress[0]);
        }

        protected void onPostExecute(String result) {
            //selesai, bisa update user interface karena ini berjalan di UI thread
            tvHasil.setText(result);
        }
    }
}

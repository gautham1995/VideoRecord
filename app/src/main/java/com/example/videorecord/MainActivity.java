package com.example.videorecord;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int VIDEO_REQUEST = 101;
    private Uri uri = null;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.size);
    }

    public void captureVideo(View view) {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoIntent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 0);

        if (videoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(videoIntent,VIDEO_REQUEST);
        }
    }

    public void playVideo(View view) {

        Intent playIntent = new Intent(this, VideoPlayActivity.class);
        playIntent.putExtra("uri",uri.toString());
        startActivity(playIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            Cursor returnCursor = this.getContentResolver().query(Uri.parse(uri.toString()), null, null, null, null);
            assert returnCursor != null;
            int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            returnCursor.moveToFirst();
            String fileSize = returnCursor.getString(sizeIndex);
            double temp2 = Integer.parseInt(fileSize);
            double temp = (temp2/1000)/1000 ; // kb
            double temp3 = temp2/1000 ; //mb
//            System.out.println("This is the file size: " + temp + "kb");
//            Toast.makeText(MainActivity.this,fileSize,Toast.LENGTH_LONG).show();
            textView.setText(String.format("%.2f", temp)+" mb\n"+temp3+" kb");

        }

    }
}
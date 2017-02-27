package com.vgilanza.app.vigilanza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraDevice;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.vgilanza.app.vigilanza.R;

import java.io.IOException;
import java.io.File;
import java.util.jar.Manifest;

public class Capture extends AppCompatActivity {
    private static String outputFile=null;
    private Button startRecord=null,stopRecord=null,mPlay=null,mStop=null;
    private static final int VIDEO_CAPTURE = 101;
    Uri videoUri;

    private MediaRecorder mVideoRecorder;
    private CameraDevice camera;
    private MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        callMe();
    }
    public void callMe(){
        startRecord=(Button) findViewById(R.id.capture_startrec);
        stopRecord=(Button) findViewById(R.id.capture_stoprec);
        mPlay=(Button) findViewById(R.id.capture_playrec);
        mStop=(Button) findViewById(R.id.capture_stop_play);
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
            }
        });
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlay();
            }
        });
        stopRecord.setEnabled(false);
        mPlay.setEnabled(false);

    }
    public void start(){
        try{
            //camera.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
        }
        catch (Exception e){

        }
        Toast.makeText(getApplicationContext(),"Recording started.....",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoRecorder=null;
        //myAudioRecorder=null;
        startRecord.setEnabled(false);
        mPlay.setEnabled(true);
        Toast.makeText(getApplicationContext(),"Video Recorded Successfully ",Toast.LENGTH_LONG).show();
    }

    public void play(){
        try{
            playbackRecordedVideo();
            /*mPlayer=new MediaPlayer();
            outputFile=getExternalCacheDir().getAbsolutePath()+"/test.3gp";
            mPlayer.setDataSource(outputFile);
            mPlayer.prepare();
            mPlayer.start();*/
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),"Playing Video.....",Toast.LENGTH_LONG).show();
    }
    public void stopPlay(){
        try{
            mPlayer.stop();
            mPlayer.release();
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),"Stop Playing Audio.....",Toast.LENGTH_LONG).show();
    }

    public void startRecordVideo(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File mediaFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/myvideo.mp4");
            videoUri=Uri.fromFile(mediaFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,videoUri);
            startActivityForResult(intent,VIDEO_CAPTURE);
        }
        else {
            Toast.makeText(this,"No camera on device ",Toast.LENGTH_LONG).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==VIDEO_CAPTURE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"Video saved to "+data.getData(),Toast.LENGTH_LONG).show();
                playbackRecordedVideo();
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Video Recording cancelled",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Failed to record video",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void playbackRecordedVideo(){
        VideoView mVideoView=(VideoView) findViewById(R.id.capture_play_video);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}

package com.example.expressions;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.camerakit.CameraKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.microsoft.projectoxford.face.FaceServiceClient;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.Face;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Level2 extends AppCompatActivity {

    private String chosenEmotion;
    private ArrayList<Emotion> emotions = new ArrayList<>();
    private CameraKitView cameraKitView;
    // Add your Face endpoint to your environment variables.
    private final String apiEndpoint = "https://eastus.api.cognitive.microsoft.com/face/v1.0";
    // Add your Face subscription key to your environment variables.
    private final String subscriptionKey = "c09bae2d6b424719b205d93ec4e1e043";
    private final FaceServiceClient faceServiceClient =
            new FaceServiceRestClient(apiEndpoint, subscriptionKey);

    private ProgressDialog detectionProgressDialog;
    private ProgressBar progressBar;

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        cameraKitView = findViewById(R.id.camera2);
        progressBar = findViewById(R.id.gameProgressBar2);

        BottomNavigationView bnav = findViewById(R.id.bottom_nav_bar);
        bnav.setSelectedItemId(R.id.game);

        detectionProgressDialog = new ProgressDialog(this);

        bnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),
                                Statistics.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.music:
                        startActivity(new Intent(getApplicationContext(),
                                Playlist.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.game:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void Contempt(View view){
        chosenEmotion = "Contempt";
        progressBar.setVisibility(View.VISIBLE);
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @Override
            public void onImage(CameraKitView cameraKitView, final byte[] photo) {
                mBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                System.out.println("Saved image");
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                detectAndFrame(mBitmap);
            }
        }, 3000);
    }

    public void Disgust(View view){
        chosenEmotion = "Joy";
        progressBar.setVisibility(View.VISIBLE);
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @Override
            public void onImage(CameraKitView cameraKitView, final byte[] photo) {
                mBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                System.out.println("Saved image");
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                detectAndFrame(mBitmap);
            }
        }, 3000);
    }

    public void Fear(View view){
        chosenEmotion = "Joy";
        progressBar.setVisibility(View.VISIBLE);
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @Override
            public void onImage(CameraKitView cameraKitView, final byte[] photo) {
                mBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                System.out.println("Saved image");
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                detectAndFrame(mBitmap);
            }
        }, 3000);
    }

    public void Neutral(View view){
        chosenEmotion = "Joy";
        progressBar.setVisibility(View.VISIBLE);
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @Override
            public void onImage(CameraKitView cameraKitView, final byte[] photo) {
                mBitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                System.out.println("Saved image");
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                detectAndFrame(mBitmap);
            }
        }, 3000);
    }

    private void detectAndFrame(final Bitmap imageBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        progressBar.setVisibility(View.INVISIBLE);

        @SuppressLint("StaticFieldLeak") AsyncTask<InputStream, String, Face[]> detectTask =
                new AsyncTask<InputStream, String, Face[]>() {
                    String exceptionMessage = "";

                    @Override
                    protected Face[] doInBackground(InputStream... params) {
                        try {
                            publishProgress("Detecting...");
                            Face[] result = faceServiceClient.detect(
                                    params[0],
                                    true,         // returnFaceId
                                    false,        // returnFaceLandmarks
                                    new FaceServiceClient.FaceAttributeType[]{
                                            FaceServiceClient.FaceAttributeType.Emotion
                                    }          // returnFaceAttributes:
                                /* new FaceServiceClient.FaceAttributeType[] {
                                    FaceServiceClient.FaceAttributeType.Age,
                                    FaceServiceClient.FaceAttributeType.Gender }
                                */
                            );
                            if (result == null){
                                publishProgress(
                                        "Detection Finished. Nothing detected");
                                return null;
                            }
                            publishProgress(String.format(
                                    "Detection Finished. %d face(s) detected",
                                    result.length));
                            return result;
                        } catch (Exception e) {
                            exceptionMessage = String.format(
                                    "Detection failed: %s", e.getMessage());
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    protected void onPreExecute() {
                        //TODO: show progress dialog
                        detectionProgressDialog.show();
                    }
                    @Override
                    protected void onProgressUpdate(String... progress) {
                        //TODO: update progress
                        detectionProgressDialog.setMessage(progress[0]);
                    }
                    @Override
                    protected void onPostExecute(Face[] result) {
                        //TODO: update face frames
                        JSONArray jsonArray;
                        detectionProgressDialog.dismiss();

                        if(!exceptionMessage.equals("")){
                            showError(exceptionMessage);
                        }
                        if (result == null) return;

                        for(Face face: result){
                            emotions.add(new Emotion(face.faceAttributes.emotion.contempt, "Contempt"));
                            emotions.add(new Emotion(face.faceAttributes.emotion.disgust, "Disgust"));
                            emotions.add(new Emotion(face.faceAttributes.emotion.fear, "Fear"));
                            emotions.add(new Emotion(face.faceAttributes.emotion.neutral, "Neutral"));
                        }

                        Collections.sort(emotions, emotionComparator);
                        Collections.reverse(emotions);
                        System.out.println(emotions.get(0).getEmotion() + ": " + emotions.get(0).getProbability());
                        imageBitmap.recycle();
                        checkIfCorrect();
                    }
                };

        detectTask.execute(inputStream);
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }})
                .create().show();
    }

    public static Comparator<Emotion> emotionComparator = new Comparator<Emotion>() {
        @Override
        public int compare(Emotion o1, Emotion o2) {
            return Double.compare(o1.getProbability(), o2.getProbability());
        }
    };

    private void checkIfCorrect(){
        if(chosenEmotion.equals(emotions.get(0).getEmotion())){
            Intent intent = new Intent(this, Correct.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, Incorrect.class);
            intent.putExtra("Answer", emotions.get(0).getEmotion());
            startActivity(intent);
        }
    }
}

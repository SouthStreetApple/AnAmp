package com.example.android.anamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    /**
     * Song data variables
     */
    int numberOfSongs;
    int selectedSong;
    ArrayList<song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        /**
         * Load the variables from the previous screen
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Load the songs array!
            songs = extras.getParcelableArrayList("songs");
            //Gets which song we are working with
            selectedSong = extras.getInt("selectSong");
            //Sets the SeekBar Position
            SeekBar seekBar = findViewById(R.id.song_position);
            seekBar.setProgress(extras.getInt("songPosition"));
        }
        /**
         * Load song information into the various views.
         */
        //Song Name
        TextView songName = (TextView) findViewById(R.id.song_name);
        songName.setText(songs.get(selectedSong).songName);
        //Song Length
        TextView songLength = (TextView) findViewById(R.id.song_length);
        songLength.setText(songs.get(selectedSong).songLength);
        //Song Album
        TextView songAlbum = (TextView) findViewById(R.id.song_album);
        songAlbum.setText(songs.get(selectedSong).albumName);
        //Song Genre
        TextView songGenre = (TextView) findViewById(R.id.song_genre);
        songGenre.setText(songs.get(selectedSong).songGenre);
        //Song Album Image
        ImageView songAlbumImage = (ImageView) findViewById(R.id.album_image);
        songAlbumImage.setImageResource(songs.get(selectedSong).albumCover);


        /**
         * Set onClick listeners for buttons
         */
        findViewById(R.id.song_btn_backward).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_stop).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_pause).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_play).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_forward).setOnClickListener(appClickListener);
    }

    /**
     * Button onClick handlers, many thanks to Cubeactive Blog for the idea of using a case witch to handle all the button code in one place.
     * URL: http://blog.cubeactive.com/onclicklistener-android-tutorial/
     */
    //Global On click listener so we can handle button clicks
    View.OnClickListener appClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.song_btn_backward:
                    Toast.makeText(getApplicationContext(), "Previous Song", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.song_btn_stop:
                    Toast.makeText(getApplicationContext(), "Stop Song", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.song_btn_pause:
                    Toast.makeText(getApplicationContext(), "Pause Song", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.song_btn_play:
                    Toast.makeText(getApplicationContext(), "Play Song", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.song_btn_forward:
                    Toast.makeText(getApplicationContext(), "Next Song", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}

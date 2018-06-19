package com.example.android.anamp;

import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Song data variables
     */
    int numberOfSongs;
    int selectedSong;
    ArrayList<song> songs;

    /**
     * ListView variable
     */
    ListView songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Loads the ListView into memory
         */
        songList = findViewById(R.id.song_list);
        /**
         * Allows us to show the selected song as highlighted.
         * URL: https://stackoverflow.com/questions/5925892/how-to-highlight-row-in-listview-in-android
         */
        songList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        /**
         * Set OnItemClickListener
         * We grab the index of the ListItem that was clicked then use that to look up the song
         * element in the songs array.
         */
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Item is selected so we should do something?
                Toast.makeText(getApplicationContext(), songs.get(i).songName, Toast.LENGTH_SHORT).show();
                selectedSong = i;
            }
        });


        /**
         * Load song data
         */
        numberOfSongs = getResources().getInteger(R.integer.number_of_songs);
        songs = new ArrayList<song>();

        /**
         * Call function to load songs into memory
         */
        loadSongs();

        /**
         * Set onClick listeners for buttons
         */
        findViewById(R.id.song_btn_backward).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_stop).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_pause).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_play).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_forward).setOnClickListener(appClickListener);
        findViewById(R.id.song_btn_details).setOnClickListener(appClickListener);
    }

    private void loadSongs() {
        /**
         * Special Thanks to @Neural & @BigMikeDog from the Grow with Google Scholarship Slack.
         * They were instrumental in helping me debug my array usage and getIdentifier issues.
         *
         * This loads the songs into an array
         */
        int x;
        String songID;

        int songNameID;
        int albumNameID;
        int releaseYearID;
        int songGenreID;
        int songArtistID;
        int songLengthID;


        for (x = 1; x < (numberOfSongs + 1); x++) {
            song s = new song();
            /*Grab resource IDs for the x song*/
            songNameID = getResources().getIdentifier("song_" + (x) + "_name", "string", getPackageName());
            albumNameID = getResources().getIdentifier("song_" + (x) + "_album_name", "string", getPackageName());
            releaseYearID = getResources().getIdentifier("song_" + (x) + "_release_year", "string", getPackageName());
            songGenreID = getResources().getIdentifier("song_" + (x) + "_genre", "string", getPackageName());
            songArtistID = getResources().getIdentifier("song_" + (x) + "_artist", "string", getPackageName());
            songLengthID = getResources().getIdentifier("song_" + (x) + "_length", "string", getPackageName());
            /*Sets song information*/
            s.setSong(getString(songNameID), getString(albumNameID), getString(releaseYearID), getString(songGenreID), getString(songArtistID), getString(songLengthID), R.drawable.dmb_album);
            songs.add(s);
            Log.e("LOADING ARRAY", s.songName);
        }
        Log.e("TOTAL", String.valueOf(songs.size()));

        /**
         * This uses a custom adaptor songAdaptor to link the ListView tot he adaptor to display the songs in the ArrayList
         */
        songAdapter sa = new songAdapter(this, songs);
        songList.setAdapter(sa);
    }

    /**
     * Button onClick handlers, many thanks to Cubeactive Blog for the idea of using a case witch to handle all the button code in one place.
     * URL: http://blog.cubeactive.com/onclicklistener-android-tutorial/
     */
    //Global On click listener for all views
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
                case R.id.song_btn_details:
                    Toast.makeText(getApplicationContext(), "Song Details", Toast.LENGTH_SHORT).show();
                    //This is where we will use an intent to switch to the details screen to show more song information!
                    Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                    //Send data that we will need later...
                    i.putExtra("songs", songs);
                    i.putExtra("selectSong",selectedSong);
                    SeekBar sb = (SeekBar) findViewById(R.id.song_position);
                    i.putExtra("songPosition",sb.getProgress());
                    startActivity(i);
                    break;
            }
        }
    };
}

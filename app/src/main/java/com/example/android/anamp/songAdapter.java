package com.example.android.anamp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class songAdapter extends ArrayAdapter<song> {



    public songAdapter(Activity context, ArrayList<song> objects) {
        super(context,0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get current song
        song currentSong = getItem(position);

        //Check to see if the current song view is null, if it is create it
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        //Update the listItemView with data from the song.
        //Song Name
        TextView songName  = (TextView) listItemView.findViewById(R.id.song_name);
        songName.setText(currentSong.songName);
        //Length of song
        TextView songLength = (TextView) listItemView.findViewById(R.id.song_length);
        songLength.setText(currentSong.songLength);
        //Song Album
        TextView songAlbum = (TextView) listItemView.findViewById(R.id.song_album);
        songAlbum.setText(currentSong.albumName);
        //Song Genre
        TextView songGenre = (TextView) listItemView.findViewById(R.id.song_genre);
        songGenre.setText(currentSong.songGenre);


        //Return the listViewItem!
        return listItemView;
    }


}


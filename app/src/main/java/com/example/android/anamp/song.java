package com.example.android.anamp;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple little class that will allow me to create an array of songs that hols all the relevant data.
 * Utilized a plugin to make this Parceable
 * URL: http://corochann.com/fast-easy-parcelable-implementation-with-android-studio-parcelable-plugin-641.html
 */

public class song implements Parcelable {
    String songName;
    String albumName;
    String releaseYear;
    String songGenre;
    String songArtist;
    String songLength;
    int albumCover;


    /**
     * This function allows us to set all the values in one go.
     */
    public void setSong(String song_name,String album_name,String release_year,String song_genre,String song_Artist, String song_length,int album_cover) {
        songName = song_name;
        albumName = album_name;
        releaseYear = release_year;
        songGenre = song_genre;
        songArtist = song_Artist;
        songLength = song_length;
        albumCover = album_cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.songName);
        dest.writeString(this.albumName);
        dest.writeString(this.releaseYear);
        dest.writeString(this.songGenre);
        dest.writeString(this.songArtist);
        dest.writeString(this.songLength);
        dest.writeInt(this.albumCover);
    }

    public song() {
    }

    protected song(Parcel in) {
        this.songName = in.readString();
        this.albumName = in.readString();
        this.releaseYear = in.readString();
        this.songGenre = in.readString();
        this.songArtist = in.readString();
        this.songLength = in.readString();
        this.albumCover = in.readInt();
    }

    public static final Parcelable.Creator<song> CREATOR = new Parcelable.Creator<song>() {
        @Override
        public song createFromParcel(Parcel source) {
            return new song(source);
        }

        @Override
        public song[] newArray(int size) {
            return new song[size];
        }
    };
}

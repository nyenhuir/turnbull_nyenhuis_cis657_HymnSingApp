package cis657.project.hymnsingapp;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Event {
    String title,location,time,date,_eventkey;
    List<String> songs;

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String get_eventkey() {
        return _eventkey;
    }

    public void set_eventkey(String _eventkey) {
        this._eventkey = _eventkey;
    }
}

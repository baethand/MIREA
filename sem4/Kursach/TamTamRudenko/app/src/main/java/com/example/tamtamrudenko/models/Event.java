package com.example.tamtamrudenko.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Event implements Parcelable {
    private String id;
    private String name;
    private String description;
    private List<String> usersId;
    private String eventImageUrl;
    private Integer seats;

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Event() {
    }

    public Event(String id, String name, String description, List<String> usersId, String eventImageUrl, Integer seats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.usersId = usersId;
        this.eventImageUrl = eventImageUrl;
        this.seats = seats;
    }

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        usersId = in.createStringArrayList();
        eventImageUrl = in.readString();
        seats = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<String> usersId) {
        this.usersId = usersId;
    }

    public String getEventImageUrl() {
        return eventImageUrl;
    }

    public void setEventImageUrl(String eventImageUrl) {
        this.eventImageUrl = eventImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeStringList(usersId);
        parcel.writeString(eventImageUrl);
        parcel.writeInt(seats);
    }
}

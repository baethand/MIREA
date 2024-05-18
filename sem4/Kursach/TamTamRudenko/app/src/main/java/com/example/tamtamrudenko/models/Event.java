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

    // Конструктор
    public Event() {}

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        usersId = in.createStringArrayList();
        eventImageUrl = in.readString();
        seats = in.readByte() == 0 ? null : in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeStringList(usersId);
        dest.writeString(eventImageUrl);
        if (seats == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(seats);
        }
    }

    // Геттеры и сеттеры для полей
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getUsersId() { return usersId; }
    public void setUsersId(List<String> usersId) { this.usersId = usersId; }
    public String getEventImageUrl() { return eventImageUrl; }
    public void setEventImageUrl(String eventImageUrl) { this.eventImageUrl = eventImageUrl; }
    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }
}

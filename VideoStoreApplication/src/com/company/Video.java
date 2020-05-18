package com.company;

public class Video implements Comparable<Object>{

    private String title;
    private String id;

    public Video(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if ((o instanceof String))
            if(getTitle().equals((String)o))
                return true;
        return false;
    }


    @Override
    public int compareTo(Object video) {
        if(video instanceof Video)
            return getTitle().compareTo(((Video)video).getId());
        else if (video instanceof String)
            return getTitle().compareTo((String)video);
        return 2;
    }


    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}

package com.company;

public class Customer implements Comparable<Object> {
    private String name;
    private String id;
    private DS<Video> rentVideo;

    public Customer(String name, String id, String DSProtocol) {
        this.name = name;
        this.id = id;

        if (DSProtocol.equals("SLL"))
            rentVideo =(DS<Video>) new SLL<Video>();
        else if (DSProtocol.equals("DLL"))
            rentVideo =(DS<Video>) new DLL<Video>();
        else if (DSProtocol.equals("BST"))
            rentVideo =(DS<Video>) new BST<Video>();

        else if (DSProtocol.equals("AVL"))
            rentVideo =(DS<Video>) new AVL<Video>();
        else
            System.out.println("Illegal Data Structure! ");
    }

    public DS<Video> getRentVideo() {
        return rentVideo;
    }

    public void setRentVideo(DS<Video> rentVideo) {
        this.rentVideo = rentVideo;
    }

    public void printRentedVideos (){
        System.out.println("videos rented by "+getName()+" :");
        getRentVideo().print();
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if ((o instanceof String))
            if(getName().equals((String)o))
                return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object tCustomer) {
        if( tCustomer instanceof Customer)
            getName().compareTo(((Customer)tCustomer).getName());

        else if ( tCustomer instanceof String)
            return getName().compareTo((String)tCustomer);

        return 2;
    }



    @Override
    public String toString() {
        return "Customer = " +
                "name='" + name + '\'' +
                ", id=" + id ;
    }


}

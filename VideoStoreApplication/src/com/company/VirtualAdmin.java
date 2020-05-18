package com.company;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class VirtualAdmin {

    private int numOfCustomers;
    private int numOfVideos;
    private int numOfRequests;
    StoreManager store;
    ArrayDeque<ArrayList<String>> requestsQueu;

    public VirtualAdmin(int numOfCustomers, int numOfVideos, int numOfRequests, StoreManager store) {
        this.numOfCustomers = numOfCustomers;
        this.numOfVideos = numOfVideos;
        this.numOfRequests = numOfRequests;
        this.store=store;
        requestsQueu = new ArrayDeque<ArrayList<String>>();
    }


    public void generateCustomers(){
        for(int i=0;i<getNumOfCustomers();i++){
            getStore().addCustomer("C"+Integer.toString(i),Integer.toString(i));
        }
    }
    public void generateVideos(){
        for(int i=0;i<getNumOfVideos();i++){
            getStore().addVideo("V"+Integer.toString(i),Integer.toString(i));
        }
    }
    public void generateRequest(){
        Random r = new Random();
        for(int i=0;i<getNumOfRequests();i++) {
            ArrayList<String> requst = new ArrayList<String>();
            int randomRequest = r.nextInt(3) + 5;
            if (randomRequest == 5) {
                requst.add("5");
                requst.add("V"+Integer.toString(i));
            } else if (randomRequest == 6) {
                requst.add("6");
                requst.add("C"+Integer.toString(i));
                requst.add("V"+Integer.toString(i));
            } else if (randomRequest == 7) {
                requst.add("7");
                requst.add("C"+Integer.toString(i));
                requst.add("V"+Integer.toString(i));
            }
            getRequestsQueu().add(requst);
        }
    }

    public void processRequests(){
        while(!getRequestsQueu().isEmpty()){
            ArrayList<String> request = (ArrayList<String>) getRequestsQueu().poll();
            if(request.get(0).equals("5")){
                store.searchVideo(request.get(1));
            }
            else if(request.get(0).equals("6")){
                store.checkOut(request.get(1),request.get(2));
            }
            else if(request.get(0).equals("7")){
                store.checkIn(request.get(1),request.get(2));
            }
        }
    }



    public int getNumOfCustomers() {
        return numOfCustomers;
    }

    public int getNumOfVideos() {
        return numOfVideos;
    }

    public int getNumOfRequests() {
        return numOfRequests;
    }

    public StoreManager getStore() {
        return store;
    }

    public ArrayDeque<ArrayList<String>> getRequestsQueu() {
        return requestsQueu;
    }
}

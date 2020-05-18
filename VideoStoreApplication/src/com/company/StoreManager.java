package com.company;

public class StoreManager{
    private DS<Customer> customers;
    private DS<Video> videoInStore;
    private DS<Video> rentedVideos;
    private String DSProtocol;

    public StoreManager(String DSProtocol) {
        this.DSProtocol=DSProtocol;
        if (DSProtocol.equals("SLL")) {
            rentedVideos=(DS<Video>) new SLL<Video>();
            videoInStore=(DS<Video>) new SLL<Video>();
            customers=(DS<Customer>) new SLL<Customer>();
        }
        else if (DSProtocol.equals("DLL")) {
            rentedVideos=(DS<Video>) new DLL<Video>();
            videoInStore=(DS<Video>) new DLL<Video>();
            customers=(DS<Customer>) new DLL<Customer>();
        }
        else if (DSProtocol.equals("BST")) {
            rentedVideos=(DS<Video>) new BST<Video>();
            videoInStore=(DS<Video>) new BST<Video>();
            customers=(DS<Customer>) new BST<Customer>();
        }
        else if (DSProtocol.equals("AVL")) {
            rentedVideos=(DS<Video>) new AVL<Video>();
            videoInStore=(DS<Video>) new AVL<Video>();
            customers=(DS<Customer>) new AVL<Customer>();
        }
        else
            System.out.println("Illegal Data Structure! ");
    }

    public String getDSProtocol() {
        return DSProtocol;
    }

    public DS<Video> getRentedVideos() {
        return rentedVideos;
    }

    public DS<Customer> getCustomers() {
        return customers;
    }

    public void setCustomer(DS<Customer> customers) {
        this.customers = customers;
    }

    public DS<Video> getVideoInStore() {
        return videoInStore;
    }

    public void setVideoInStore(DS<Video> videoInStore) {
        this.videoInStore = videoInStore;
    }

    public void addVideo(String title,String id) {
        this.videoInStore.insert(new Video(title, id));
    }

    public void deleteVideo(String title) {

        getVideoInStore().delete(title);

    }

    public void deleteCustomer(String name){
        getCustomers().delete(name);
    }

    public void addCustomer(String name,String id){
        getCustomers().insert(new Customer(name,id,getDSProtocol()));
    }

    public boolean searchVideo(String title){
        return getVideoInStore().search(title);
    }

    public boolean checkOut(String customerName, String videoName){
        if (searchVideo(videoName)){
            Video video = findVideo(videoName);
            Customer customer= findCustomer(customerName);
            deleteVideo(videoName);
            getRentedVideos().insert(video);
            if(customer==null || video==null)
                return false;
            customer.getRentVideo().insert(video);
            return true;
        }
        else
            return false;
    }

    public boolean checkIn(String customerName, String videoName){
        Customer customer= findCustomer(customerName);
        if(customer!=null) {
            Video video = customer.getRentVideo().getByValue(videoName);
            if (video != null) {
                getVideoInStore().insert(video);
                getRentedVideos().delete(videoName);
                customer.getRentVideo().delete(videoName);
                return true;
            }
        }
        return false;
    }


    public Customer findCustomer(String customerName){
        return getCustomers().getByValue(customerName);
    }

    public Video findVideo(String videoTitle){
        return getVideoInStore().getByValue(videoTitle);
    }

    public void printAllCustomers(){
        getCustomers().print();
    }
    public void printAllVideos() {
        System.out.println("All videos:");
        getVideoInStore().print();
        getRentedVideos().print();
    }
    public void printRentedVideos() {
        System.out.println("The rented videos:");
        getRentedVideos().print();
    }
    public void printInStoreVideos (){
        System.out.println("videos in store:");
        getVideoInStore().print();
    }

    public void printVideosRentByACustomer(String customerName){
        Customer customer= findCustomer(customerName);
        if (customer==null)
            System.out.println("The customer was not found!");
        else
            customer.getRentVideo().print();
    }

}

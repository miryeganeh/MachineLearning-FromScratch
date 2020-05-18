package com.company;

import java.util.Scanner;

public class VideoStore {

    public static void printMenu(){
        String menu="\n\nSelect one of the followings: \n" +
                "1: To add a video\n" +
                "2: To delete a video\n" +
                "3: To add a customer\n" +
                "4: To delete a customer\n" +
                "5: To check if a particular video is in store \n" +
                "6: To check out a video\n" +
                "7: To check in a video\n" +
                "8: To print all customers\n" +
                "9: To print all videos\n" +
                "10: To print in store videos\n" +
                "11: To print all rent videos\n" +
                "12: To print the videos rent by a customer\n" +
                "13: To exit\n";
        System.out.print(menu);
    }

    public static int handleRequest(Scanner input,StoreManager storeManager){
        int selectedOption=input.nextInt();
        if(selectedOption==1){
            System.out.println("Enter video title to add:");
            input.nextLine();
            String title=input.nextLine();
            System.out.println("Enter video id:");
            String id=input.nextLine();
            storeManager.addVideo(title,id);
        }
        else if(selectedOption==2){
            System.out.println("Enter video title to delete:");
            input.nextLine();
            String title=input.nextLine();
            storeManager.deleteVideo(title);
        }
        if(selectedOption==3){
            System.out.println("Enter Customer Name to add:");
            input.nextLine();
            String name=input.nextLine();
            System.out.println("Enter Customer id:");
            String id=input.nextLine();
            storeManager.addCustomer(name,id);
        }
        else if(selectedOption==4){
            System.out.println("Enter Customer name to delete:");
            input.nextLine();
            String name=input.nextLine();
            storeManager.deleteCustomer(name);
        }
        else if (selectedOption==5){
            System.out.println("Enter video title to search for:");
            input.nextLine();
            String title=input.nextLine();
            System.out.println(storeManager.searchVideo(title));
        }

        else if (selectedOption==6){
            System.out.println("Enter video title to check out:");
            input.nextLine();
            String videoTitle=input.nextLine();
            System.out.println("Enter the name of the customer:");
            String customerName=input.nextLine();
            if (storeManager.checkOut(customerName,videoTitle))
                System.out.println("The checkout was successful! ");
            else
                System.out.println("The information entered was wrong! ");
        }

        else if (selectedOption==7){
            System.out.println("Enter video title to check in:");
            input.nextLine();
            String videoTitle=input.nextLine();
            System.out.println("Enter the name of the customer:");
            String customerName=input.nextLine();
            if (storeManager.checkIn(customerName,videoTitle))
                System.out.println("The check-in was successful! ");
            else
                System.out.println("The information entered was wrong! ");
        }

        else if (selectedOption==8){
            storeManager.printAllCustomers();
        }
        else if (selectedOption==9){
            storeManager.printAllVideos();
        }
        else if(selectedOption==10){
            storeManager.printInStoreVideos();
        }
        else if(selectedOption==11){
            storeManager.printRentedVideos();
        }
        else if(selectedOption==12) {
            System.out.println("Enter the name of the customer:");
            input.nextLine();
            String name = input.nextLine();
            System.out.println("The videos rented by the customer:");
            storeManager.printVideosRentByACustomer(name);
        }
        else if(selectedOption==13) {
            System.out.println("Goodbye :)");
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {


	    String DSProtocol=args[0];
        StoreManager storeManager=new StoreManager(DSProtocol);

        if (args.length>1){
            VirtualAdmin admin = new VirtualAdmin(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),storeManager);
            admin.generateCustomers();
            admin.generateVideos();
            admin.generateRequest();
            long t1 = System.currentTimeMillis();
            admin.processRequests();
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            System.out.println("The running time of the algorithm is " + t);
        }
        else {
            Scanner input = new Scanner(System.in);
            while (true) {
                printMenu();
                if (handleRequest(input, storeManager) == 0)
                    break;
            }
        }
    }
}

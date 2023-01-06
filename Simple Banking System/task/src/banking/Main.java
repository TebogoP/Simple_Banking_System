package banking;

import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;


public class Main {// class Account is Main
    private String PIN = null;
    private final String BIN  = "400000"; //Bank Identifier Number
    private String MII = String.valueOf(BIN.charAt(0)); //BIN/100000; //Major Industry Identifier
    private String AI = null; // Account Identifier
    private String checksumValue = null;  //check digit or checksum
    private HashMap<String,String>CustomerAccounts = new HashMap<>();
    private String card = null;
    private double balance = 0;
    Scanner sc = new Scanner(System.in);


    public Main(){
        card = "";
        PIN = "";
        AI = "";
    }
    public double getBalance(){
        return balance;   
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public String getPIN(){
        return PIN;   
    }
    public void setPIN(String PIN){
        this.PIN = PIN;
    }
    public String getCardNum(){
        return card;   
    }
    public void setCardNum(String card){
        this.card = card;
    }
    
    public String getAccountIdentifier(){
        return AI;   
    }
    public void setAccountIdentifier(String AI){
        this.AI = AI;
    }
    
    public String getCheckNum(){
        return checksumValue;
    }
    
    public static void setCheckNum(String checksumValue){
        this.checksumValue = checksumValue;
    }
    
    public void applyLuhnAlgorithm(String BIN, String AI) {
        String card15 = BIN + AI; 
        int sum = 0;
        long roundUp10 = 0L;
        char[] cardchar = card15.toCharArray();
        for (int  i = 0; i < cardchar.length ; i++) {
            int value = Character.getNumericValue(cardchar[i]);
            if (i % 2 == 0) {
                value *= 2;
                if (value > 9) {
                value += -9;
                }
            } 
            sum += value;
        } 
        roundUp10 = Math.round((sum + 10) / 10.0) * 10;
        setCheckNum(String.valueOf(roundUp10 - sum));
    }
    
    public boolean checkLength(String cardnum, int lengthValue){
        return cardnum.toCharArray().length == lengthValue ;
    }
    
    public String generateCardNumber() {
        boolean goodLength = false;
        String account = String.valueOf(generateNum(0,999_999_999));
        String cardNum = null;
        while (!checkLength(account, 9)) {
            account = "0"+ account.trim();
        }
        applyLuhnAlgorithm(BIN.trim(), account.trim());
        cardNum = BIN.trim() + account.trim() + getCheckNum().trim();
        while (!checkLength(cardNum, 16)) {
           cardNum = generateCardNumber ();
        }   
        return cardNum != null ? cardNum: "0";
    }
    public String getMIIString (int MII){
        String IndustryIdentifier ="";
        switch(MII){
            /*1 and 2 are issued by airlines
            3 is issued by travel and entertainment
            4 and 5 are issued by banking and financial institutions
            6 is issued by merchandising and banking
            7 is issued by petroleum companies
            8 is issued by telecommunications companies
            9 is issued by national assignment*/
            case 1:
                IndustryIdentifier = "airlines";
            break;
            case 2:
                IndustryIdentifier = "airlines";
            break;
            case 3:
                IndustryIdentifier = "travel and entertainment";
            break;
            case 4:
                IndustryIdentifier = "Credit Card";
            break;
            case 5:
                IndustryIdentifier = "banking and financial institutions";
            break;
            case 6:
                IndustryIdentifier = "merchandising and banking";
            break;
            case 7:
                IndustryIdentifier = "petroleum companies";
            break;
            case 8:
                IndustryIdentifier = "telecommunications companies";
            break;
            case 9:
                IndustryIdentifier = "national assignment";
            break;
        }
        return IndustryIdentifier;
        
    }
   // @Range(9999)
    public int generateNum(int min, int max){
        Random num = new Random();
        return num.nextInt(max - min + 1) + min;
        //PIN = pin.nextInt(10000);
    }
    public void createAccount(){
        String  card = generateCardNumber().trim();
        PIN = String.valueOf(generateNum(0000,9999));
        while (!checkLength(PIN, 4)) {
            PIN = "0"+ PIN;
        }

        System.out.println("Your card has been created");
        System.out.printf("Your card number:\n%s%nYour card PIN:\n%s\n",card,PIN);
        CustomerAccounts.put(card,PIN);
    }
    
    public void start(){
        boolean exit = false;
        boolean match = exit;
        int menu = 0;
        int askAns = 0;
        do{
            askQuestions();
            askAns = sc.nextInt();
            switch(askAns){
                case 1:
                    createAccount();
                break;
                case 2:
                    System.out.println("Enter your card number:");
                    String card = sc.next();
                    System.out.println("Enter your PIN:");
                    String pin = sc.next(); 
                    match = loginDetailsCheck(card.trim(), pin.trim());
                    if(!match){
                        System.out.println("Wrong card number or PIN!");
                        start();
                    }else{
                        System.out.println("You have successfully logged in!");
                        balanceMenu();
                        menu = sc.nextInt();
                        switch(menu){
                            case 0:
                            break;
                            case 1:
                                System.out.printf("Balance: %d%n",(int)getBalance());
                            break;
                            case 2:
                                System.out.println("You have successfully logged out!");
                            break;
                        }
                    }

                break;
                case 0:
                    exit = true;
                break;
            }
        }while(!exit);
        System.out.println("Bye");
    }
    public boolean loginDetailsCheck(String card, String pin){
        if(CustomerAccounts.containsKey(card)){
            return pin.equals(CustomerAccounts.get(card));
        }
        return false;
    }
    public void balanceMenu() {
        System.out.println("1. Balance\n2. Log out\n0. Exit");
    }
    public void askQuestions(){
        System.out.println("1. Create an account\n2. Log into account\n0. Exit");
    }
    //--------------------------------------Run Program--------------------------------------------
    public static void main(String[] args) {

        Database.createNewDatabase("test.db");
        Database.createNewTable("test.db");
        Main mUser = new Main();
        mUser.start();
        
      //  System.out.println("Hello, world!");
    }
    //---------------------------------------------------------------------------------------------
}
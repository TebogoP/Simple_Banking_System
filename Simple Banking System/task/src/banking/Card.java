package banking;

public class Card {
    private String PIN = null;

    public static void setCheckNum(String checksumValue){
        this.checksumValue = checksumValue;
    }
    public String getCheckNum(){
        return checksumValue;
    }
    public void setCardNum(String card){
        this.card = card;
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
}

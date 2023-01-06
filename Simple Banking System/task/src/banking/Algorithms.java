package banking;

public class Algorithms {
    protected static void applyLuhnAlgorithm(String BIN, String AI) {
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
        Card.setCheckNum(String.valueOf(roundUp10 - sum));
    }
}

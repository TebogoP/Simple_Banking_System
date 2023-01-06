import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // start coding here
        int leastHours = sc.nextInt();
        int maxHours = sc.nextInt();
        int annHours = sc.nextInt();
        System.out.println(leastHours > annHours ? "Deficiency" : maxHours < annHours ? "Excess" : "Normal");
    }
}

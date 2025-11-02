import java.util.Scanner;

public class DFAChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a binary string: ");
        String str = input.nextLine();
        input.close();

        // Starting state
        String state = "q0";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // String input
            if (ch != '0' && ch != '1') {
                System.out.println("Invalid input! Please enter only 0s and 1s.");
                return;
            }

            // Transition rules
            switch (state) {
                case "q0":
                    if (ch == '0')
                        state = "q1";
                    else
                        state = "q0";
                    break;

                case "q1":
                    if (ch == '0')
                        state = "q1";
                    else
                        state = "q2";
                    break;

                case "q2":
                    if (ch == '0')
                        state = "q1";
                    else
                        state = "q0";
                    break;
            }
        }

        // Accepting state: q2(string ending with 01)
        if (state.equals("q2"))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
    }
}
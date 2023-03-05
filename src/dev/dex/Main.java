package dev.dex;

import java.util.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String emailRegex = "^[a-zA-z0-9-_]+@gmail.com$";
        String phoneRegex = "^(\\+359|0)8[7-9][0-9]{7}$";
        String visaRegex = "^4[0-9]{12}([0-9]{3})?$";

        System.out.println("===Input Validator===");

        validateInput("Email", "Email should be Gmail.\n", emailRegex);
        validateInput("Phone", "Phone should be Bulgarian.\n", phoneRegex);
        validateInput("Visa", "", visaRegex);

        for (int i = 0; i < 30; i++) {
            System.out.print("=");
            sleep(100);
        }
        System.out.println("\nCompleted!");

    }

    private static void validateInput(String type, String message, String regex) {
        Scanner s = new Scanner(System.in);

        String input = null;
        int count = 0;
        Function<String, Boolean> f = (in) -> in.matches(regex);
        if (type == "Visa") {
            f = Main::checkVisa;
        }
        do {
            if (count > 0) {
                System.out.println("Wrong " + type + ".");
            }
            count++;
            System.out.print(message);
            System.out.print(type + ": ");
            input = s.nextLine();
            if (type != "Email") {
                input = input.replaceAll("\\s", "");
            }
            if (type == "Visa" && !input.matches(regex)) {
                System.out.println("Visa must be 16 or 19 numbers.");
                input = null;
            }
        } while (!f.apply(input));
    }

    private static boolean checkVisa(String visa) {
        if (visa == null) return false;
        long visaNumber = Long.parseLong(visa);
        int sumOfMultipliedDigits = 0;
        int sumOfDigits = 0;
        while (visaNumber > 0) {
            sumOfDigits += visaNumber%10;
            visaNumber /= 10;
            int curr = (int)(visaNumber%10) * 2;
            if (curr > 10) {
                curr = curr%10 + curr/10;
            }

            sumOfMultipliedDigits += curr;
            visaNumber/=10;
        }
        int sum = sumOfDigits+sumOfMultipliedDigits;
        if (sum%10 == 0) return true;
        return false;
    }
}

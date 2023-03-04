package dev.dex;

import java.util.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String emailRegex = "^.*@gmail.com$";
        String phoneRegex = "^(\\+359|0)8[7-9][0-9]{7}$";
        String visaRegex = "^4[0-9]{12}([0-9]{3})?$";

        System.out.println("===Input Validator===");

        Scanner s = new Scanner(System.in);

        String email = null;
        int count = 0;
        do {
            if (count > 0) {
                System.out.println("Wrong email");
            }
            count++;
            System.out.println("Email should be gmail");
            System.out.print("Email: ");
            email = s.nextLine();
        } while (!email.matches(emailRegex));

        String phone = null;
        int count2 = 0;
        do {
            if (count2 > 0) {
                System.out.println("Wrong phone");
            }
            count2++;
            System.out.println("Phone should be Bulgarian");
            System.out.print("Phone: ");
            phone = s.nextLine();
            phone = phone.replaceAll("\\s", "");
        } while (!phone.matches(phoneRegex));


        long visaNumber = 0;
        int count3 = 0;
        do {
            if (count3 > 0) {
                System.out.println("Wrong Visa");
            }
            count3++;
            System.out.print("Visa: ");
            String visa = s.nextLine();
            visa = visa.replaceAll("\\s", "");
            if (!visa.matches(visaRegex)) {
                System.out.println("Invalid number of digits");
                continue;
            }
            visaNumber = Long.parseLong(visa);

        } while (!checkVisa(visaNumber));

        for (int i = 0; i < 30; i++) {
            System.out.print("=");
            sleep(100);
        }
        System.out.println("\nCompleted!");

    }

    private static boolean checkVisa(long cardNumber) {
        int sumOfMultipliedDigits = 0;
        int sumOfDigits = 0;
        while (cardNumber > 0) {
            sumOfDigits += cardNumber%10;
            cardNumber /= 10;
            int curr = (int)(cardNumber%10) * 2;
            if (curr > 10) {
                curr = curr%10 + curr/10;
            }

            sumOfMultipliedDigits += curr;
            cardNumber/=10;
        }
        int sum = sumOfDigits+sumOfMultipliedDigits;
        if (sum%10 == 0) return true;
        return false;
    }
}

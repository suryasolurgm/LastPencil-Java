package lastpencil;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void printCharArray(char[] charArray){
        for (int i = 0; i < charArray.length; i++){
            if(charArray[i] == '|'){
                System.out.print(charArray[i]);
            }

        }
        System.out.println();
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencils;
        while (true) {
            try {
                pencils = Integer.parseInt(scanner.nextLine());
                if (pencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
        System.out.println("Who will be the first (John, Jack):");
        String first;
        while (true) {
            first = scanner.nextLine();
            if (!(first.equals("John") || first.equals("Jack"))) {
                System.out.println("Choose between 'John' and 'Jack'");
            } else {
                break;
            }
        }

        char[] pencilArray = new char[pencils];
        Arrays.fill(pencilArray, '|');
        System.out.println(pencilArray);
        //Game loop
        String currentPlayer = first;
        int end = pencilArray.length - 1;
        Random random = new Random();
        while (pencils != 0) {
            System.out.println(currentPlayer + "'s turn");

            // Input: Number of pencils to remove
            int removePencils;
            while (true) {
                if(currentPlayer.equals("Jack")){
                    if (end == 0) {
                        removePencils = 1;
                    } else {
                        int remainder = (end + 1) % 4;
                        if (remainder == 1) {
                            removePencils = random.nextInt(3) + 1;
                        } else {
                            if(remainder==3){
                                removePencils=2;
                            } else if (remainder==2) {
                                removePencils=1;
                            }else{
                                removePencils=3;
                            }

                        }
                    }
                    System.out.println(removePencils);
                    break;
                }else {
                    try {
                        removePencils = Integer.parseInt(scanner.nextLine());
                        if (removePencils < 1 || removePencils > 3) {
                            System.out.println("Possible values: '1', '2' or '3'");
                        } else if (end - removePencils + 1 < 0) {
                            System.out.println("Too many pencils were taken");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }
                }
            }

            // Update pencil array
            for (int i = 0; i < removePencils; i++) {
                pencilArray[end] = ' ';
                end--;
                pencils--;
            }
            if(pencils != 0){
                printCharArray(pencilArray);
            }


            // Switch players
            currentPlayer = (currentPlayer.equals("John")) ? "Jack" : "John";
        }
        // Print winner
        System.out.println(currentPlayer + " won!");

    }
}

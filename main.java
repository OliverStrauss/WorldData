package WorldData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;




public class main {
    public static void displayPops(int [] pops){
        int [] dates = {1970,1980,1990,2000,2010,2015,2020,2022,2023};
        for(int i =0; i < pops.length; i++){
            System.out.println(dates[i] + ":" + formatWithCommas(pops[i]));
        }
    }
    public  static ArrayList<country> makeWorld(){
        // Creating list of "country" objects
        ArrayList<country> world = new ArrayList<>();

        String filePath = "WorldData/world_population_data.csv"; // Replace with the actual path to your text file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] items = line.split(",");
                int [] pops = new int[8];
                for(int i = 0; i < 8;i++) {
                    try {
                        pops[i] = Integer.parseInt(items[items.length - 5 - i]);
                    }
                    catch (NumberFormatException e){
                        i++;
                    }

                    //displayPops(items);
                }

                System.out.println();
                int id = 1;
                 id += Integer.parseInt(items[0]);

                String popPercent = (items[items.length-1].replace("%",""));
                double dub = Double.parseDouble(popPercent);


                country c = new country(id, items[2], items[3], pops,dub);
                world.add(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return world;


    }
    public static int getPops() {
        Scanner scanner = new Scanner(System.in);
        int userInput;


        System.out.print("Would you like to view the population over time? [1]Yes, [2]No");
        while (!scanner.hasNextInt()) {
            // Handle non-integer input
            scanner.nextInt(); // Consume the invalid input
        }
        userInput = scanner.nextInt();

        // Close the scanner

        return userInput;
    }
    public static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        int userInput;


        System.out.print("Welcome to the world population databse would you like to search by [1]Population Rank, [2]Country Name or [3]quit: ");
            while (!scanner.hasNextInt()) {
                // Handle non-integer input
                scanner.nextInt(); // Consume the invalid input
            }
            userInput = scanner.nextInt();

        // Close the scanner

        return userInput;
    }
    public static int getUserInputInRange() {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        do {
            System.out.print("Enter an integer in the range [1, 234] to find a country: ");
            while (!scanner.hasNextInt()) {
                // Handle non-integer input
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
            userInput = scanner.nextInt();

            if (userInput < 0 || userInput > 234) {
                System.out.println("Invalid input. Number must be in the range [0, 234].");
            }

        } while (userInput < 0 || userInput > 234);

        // Close the scanner

        return userInput;
    }
    public static void displayCountry(int userInput,country c,boolean flag){
        if(flag){
            System.out.println(("Ahh yes " + c.getName()) + " is the number " + userInput +" ranked country for its population size");
        }
        else {
            System.out.println("You entered: " + userInput);
            System.out.println("The country thats number " + userInput + " for population size is " + c.getName());
        }
        System.out.println(c.getName()+ " is in the great continent of " + c.getContinent() + " with an average population of " + formatWithCommas(c.getAvg(c.getPops()))+ " which makes up " + c.getPopPercent() +" percent of the world");


    }

    public static String formatWithCommas(long number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }


    public static void main(String[] args) {
        ArrayList<country> world = makeWorld();
        boolean running = true;
        while (running) {
            int search = getUserChoice();
            if (search == 1) {
                int userInput = getUserInputInRange();
                // user input is index on "world" list of countrys
                country c = (world.get(userInput-1));
                // accesing country object
                displayCountry(userInput,c,false);
                int uChoice = getPops();
                if(uChoice == 1){
                    displayPops(c.getPops());
                }





            }
            if(search ==2){
                Scanner scanner = new Scanner(System.in);


                boolean found = false;
                boolean quit = false;
                country c = world.get(0);
                int count =0;

                while (!found){
                    String message = "quit";

                    System.out.print("Please enter country name or type \"" + message + "\" quit to exit to main menu:");
                    String choice = scanner.nextLine();
                    if (choice.equals("quit")){
                        quit = true;
                        break;
                    }
                    count =0;
                    for (country cu : world) {
                        if(cu.getName().equals(choice)) {
                            found = true;
                            c = cu;
                            break;
                        }
                        count++;
                    }
                    if (!found) {
                        System.out.println("Country not found");
                    }



                }
                if(!quit) {
                    displayCountry(count, c, true);
                    int uChoice = getPops();
                    if(uChoice == 1){
                        displayPops(c.getPops());
                    }
                }

            }
            if(search ==3 ){
                running = false;


            }
        }











    }
}
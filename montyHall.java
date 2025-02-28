import java.util.Scanner;
import java.util.Random;


public class montyHall {
    public static void main(String[] args){ //main code goes in special main class 
        Scanner scan = new Scanner(System.in); //Allows you to use scan.nextLine() to get an input
        System.out.println("Type game for game and sim for simulation: "); //print...
        String input = scan.nextLine(); //input
        if (input.equals("game")) { //input == "game"
            game(); //run function game
        }
        else if (input.equals("sim")) {
            simulation(); //run function simulation
        }
        scan.close(); //have to close the scanner
    }


    public static void game(){ //the actual game 
        System.out.println(" ");
        Scanner scan = new Scanner(System.in);
        System.out.println("3 doord will be presented, you shall choose a door, 1 2 or 3.\n2 have goats behind and 1 has a super car behind. \nThe program will reveal one of the doors with a goat behind, you will be asked to stay or switch");
        System.out.println(" "); //makes the text clearer when outputted (space inbetween)
        System.out.println("Input your choice of door, 1 2 or 3");
        int input = scan.nextInt();
        input = input -1; //change it to an index of an array as they start at 0
        System.out.println(" ");
        int[] doorInfo = new int[3]; //the array containing what is behind each door (1 is supercar and 0 is goat)
        Random rand = new Random(); //allows me to use rand.nextInt()
        int superCarPos = rand.nextInt(3); //gets a random mumber between 0 and 2 for the super car position

        doorInfo[superCarPos] =1; //super car = 1 goat = 0
        //door info looks like this e.g [1,0,0] = [car, goat, goat]
        int openedDoor = 0;
        if (doorInfo[input] == 0) { //if you chose a goat, you find the other goat
            for (int i = 0; i < doorInfo.length; i++) { //for int i is 0, when i is < the length of doorInfo array, i++ is i = i +1 so incrememts
                if (doorInfo[i] == 0 && i != input) {
                    System.out.println("A goat is behind door number " + (i+1));
                    openedDoor = i;
                }
            }
        }
        else if (doorInfo[input] == 1) { //if you chsoe the super car then you need to randomly choose a goat to reveal
            int randomGoat = rand.nextInt(2);
            if (randomGoat == 0) {
                boolean found = false;
                for (int i = 0; i < doorInfo.length; i++) {
                    if (doorInfo[i] == 0 && found == false) {
                        System.out.println("A goat is behind door number " + (i+1));
                        openedDoor = i;
                        found = true;
                    }
                }
            }
            else if (randomGoat == 1) {
                boolean flag = false; //not found first goat
                for (int i = 0; i < doorInfo.length; i++) {
                    if (doorInfo[i]==0) {
                        if (flag == true) {
                            System.out.println("A goat is behind door number " + (i+1));
                            openedDoor = i;

                        }
                        flag = true;
                    }
                }
            }
        }
        scan.nextLine();  
        System.out.println("would you like to switch yes or no? ");
        String userSwitch = scan.nextLine(); //does the user want to switch
        if (userSwitch.toLowerCase() == "yes") { 
            for (int i = 0; i < doorInfo.length; i++) {
                if (i != openedDoor && i != input) { //switches to the unchosen and unopened door
                    input = i;
                }
            }
        }

        int superCarLocation = 0;
        for (int i = 0; i < doorInfo.length; i++) { //gives a list of whats behind each door
            if (doorInfo[i] == 1) {
                System.out.println("Behind door number "+ (i+1) + " is a super car");
                superCarLocation = i;
            }
            else if (doorInfo[i] == 0) {
                System.out.println("Behind door number "+ (i+1) +" is a goat");
            }
        }
        System.out.println(" ");
        System.out.println(" "); //add a little space between to make clear

        if (input == superCarLocation) { //final message
            System.out.println("Congrats you won a super car!!! ");
        }
        else{
            System.out.println("You got a goat");
        }

        scan.close();


    }

    public static void simulation(){ //runs the simulation
        Scanner scan = new Scanner(System.in);
        System.out.println("Set the simulation to Switch, true or false");
        Boolean switchPos = scan.nextBoolean(); //asking for true or false
        scan.nextLine();
        System.out.println("Alright how many simulations would you like to run: ");
        long numOfSims = scan.nextLong(); //a long is a big number because i want a lot of simualtions
        scan.close();
        int acc = 0; //accumulator
        for (int i = 1; i <= numOfSims; i++) { //actual loop calling the simulations
            acc += gameForSim(switchPos); //gameForSim returns 1 if you won and 0 if you lost
        }
        double percentage = (((double) acc)/numOfSims)*100; //getting the percentage
        System.out.println("Pecentage of sims won: " + percentage + "%.");
    }

    //special comment that can automatically create a webpage with the info thats there  (javadocs)
    /**
     * gameForSim
     * @param switchPos //does the user want to run the simulation when we switch every time or not switch
     * @return 1 or 0 depending on if you won or lost
     */
    public static int gameForSim(Boolean switchPos){ //does each round of simulation
        int input = 0;
        int[] doorInfo = new int[3];
        Random rand = new Random();
        int superCarPos = rand.nextInt(3);

        doorInfo[superCarPos] =1; //super car = 1 goat = 0
        //door info looks like this e.g [1,0,0] [car, goat, goat]
        int openedDoor = 0;
        if (doorInfo[input] == 0) {
            for (int i = 0; i < doorInfo.length; i++) {
                if (doorInfo[i] == 0 && i != input) {
                    openedDoor = i;
                }
            }
        }
        else if (doorInfo[input] == 1) {
            int randomGoat = rand.nextInt(2); //looking for the first goat in the array
            if (randomGoat == 0) {
                boolean found = false;
                for (int i = 0; i < doorInfo.length; i++) {
                    if (doorInfo[i] == 0 && found == false) {
                        openedDoor = i;
                        found = true;                            
                    }
                }
            }
            else if (randomGoat == 1) { //looking for the second goat in the array
                boolean flag = false; //not found first goat
                for (int i = 0; i < doorInfo.length; i++) {
                    if (doorInfo[i]==0) {
                        if (flag == true) {
                            openedDoor = i;
                        }
                        flag = true;
                    }
                }
            }
        }
        Boolean userSwitch = switchPos;
        if (userSwitch == true) { //switches door based on parameter
            for (int i = 0; i < doorInfo.length; i++) {
                if (i != openedDoor && i != input) {
                    input = i;
                }
            }
        }

        int superCarLocation = 0;
        for (int i = 0; i < doorInfo.length; i++) {
            if (doorInfo[i] == 1) {
                superCarLocation = i;
            }
        }
        if (input == superCarLocation) {
            return 1;
        }
        else{
            return 0;
        }
    }
}

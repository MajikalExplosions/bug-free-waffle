import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PlantainPeel {
    static int ARG_COUNT = 4;
    public static void main(String[] args) {
    	System.out.println("Starting genetic algorithm...");
        Random rng = new Random();
        int[] arguments;
        try {
            File f = new File("kykatro.bacond");
            if (f.exists()) {
                arguments = getArgs();
            }
            else {
                arguments = new int[ARG_COUNT];
                arguments[0] = randomInt(rng, 75, 125);
                arguments[1] = randomInt(rng, 100, 250);
                arguments[2] = randomInt(rng, 300, 750);
                arguments[3] = randomInt(rng, 50, 500);
                setArgs(arguments);
            }
            for (int i = 0; i < 100; i++) {
                runGame(getArgs());
                //Parse game output and change args
            }
        }
        catch(Exception e) {
        	System.out.println("Error 102");
            System.exit(102);
        }
    }

    public static void runGame(int[] args) {
    	try {
            String arguments = "";
            for (int i : args) {
            	arguments += " " + i;
            }

            System.out.println("Arguments set.");

            String bashFileContents = "set -e\n" +
            "javac MyBot.java\n" +
            "./halite --replay-directory replays/ -vvv --width 32 --height 32 \"java MyBot" + arguments + "\" \"java MyBot" + arguments + "\"";
            
            BufferedWriter bashFile = new BufferedWriter(new FileWriter("plantain.sh"));
            bashFile.write(bashFileContents);
            bashFile.close();

            System.out.println("Batch file written.  Running...");
            
            Process p = Runtime.getRuntime().exec("sh plantain.sh");
            
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String s;
            System.out.println("Waiting for current game to end...");
            while ((s = stdInput.readLine()) != null) System.out.println(s);
            System.out.println("Game ended.");
        }
        catch (IOException e) { System.exit(100); };
    }
    
    private static int[] getArgs() {
        try {
            File file = new File("kykatro.bacond"); 
            
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            
            String st;
            int[] args = new int[ARG_COUNT];
            int x = 0;
            while ((st = br.readLine()) != null)
                args[x++] = Integer.parseInt(st);
            br.close();
            return args;
        }
        catch (IOException e) {
        	System.out.println("Error 103");
            System.exit(103);
        }
        System.out.println("Error 105");
        System.exit(105);
        return new int[0];
    }
    
    private static void setArgs(int[] args) {
        try {
            String c = "";
            for (int i = 0; i < ARG_COUNT; i++) {
                c += args[i];
                c += "\n";
            }
            BufferedWriter argsFile = new BufferedWriter(new FileWriter("kykatro.bacond"));
            argsFile.write(c);
            argsFile.close();
        }
        catch(Exception e) {
        	System.out.println("Error 104");
            System.exit(104);
        }
    }
    
    private static int randomInt(Random r, int min, int max) {
        return r.nextInt(max - min) + min;
    }
}
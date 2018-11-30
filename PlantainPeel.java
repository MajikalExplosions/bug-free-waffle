import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlantainPeel {
    
    public static void main(String[] args) {
    	System.out.println("Starting bot...");
    	runGame(new int[0]);
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
            
            while ((s = stdInput.readLine()) != null) continue;
        }
        catch (IOException e) System.exit(100);
    }

}

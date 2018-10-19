import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
    private FileWriter file;

    private static Log INSTANCE;
    private static ArrayList<String> LOG_BUFFER = new ArrayList<>();

    static {
        Runtime.getRuntime().addShutdownHook(new AtExit());
    }

    private static class AtExit extends Thread {
        @Override
        public void run() {
            if (INSTANCE != null) {
                return;
            }

            long now_in_nanos = System.nanoTime();
            String filename = "bot-unknown-" + now_in_nanos + ".log";
            try (FileWriter writer = new FileWriter(filename)) {
                for (String message : LOG_BUFFER) {
                    writer.append(message).append('\n');
                }
            } catch (IOException e) {
                // Nothing much we can do here.
            }
        }
    }

    private Log(FileWriter f) {
        file = f;
    }

    static void open(int botId) {
        if (INSTANCE != null) {
            Log.log("Error: log: tried to open(" + botId + ") but we have already opened before.");
            throw new IllegalStateException();
        }

        String filename = "bot-" + botId + ".log";
        FileWriter writer;
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        INSTANCE = new Log(writer);

        try {
            for (String message : LOG_BUFFER) {
                writer.append(message).append('\n');
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        LOG_BUFFER.clear();
    }

    public static void log(String message) {
        if (INSTANCE == null) {
            LOG_BUFFER.add(message);
            return;
        }

        try {
            INSTANCE.file.append(message).append('\n').flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;

public class ReaderThread implements Runnable {
    private final String inputFile;
    private final PipedOutputStream out;

    public ReaderThread(String inputFile, PipedOutputStream out) {
        this.inputFile = inputFile;
        this.out = out;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                writer.flush(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { out.close(); } catch (IOException ignored) {}
        }
    }
}

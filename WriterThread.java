import java.io.*;

public class WriterThread implements Runnable {
    private final String outputFile;
    private final PipedInputStream in;

    public WriterThread(String outputFile, PipedInputStream in) {
        this.outputFile = outputFile;
        this.in = in;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

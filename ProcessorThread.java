import java.io.*;

public class ProcessorThread implements Runnable {
    private final PipedInputStream in;
    private final PipedOutputStream out;

    public ProcessorThread(PipedInputStream in, PipedOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processed = line.toUpperCase();

                writer.write(processed);
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

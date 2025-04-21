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
                // If the line is in lowercase, convert to uppercase
                if (line.equals(line.toLowerCase())) {
                    line = line.toUpperCase();
                } 
                // If the line is in uppercase, convert to lowercase
                else if (line.equals(line.toUpperCase())) {
                    line = line.toLowerCase();
                }
                if (line.toLowerCase().contains("java")) {
                    line = line.replaceAll("(?i)java", "JAVA PRO"); 
                }
    
                // Write the processed line
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

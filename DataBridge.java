import java.io.*;

public class DataBridge {
    public static void main(String[] args) throws IOException {
        PipedOutputStream readerToProcessorOut = new PipedOutputStream();
        PipedInputStream readerToProcessorIn = new PipedInputStream(readerToProcessorOut);

        PipedOutputStream processorToWriterOut = new PipedOutputStream();
        PipedInputStream processorToWriterIn = new PipedInputStream(processorToWriterOut);

        Thread reader = new Thread(new ReaderThread("input.txt", readerToProcessorOut));
        Thread processor = new Thread(new ProcessorThread(readerToProcessorIn, processorToWriterOut));
        Thread writer = new Thread(new WriterThread("output.txt", processorToWriterIn));

        reader.start();
        processor.start();
        writer.start();
    }
}



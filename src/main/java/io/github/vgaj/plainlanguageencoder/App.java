package io.github.vgaj.plainlanguageencoder;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

/***
 * A class to invoke the encryption and decryption logic from the command line.
 * This is the main class in the jar.
 */
public class App {
    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        boolean isEncode = (args.length > 0 && args[0].startsWith("e"));
        boolean isDecode = (args.length > 0 && args[0].startsWith("d"));
        if (args.length == 3 && (isEncode || isDecode)) {
            if (isEncode) {
                try (InputStream is = new BufferedInputStream( new FileInputStream( args[1]));
                     OutputStream os = new LanguageEncodedOutputStream( new BufferedOutputStream( new FileOutputStream( args[2])))) {
                    int inputData;
                    while ((inputData = is.read()) != -1) {
                        os.write(inputData);
                    }
                    System.out.println(String.format("Encoding completed in %d seconds", Duration.between(start,Instant.now()).getSeconds()));
                }
            } else if (isDecode) {
                try (InputStream is = new LanguageEncodedInputStream( new BufferedInputStream( new FileInputStream( args[1])));
                     OutputStream os = new BufferedOutputStream( new FileOutputStream( args[2]))) {
                    int inputData;
                    while ((inputData = is.read()) != -1) {
                        os.write(inputData);
                    }
                    System.out.println(String.format("Decoding completed in %d seconds", Duration.between(start,Instant.now()).getSeconds()));
                }
            }

        } else {
            System.out.println("Usage: java -jar xxx.jar encrypt <original_file> <encrypted_file>" + System.lineSeparator() +
                               "       java -jar xxx.jar decrypt <encrypted_file> <decrypt_file>");
        }
    }}

package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Tools {

    public static Supplier<Stream<String>> getInputSupplier(String path) {
        return () -> {
            try {
                return new BufferedReader(new FileReader(new File(path))).lines();
            } catch (FileNotFoundException ignored) {}
            return null;
        };
    }
}

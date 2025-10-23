package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        List<String> strings;
        int buy = 0;
        int supply = 0;

        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String string : strings) {
            String[] split = string.split(",");

            if (split[0].charAt(0) == 'b') {
                buy += Integer.parseInt(split[1]);
            }
            if (split[0].charAt(0) == 's') {
                supply += Integer.parseInt(split[1]);
            }
        }

        String[] result = {"supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy)};

        File file1 = new File(toFileName);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String string : result) {
            try {
                Files.write(file1.toPath(), string.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

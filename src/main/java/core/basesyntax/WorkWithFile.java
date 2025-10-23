package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;

        List<String> strings = readDataFromFile(fromFileName);

        for (String string : strings) {
            String[] split = string.split(",");

            if (split[0].charAt(0) == 'b') {
                buy += Integer.parseInt(split[1]);
            }
            if (split[0].charAt(0) == 's') {
                supply += Integer.parseInt(split[1]);
            }
        }
        StringBuilder report = createReport(buy, supply);

        writeDataToFile(toFileName, report);
    }

    public StringBuilder createReport(int buy, int supply) {
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
    }

    public void writeDataToFile(String toFileName, StringBuilder stringBuilder) {
        File file = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException("Can't write" + toFileName, e);
        }
    }

    public List<String> readDataFromFile(String fromFileName) {
        List<String> strings;

        try {
            strings = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName,e);
        }

        return strings;
    }
}

package de.neasy.task.util;

import java.io.PrintWriter;
import java.util.List;
import java.util.Arrays;
import de.neasy.task.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class WriteDataToCSV {

    public static void writeObjectToCSV(PrintWriter writer, List<User> users) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Id", "FirstName", "LastName", "Address", "Email", "Date"));
        ) {
            for (User user : users) {
                long id=user.getId();
                String s=Long.toString(id);
                List<String> data = Arrays.asList(
                        s,
                        //user.getId().toString(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAddress(),
                        user.getEmail(),
                        user.getDate()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }


}


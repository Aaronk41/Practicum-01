import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personTextData.txt");

        boolean done = false;

        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits] ");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the first name ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the last name ");
            title = SafeInput.getNonZeroLenString(in, "Enter the title ");
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth ", 1000, 9999);

            personRec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            folks.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        for (String p : folks)
            System.out.println(p);

        try {
            // Create parent directories if they don't exist
            Files.createDirectories(file.getParent());

            // Open the file with CREATE and APPEND options
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE, StandardOpenOption.APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : folks) {
                writer.write(rec);  // No need to specify start and length
                writer.newLine();   // Use newLine() to add a newline

            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            java.io.File selectedFile = fileChooser.getSelectedFile();

            try {
                // Open the selected file for reading
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                // Display column headers
                System.out.println("ID#           Firstname     Lastname       Title    YOB");
                System.out.println("=======================================");

                // Read and display each line from the file
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (parts.length == 5) {
                        String formattedLine = String.format("%-15s %-15s %-15s %-8s %s",
                                parts[0], parts[1], parts[2], parts[3], parts[4]);
                        System.out.println(formattedLine);
                    }
                    else {
                        System.out.println("Invalid line: " + line);
                    }
                }

                // Close the file
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}



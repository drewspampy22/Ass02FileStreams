import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class ProductGenerator {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);

        boolean done = false;
        String ID = "";
        String name = "";
        String desc = "";
        double cost = 0;
        ArrayList<Product> ProductList = new ArrayList<>();

        do {

            ID = SafeInput.getNonZeroLenString(pipe, "ID? ");
            name = SafeInput.getNonZeroLenString(pipe, "Product Name?  ");
            desc = SafeInput.getNonZeroLenString(pipe, "Description of the Product ");
            cost = SafeInput.getDouble(pipe, "What is the cost? ");


            Product productin = new Product(ID,name,desc,cost);
            ProductList.add(productin);
            done = SafeInput.getYNConfirm(pipe, "Are you finished?: ");

        }
        while (!done);


        File workDir = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workDir.getPath() + "\\src\\ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(Product i:ProductList ) {
                writer.write(i.toCSVDataRecord());
                writer.newLine();
            }
            writer.close();
            System.out.println("data file written");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package valiant;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String separator = File.separator;
        String pathInputFile = System.getProperty("user.dir") + separator + "cdr.txt";
        File inputFile = new File(pathInputFile);
        Scanner scanner = new Scanner(inputFile);


        Map<String, Subscriber> subscribers = new HashMap<>();
        while(scanner.hasNextLine()) {
            String lineData = scanner.nextLine();
            Subscriber sub = new Subscriber(lineData);
            if(subscribers.containsKey(sub.getNumber())) {
                subscribers.get(sub.getNumber()).addCall(lineData);
            } else {
                subscribers.put(sub.getNumber(), sub);
            }
        }


        for(String key: subscribers.keySet()) {
            String pathOutputFile = System.getProperty("user.dir") + separator + "reports" + separator +
                    key + ".txt";
            File outputFile = new File(pathOutputFile);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            PrintWriter printWriter = new PrintWriter(outputFile);
            printWriter.printf("Tariff index: %s\n", subscribers.get(key).getTariffIndex());
            printWriter.println("----------------------------------------------------------------------------");
            printWriter.printf("Report for phone number %s:\n", subscribers.get(key).getNumber());
            printWriter.println("----------------------------------------------------------------------------");
            printWriter.println("| Call Type |   Start Time        |     End Time        | Duration | Cost  |");
            printWriter.println("----------------------------------------------------------------------------");

            List<Call> cs = subscribers.get(key).getCalls();
            for (Call cl : cs) {
                printWriter.printf("|     %s    | %s | %s | %s |  %.2f |\n",
                        cl.getType(), cl.getStartAt(), cl.getEndAt(), cl.getDurationCall(), cl.getCostCall());
            }

            printWriter.println("----------------------------------------------------------------------------");
            printWriter.printf("|                                           Total Cost: |     %.2f rubles |\n",
                    subscribers.get(key).getTotalCost());
            printWriter.println("----------------------------------------------------------------------------");
            printWriter.close();
        }
        scanner.close();
    }
}
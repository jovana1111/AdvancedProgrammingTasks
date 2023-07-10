import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

class OnlinePayments {
    Map<String, List<Item>> payments;

    public OnlinePayments() {
        payments = new HashMap<>();
    }

    public void readItems(InputStream in) {
        Scanner scanner = new Scanner(in);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String [] parts = line.split(";");
            List<Item> itemList = new ArrayList<>();
            Item item = new Item(parts[1], Integer.parseInt(parts[2]));
            payments.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(item);
            itemList.add(item);
        }

    }

    public void printStudentReport(String id, PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        List<Item> items = payments.getOrDefault(id, new ArrayList<>());

        if(items.isEmpty()){
            printWriter.println("Student " + id + " not found!");
            printWriter.flush();
            return;
        }
        items.sort(Comparator.comparing(Item::getPrice).reversed());
        Student student = new Student(id);
        student.items = items;
        printWriter.println(student);
        printWriter.println("Items:");

        for(int i=0; i<items.size(); i++){
            Item item = items.get(i);
            printWriter.println((i+1) + ". " + item.getName() + " " + item.getPrice());
        }
        printWriter.flush();

    }
}

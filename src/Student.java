import java.util.ArrayList;
import java.util.List;

class Student {


    String id;
    List<Item> items;
    public Student(String id) {
        this.id = id;
        items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getNet(){
        int sum = 0;
        for(Item i : items){
            sum+= i.getPrice();
        }
        return sum;
    }

    public int getFee(){
        if(getNet()*0.0114 < 3) return 3;
        else if(getNet()*0.0114 > 300) return 300;
        else return (int) Math.round(getNet()*0.0114);
    }

    @Override
    public String toString() {
        return String.format("Student: %s Net: %d Fee: %d Total: %d", id, getNet(), getFee(), getNet()+getFee());
    }
}

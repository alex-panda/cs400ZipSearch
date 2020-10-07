import java.util.ArrayList;

public class TestSuite {
   public static void main(String[] args) {
      ArrayList<Place> test = new ArrayList<>();
      DataLoader newData = new DataLoader();
      test = newData.getData();
      for (int i = 0; i <20; i++) {
         System.out.println(test.get(i));
      }
   }
}

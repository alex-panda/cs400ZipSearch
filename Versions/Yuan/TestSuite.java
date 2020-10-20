public class TestSuite {
   public static void main(String[] args) {
//      ArrayList<Place> test = new ArrayList<>();
//      DataLoader newData = new DataLoader();
//      test = newData.getData();
//      RedBlackTree<Place> zipTree = new RedBlackTree<>();
//      for (int i = 0; i < 20; i++) {
//         zipTree.insert(test.get(i));
//      }
//      System.out.println(zipTree);
      ZipCodeRBT zipTree = new ZipCodeRBT();
      System.out.println(zipTree.contains(5001));
      System.out.println(zipTree.colorInorderTraversal());
   }
   
   
}

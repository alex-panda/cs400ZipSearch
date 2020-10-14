// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// Role: Backend Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.ArrayList;

public class ZipCodeRBT {
  private RedBlackTree<Place> RBT;
  private int size; // number of zipCodes stored in the current RBT

  public ZipCodeRBT() {
    RBT = new RedBlackTree<Place>();
    initialization();
  }

  private void initialization() {
    DataLoader dataLoader = new DataLoader();
    ArrayList<Place> data = dataLoader.getData();
    for (int i = 0; i < data.size(); i++)
      RBT.insert(data.get(i));
    size = data.size();
  }

  public Place get(int zipCode) {
    Place place = null;
    RedBlackTree.Node<Place> current = RBT.root;
    while (current != null) {
      if (current.data.getZipCode() == zipCode) {
        place = current.data;
        break;
      } else if (current.data.getZipCode() < zipCode)
        current = current.rightChild;
      else if (current.data.getZipCode() > zipCode)
        current = current.leftChild;
    }
    return place; // return null if zipCode does not exist
  }

  public boolean contains(int zipCode) {
    if (get(zipCode) != null)
      return true;
    return false;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean add(int zipCode, String city, String county, String state) {
    Place newPlace = new Place(zipCode, city, county, state);
    try {
      if (!contains(zipCode)) {
        RBT.insert(newPlace);
        size++;
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean remove(int zipCode) {
    return false;
  }
}

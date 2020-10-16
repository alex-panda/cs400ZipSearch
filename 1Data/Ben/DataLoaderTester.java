import java.util.ArrayList;

public class DataLoaderTester {

	public static void main(String[] args) {
		DataLoader data = new DataLoader();
		ArrayList<Place> list = data.getData();
		for(int i = 18220; i < 18230; i++) {
			System.out.println(list.get(i));
		}
	}

}

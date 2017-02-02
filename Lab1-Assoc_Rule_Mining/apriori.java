import java.util.*;

public class apriori {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Integer> set = new HashSet<Integer>();
		int n;
		System.out.println("Enter the size of the set: ");
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		try{
			
			for(int i=0;i<n;i++){
				set.add(i%5);				
			}
		Set<Set<Integer>> power1 = powerSet1(set);
		
		}catch(Exception e){
			System.out.println(" Try failed");
			e.printStackTrace();
		}
		
		scanner.close();
		
	}
		
	public static <T> Set<Set<T>> powerSet1(Set<T> originalSet){
		Set<Set<T>>s = new HashSet<Set<T>>();
		T a[] = (T[]) originalSet.toArray();
		Set<T> tempSet = new HashSet<T>();
		int counter = (int)Math.pow(2, originalSet.size());
		for(int i=0;i<counter;i++){
			for(int j=0;j<originalSet.size();j++){
				if(((i) & (1<<j)) != 0){
					System.out.print(a[j]+" ");
					tempSet.add(a[j]);
				}
			}
			s.add(tempSet);
			System.out.println("");
			tempSet.clear();
		}
		System.out.println("done");
		return s;
	}

}


//-------------------------------------------------------------------------------

/* 

Usage: ItemsGenerator items = new ItemsGenerator(size, skew, quantity);
Example: ItemsGenerator items = new ItemsGenerator(50, 0.5, 1000);

size = number of unique items
skew = zipf skew. Lies between 0 and 1.
quantity = total quantity of all items inclusive



Description: 
Class `ItemsGenerator` to generate N*Q number of items as a HashMap (item_id -> quantity)

N = number of items
Q = total quantity of items

By default approx ~100 total quantity is generated.
Each item's quantity is scaled up by factor of Q/100 to get Q total quantity.

*/

class ItemsGenerator {
 private Random rnd = new Random(System.currentTimeMillis());
 private int size;
 private int quantity;
 private double skew;
 private double bottom = 0;
 Map frequency_map;

 public ItemsGenerator(int size, double skew, int quantity) {

  this.size = size;
  this.skew = skew;
  this.quantity = quantity;

  for (int i = 1; i < size; i++) {
   this.bottom += (1 / Math.pow(i, this.skew));
  }
  
  this.createItemsMap();

 }

 //The next() method returns an random rank id whose frequency follows Zipf distribution.
 public int next() {
  int rank;
  double frequency;
  double dice;

  rank = rnd.nextInt(size);
  frequency = (1.0d / Math.pow(rank, this.skew)) / this.bottom;
  dice = rnd.nextDouble();

  while (!(dice < frequency)) {
   rank = rnd.nextInt(size);
   frequency = (1.0d / Math.pow(rank, this.skew)) / this.bottom;
   dice = rnd.nextDouble();
  }

  return rank;
 }

 //This method returns a probability that the given rank occurs.
 public double getProbability(int rank) {
  return (1.0d / Math.pow(rank, this.skew)) / this.bottom;
 }

 void createItemsMap(){
  int i, prob, sum = 0;
  frequency_map = new HashMap();
  for (i = 1; i <= this.size; i++) {
   prob = (int)(Math.round(this.getProbability(i) * 100));
   frequency_map.put(i, prob*(this.quantity/100));
   // System.out.println(i + " " + prob);
   sum = sum + prob;
  }
  // System.out.println("sum=" + sum);
 }
}
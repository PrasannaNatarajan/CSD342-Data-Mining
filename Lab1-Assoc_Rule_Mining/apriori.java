import java.util.*;

public class apriori {

	public static void main(String[] args) {
			
	 	/* Auto mode, default values */
	 	ItemsGenerator items = new ItemsGenerator(100, 0.05 , 100000);
 		TransactionsGenerator transactions = new TransactionsGenerator(items,10);
		
		/* Unit Test - Simulating items and transactions generation */
		/*
		for (int i = 0; i < transactions.transactions.size(); i++) {
	        System.out.print((i+1)+". ");
			// System.out.print((transactions.transactions.get(i).size()));
	        for (int j = 0; j < transactions.transactions.get(i).size(); j++) {
	            System.out.print(transactions.transactions.get(i).get(j)+" ");
	        }
	 		System.out.println();
	 	}
	 	*/


	}


/*-------------------------------------------------------------------------------------------------------------------
DESCRIPTION: 
Class to generate simulated transactions with normally distributed number of items in each transaction.

USAGE: TransactionsGenerator transactions = new TransactionsGenerator(ItemsGenerator items, int num_item_range);
EXAMPLE: TransactionsGenerator transactions = new TransactionsGenerator(items, 5);

items = ItemsGenerator object containing generated possible items.
num_item_range = upper bound of nmber of items to generate in one transaction.

transactions.transactions = List of Lists containing items of each transaction as a row.
----------------------------------------------------------------------------------------------------------------------*/

class TransactionsGenerator {

	private ItemsGenerator items;
	private int num_items_range;

	List<List<Integer>> transactions; 

	public TransactionsGenerator(ItemsGenerator items, int num_items_range){
		this.items = items;
		this.num_items_range = num_items_range;
		this.createTransactions();
	};

	void createTransactions(){
		this.transactions = new ArrayList<>();
		int i = 0;

		while(i < this.items.generated_quantity){ /* Until quantity of all items is exhausted, keep generating transactions */
			int num_items; 

			do { /* Find a random (normally distributed) number of items in a transaction within 0 to num_items_range */
				num_items = (int) Math.round(new Random().nextGaussian() * (3) + (num_items_range/2));
			} while (num_items <= 0 || num_items >=num_items_range);

			if(num_items > this.items.generated_quantity-i){ /* If less items are left than required, set required to what's left. */
				num_items = this.items.generated_quantity-i;
			}

			this.transactions.add(new ArrayList<Integer>(num_items));

			for (int j = 0; j < num_items; j++) {
            	// int search_threshold = (this.items.quantity); /* number of items to randomly search till non-zero frequency item is found */
				int pick_item_id = ((int)(Math.random()*this.items.size));
				while((int)(items.frequency_map.get(pick_item_id)) <= 0){
					pick_item_id = ((int)(Math.random()*this.items.size));
				}
				this.transactions.get(this.transactions.size()-1).add(pick_item_id);
				this.items.frequency_map.set(pick_item_id, this.items.frequency_map.get(pick_item_id) - 1); 
				i++;
			}
		}
	}
}


/*-------------------------------------------------------------------------------------------------------------------
DESCRIPTION: 
Class to generate simulated dataset containing N*Q number of items as an ArrayList[item_id] = quantity.

N = number of items
Q = total quantity of all items

USAGE: ItemsGenerator items = new ItemsGenerator(size, skew, quantity);
EXAMPLE: ItemsGenerator items = new ItemsGenerator(50, 0.5, 1000);

frequency_map is ArrayList containing frequency of item index i.

size = number of unique items
skew = zipf skew. 0 -> equally distributed. 10 -> opposite.
quantity = total quantity of all items inclusive
-------------------------------------------------------------------------------------------------------------------*/

class ItemsGenerator {
	private Random rnd = new Random(System.currentTimeMillis());
	public int size;
	public int generated_quantity;
	public int quantity;
	private double skew;
	private double bottom = 0;
	ArrayList<Integer> frequency_map;

	public ItemsGenerator(int size, double skew, int quantity) {

		this.size = size;
		this.skew = skew;
		this.quantity = quantity;

		for (int i = 1; i < size; i++) {
			this.bottom += (1 / Math.pow(i, this.skew));
		}

		this.createItemsMap();
	}

 /* The next() method returns an random rank id whose frequency follows Zipf distribution. */
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

 /* This method returns a probability that the given rank occurs. */
	public double getProbability(int rank) {
		return (1.0d / Math.pow(rank, this.skew)) / this.bottom;
	}

	void createItemsMap(){
		int i, quant_item;
		frequency_map = new ArrayList();
		for (i = 1; i <= this.size; i++) {
			quant_item = (int)((this.getProbability(i))*(this.quantity));
			frequency_map.add(i-1, quant_item);
			System.out.println(i-1 + " " + quant_item);
			generated_quantity = generated_quantity + quant_item;
		}
		System.out.println("generated_quantity =" + generated_quantity);
	}
}
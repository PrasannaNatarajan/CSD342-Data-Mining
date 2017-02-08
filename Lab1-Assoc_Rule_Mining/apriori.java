import java.math.BigInteger;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

class BarChart_AWT extends ApplicationFrame {

    public BarChart_AWT(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Frequent Itemsets",
                "Support",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {

        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        for (int j = 0; j < lab1.selected1.get(0).size(); j++) {
            dataset.addValue(lab1.global_counter.get(j), "{" + lab1.selected1.get(0).get(j) + "}", "Support");
        }

        for (int i = 1; i < lab1.ans.size(); i++) {
            String set = "{";
            for (int j = 0; j < lab1.ans.get(i).size(); j++) {
                set = set + lab1.ans.get(i).get(j) + ",";
            }
            set = set + "}";
            dataset.addValue(lab1.global_counter.get((lab1.ans.get(0).size()) + i - 1), set, "Support");
        }

//        System.out.println(", support = " + );
//            flag = ans.get(i).size();
//            
//      dataset.addValue( 1.0 , fiat , speed );        
//      dataset.addValue( 3.0 , fiat , userrating );        
//      dataset.addValue( 5.0 , fiat , millage ); 
//      dataset.addValue( 5.0 , fiat , safety );           
//
//      dataset.addValue( 5.0 , audi , speed );        
//      dataset.addValue( 6.0 , audi , userrating );       
//      dataset.addValue( 10.0 , audi , millage );        
//      dataset.addValue( 4.0 , audi , safety );
//
//      dataset.addValue( 4.0 , ford , speed );        
//      dataset.addValue( 2.0 , ford , userrating );        
//      dataset.addValue( 3.0 , ford , millage );        
//      dataset.addValue( 6.0 , ford , safety );               
//
        return dataset;
    }
}

public class lab1 {

    public static ArrayList<Integer> global_counter;
    public static int global_index;
    public static ArrayList<ArrayList<Integer>> ans;
    public static ArrayList<ArrayList<Integer>> selected1;

    public static void main(String[] args) {
        global_counter = new ArrayList<Integer>();
        global_index = 0;

        /* Input arguments */
        int num_unique_items = 15;
        int total_quantity = 100000;
        int num_items_per_transaction = 10;
        double zipf_factor = 0.05;
        float s = 100;

        ItemsGenerator items = new ItemsGenerator(num_unique_items, zipf_factor, total_quantity);
        TransactionsGenerator transactions = new TransactionsGenerator(items, num_items_per_transaction);

        /* Print all the generated items (with frequency) and all the transactions */
        System.out.println();
        System.out.println("Transactions generated are:");
        for (int i = 0; i < transactions.transactions.size(); i++) {
            System.out.print((i + 1) + ". ");
            // System.out.print((transactions.transactions.get(i).size()));
            for (int j = 0; j < transactions.transactions.get(i).size(); j++) {
                System.out.print(transactions.transactions.get(i).get(j) + " ");
            }
            System.out.println();
        }

        /* Copy transactions from List<List> to Array of ArrayLists (refactor later!) */
        ArrayList<Integer> new_inp[] = new ArrayList[transactions.transactions.size()];
        for (int i = 0; i < transactions.transactions.size(); i++) {
            new_inp[i] = new ArrayList<Integer>();
            for (int j = 0; j < transactions.transactions.get(i).size(); j++) {
                new_inp[i].add(j, transactions.transactions.get(i).get(j));
            }

        }
        int flag = -1;
        ans = algo(new_inp, s, num_unique_items, num_items_per_transaction);
        for (int i = 1; i < ans.size(); i++) {
            if (!(flag == ans.get(i).size())) {
                System.out.println();
                System.out.println("Frequent " + ans.get(i).size() + "-Itemsets are:");
            }
//            System.out.print((i+1)+". ")
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.println(", support = " + global_counter.get((ans.get(0).size()) + i - 1));
            flag = ans.get(i).size();
        }
        /*
    for(int i=0;i<global_index;i++)
        System.out.println("count of index "+i+" is: "+global_counter.get(i));*/
      System.out.println("done");

        BarChart_AWT chart = new BarChart_AWT("Apriori Algorithm", "Frequent itemsets vs Support");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
        
    }

    public static ArrayList<ArrayList<Integer>> algo(ArrayList<Integer>[] inp, float threshold, int num, int num_items_per_transaction) {

        int index_counter = 0;
        //int max = (int) Math.pow(2, num);
        int c[] = new int[num];
        for (int i = 0; i < num; i++) {
            c[i] = 0;
        }

        selected1 = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> uniqueElements = new ArrayList(num);
        for (int i = 0; i < num; i++) {
            uniqueElements.add(i, i);//[i]=i;
        }
        //////////////////////////////////////////////////////////
        int i = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        while (i < num) {

            for (int j = 0; j < inp.length; j++) {
                if (inp[j].contains(i)) {
                    c[i]++;
//                  System.out.println("The count of "+i+" is:"+c[i]);
                }
            }
            if (c[i] >= threshold) {
                temp.add(i);
                global_counter.add(global_index++, c[i]);
            }

//          System.out.println("temp.size = "+temp.size());
            i++;
        }
        selected1.add(index_counter, temp);
        index_counter++;
        System.out.println();
        System.out.println("Frequent 1-Itemsets are:");
        for (int j = 0; j < selected1.get(0).size(); j++) {
//            System.out.print((j+1)+". ");
            System.out.println(selected1.get(0).get(j) + ", support = " + global_counter.get(j));
        }

        ///////////////////////////////////////////////////////////
        for (int k = 2; k < num_items_per_transaction; k++) {
            
            BigInteger real_fac = factorial(BigInteger.valueOf(selected1.get(0).size())).divide((factorial(BigInteger.valueOf(selected1.get(0).size() - k)).multiply(factorial(BigInteger.valueOf(k)))));
            int numSubsets = real_fac.intValue();
            
            System.out.println("numSubsets = " + numSubsets + ","+ real_fac);
            int[] counter = new int[numSubsets];
            for (int p = 0; p < numSubsets; p++) {
                counter[p] = 0;
            }

            int[] s = new int[k];
            ArrayList<Integer>[] subsets = getSubsetWrapper(numSubsets, selected1.get(0), s, k);

            for (int q = 0; q < inp.length; q++) {
                for (int p = 0; p < numSubsets; p++) {
                    //System.out.println("subsets = "+subsets[p]);
                    //System.out.println("inp["+q+"] = "+inp[q]);
                    //System.out.println("inp[q].containsAll(subsets[p]) = "+subsets[p].containsAll(inp[q]));
                    if (inp[q].containsAll(subsets[p])) {
                        counter[p]++;
                        //System.out.println("counter["+p+"] = "+counter[p]);
                    }

                }
            }
            for (int p = 0; p < numSubsets; p++) {
                if (counter[p] >= threshold) {
                    selected1.add(index_counter, subsets[p]);
                    global_counter.add(global_index++, counter[p]);
                    index_counter++;
                }
            }
        }

        //for(int j=0;j<index_counter;j++){
        //System.out.println("j = "+j+" "+selected1.get(j).size());
        //}
        return selected1;
    }

    public static BigInteger factorial(BigInteger fac) {
        if (fac.compareTo(new BigInteger("1")) ==-1) {
            return new BigInteger("1");
        }

        return (fac.multiply(factorial(fac.subtract(new BigInteger("1")))));
    }

    public static ArrayList<Integer>[] getSubsetWrapper(int numSubsets, ArrayList<Integer> input, int[] subset, int k) {
        ArrayList<Integer>[] subsets = new ArrayList[numSubsets];
        for (int i = 0; i < numSubsets; i++) {
            subsets[i] = new ArrayList<Integer>();
        }
        int[] s = new int[k];                  // here we'll keep indices 
        int index_counter = 0;                  // pointing to elements in input array

        if (k <= input.size()) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (s[i] = i) < k - 1; i++);

            subsets[index_counter] = getSubset(input, s);
            index_counter++;

            for (;;) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && s[i] == input.size() - k + i; i--);
                if (i < 0) {
                    break;
                } else {
                    s[i]++;                    // increment this item
                    for (++i; i < k; i++) {    // fill up remaining items
                        s[i] = s[i - 1] + 1;
                    }
                    //subsets.add(getSubset(input, s));
                    subsets[index_counter] = getSubset(input, s);
                    index_counter++;
                }
            }
        }

        return subsets;
    }

    // generate actual subset by index sequence
    public static ArrayList<Integer> getSubset(ArrayList<Integer> input, int[] subset) {
        ArrayList<Integer> res = new ArrayList(subset.length);
        for (int i = 0; i < subset.length; i++) {
            res.add(i, input.get(subset[i]));
        }

        return res;
    }

}

/*-------------------------------------------------------------------------------------------------------------------
DESCRIPTION: 
Class to generate simulated transactions with normally distributed number of items in each transaction.
USAGE: TransactionsGenerator transactions = new TransactionsGenerator(ItemsGenerator items, int num_item_range);
EXAMPLE: ItemsGenerator items = new ItemsGenerator(items, 5);
items = ItemsGenerator object containing generated possible items.
num_item_range = upper bound of nmber of items to generate in one transaction.
transactions = List of Lists containing items of each transaction as a row.
----------------------------------------------------------------------------------------------------------------------*/
class TransactionsGenerator {

    private ItemsGenerator items;
    private int num_items_range;

    List<List<Integer>> transactions;

    public TransactionsGenerator(ItemsGenerator items, int num_items_range) {
        this.items = items;
        this.num_items_range = num_items_range;
        this.createTransactions();
    }

    ;

    void createTransactions() {
        this.transactions = new ArrayList<>();
        int i = 0;

        while (i < this.items.generated_quantity) {
            /* Until quantity of all items is exhausted, keep generating transactions */
            int num_items;

            do {
                /* Find a random (normally distributed) number of items in a transaction within 0 to num_items_range */
                num_items = (int) Math.round(new Random().nextGaussian() * (1) + (num_items_range / 2));
                /* Mean=range/2 , Variance=3 */
            } while (num_items <= 0 || num_items >= num_items_range);

            if (num_items > this.items.generated_quantity - i) {
                /* If less items are left than required, set required to what's left. */
                num_items = this.items.generated_quantity - i;
            }

            this.transactions.add(new ArrayList<Integer>(num_items));
            HashSet h = new HashSet();
            for (int j = 0; j < num_items; j++) {
                // int search_threshold = (this.items.quantity); /* number of items to randomly search till non-zero frequency item is found */
                int pick_item_id = ((int) (Math.random() * this.items.size));
                while ((int) (items.frequency_map.get(pick_item_id)) <= 0) {
                    pick_item_id = ((int) (Math.random() * this.items.size));
                }
                h.add(pick_item_id);
                this.items.frequency_map.set(pick_item_id, this.items.frequency_map.get(pick_item_id) - 1);
                i++;
            }

            /* Copy all items from Set to List<List> */
            Iterator iter = h.iterator();
            while (iter.hasNext()) {
                this.transactions.get(this.transactions.size() - 1).add((int) iter.next());
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

    void createItemsMap() {
        int i, quant_item;
        frequency_map = new ArrayList();
        System.out.println("Items generated are:");
        for (i = 1; i <= this.size; i++) {
            quant_item = (int) ((this.getProbability(i)) * (this.quantity));
            frequency_map.add(i - 1, quant_item);
            System.out.println(i - 1 + " " + quant_item);
            generated_quantity = generated_quantity + quant_item;
        }
        System.out.println("given quantity = " + this.quantity);
        System.out.println("generated quantity = " + generated_quantity);
    }
}

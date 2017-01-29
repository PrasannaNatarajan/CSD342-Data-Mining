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

import java.util.ArrayList;


public class Cluster {
	
	private ArrayList<Point> members;
	private Point centroid;
	private int id;
	ArrayList<Point> get_members(){
		return this.members;
	}
	
	public Cluster(int id){
		this.id = id;
		this.members = new ArrayList<Point>();
		this.centroid = null;
	}
	
	public ArrayList<Point> getMembers(){
		return this.members;
	}
	
	void add_members(Point p){
		
		this.members.add(p);
	}
	
	public void setMembers(ArrayList<Point> mem){
		this.members = mem;
	}
	
	public Point getCentroid(){
		return this.centroid;
	}
	
	public void setCentroid(Point centroid){
		this.centroid = centroid;
	}
	
	public int getId(){
		return this.id;
	}
	public void printCluster() {
		System.out.println("Cluster: " + id);
		System.out.println("Centroid: " + centroid.printPoint());
		System.out.println("Points: \n");
		for(Point x : members) {
			System.out.println(x.printPoint());
		}
		System.out.println();
	}
}

public class Point{
	private double x;
	private double y;
	private int cluster_id;
	
	Point(double x, double y){
		this.set_x(x);
		this.set_y(y);
		
	}
	
	void set_x(double x){
		this.x = x;
	}
	
	void set_y(double y){
		this.y = y;
	}
	
	
	double get_x(){
		return this.x;
	}
	
	double get_y(){
		return this.y;
	}
	
	public void setClusterId(int id){
		this.cluster_id = id;
	}
	
	public int getClusterId(){
		return this.cluster_id;
	}
	
	public String printPoint(){
		return this.get_x()+" "+this.get_y();
	}
	
}
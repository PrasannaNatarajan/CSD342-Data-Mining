import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.csvreader.CsvReader;

public class lab2 {
	
	//Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;    
    //Number of Points
    private int NUM_POINTS = 7;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = -90;
    private static final int MAX_COORDINATE = 90;
	
    private ArrayList<Point> points;
    private ArrayList<Cluster> clusters;
	
    public lab2(){
    	points = new ArrayList();
    	clusters = new ArrayList();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(2,2);
		Point p2 = new Point(1,1);
		double dist = euc_dist(p1,p2);
		System.out.println(dist);
		
		ArrayList<Point> all_points= new ArrayList<Point>();
		
		try {
			CsvReader data = new CsvReader("input.csv");
			data.setSkipEmptyRecords(true);
			data.readHeaders();
			while(data.readRecord()){
				Point temp = new Point(Double.parseDouble(data.get("lat")),Double.parseDouble(data.get("lng")));
				all_points.add(temp);
				//System.out.println(temp.get_x()+", "+temp.get_y());
			}
			lab2 kmeans = new lab2();
			
			kmeans.points = all_points;
			
			for (int i = 0; i < kmeans.NUM_CLUSTERS; i++) {
	    		Cluster cluster = new Cluster(i);
	    		Random rand = new Random();
	    		int r = rand.nextInt(7);
	    		System.out.println("random ="+r);
	    		Point centroid = kmeans.points.get(r);
	    		cluster.setCentroid(centroid);
	    		kmeans.clusters.add(cluster);
	    	}
			
			kmeans.calculate();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	static double euc_dist(Point p1, Point p2){		
		return Math.pow(Math.pow(p2.get_x()-p1.get_x(),2)+Math.pow(p2.get_y()-p1.get_y(), 2),1/2);
	}
	
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
        	
        	ArrayList<Point> lastCentroids = getCentroids();
        	
        	//Assign points to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	
        	iteration++;
        	
        	ArrayList<Point> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		distance += euc_dist(lastCentroids.get(i),currentCentroids.get(i));
        	}
        	System.out.println("#################");
        	System.out.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance);
        	plotClusters();
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
        }
    }
    
    private void plotClusters() {
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster c = clusters.get(i);
    		c.printCluster();
    	}
    }
    
    private ArrayList<Point> getCentroids() {
    	ArrayList<Point> centroids = new ArrayList(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Point aux = cluster.getCentroid();
    		Point point = new Point(aux.get_x(),aux.get_y());
    		centroids.add(point);
    	}
    	return centroids;
    }
	
    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 
        
        for(Point point : points) {
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = euc_dist(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setClusterId(cluster);
            clusters.get(cluster).add_members(point);
        }
    }
    
    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            ArrayList<Point> list = cluster.getMembers();
            int n_points = list.size();
            
            for(Point point : list) {
            	sumX += point.get_x();
                sumY += point.get_y();
            }
            
            Point centroid = cluster.getCentroid();
            if(n_points> 0) {
            	double newX = sumX / n_points;
            	double newY = sumY / n_points;
                centroid.set_x(newX);
                centroid.set_y(newY);
            }
        }
    }

}


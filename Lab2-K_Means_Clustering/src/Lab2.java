import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import com.csvreader.CsvReader;

public class Lab2 {

 //Number of Clusters. This metric should be related to the number of points
 private int NUM_CLUSTERS;
 private ArrayList < Point > points;
 private ArrayList < Cluster > clusters;

 public Lab2(ArrayList < Point > all_points, int NUM_CLUSTERS) {
  this.NUM_CLUSTERS = NUM_CLUSTERS;
  this.points = all_points;
  this.clusters = new ArrayList < Cluster > ();
  this.initClusters();
 }

 public static void main(String[] args) {

  /* Read all the spatial points from the dataset into the program */
  /* Add all points & Initialise K random clusters */
  /* Run KMeans algorithm on the dataset */
  ArrayList < Point > all_points = readDataFile("C:\\Users\\vishg\\Documents\\NetBeansProjects\\Lab2\\src\\lab2\\input.csv");
  Lab2 kmeans = new Lab2(all_points, 10);
  kmeans.calculate();

 }

 public void initClusters() {
  for (int i = 0; i < this.NUM_CLUSTERS; i++) {
   Cluster cluster = new Cluster(i);
   Random rand = new Random();
   int r = rand.nextInt(7);
   System.out.println("random =" + r);
   Point centroid = this.points.get(r);
   cluster.setCentroid(centroid);
   this.clusters.add(cluster);
  }
 }


 public static ArrayList < Point > readDataFile(String filePath) {
  ArrayList < Point > all_points = new ArrayList < Point > ();
  try {
   CsvReader data = new CsvReader(filePath);
   data.setSkipEmptyRecords(true);
   data.readHeaders();
   while (data.readRecord()) {
    Point temp = new Point(Double.parseDouble(data.get("lat")), Double.parseDouble(data.get("lng")));
    all_points.add(temp);
   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
  return all_points;
 }


 static double euc_dist(Point p1, Point p2) {
  return Math.sqrt(Math.pow(p2.get_x() - p1.get_x(), 2) + Math.pow(p2.get_y() - p1.get_y(), 2));
 }

 //The process to calculate the K Means, with iterating method.
 public void calculate() {
  boolean finish = false;
  int iteration = 0;

  // Add in new data, one at a time, recalculating centroids with each new one. 
  while (!finish) {


   ArrayList < Point > lastCentroids = getCentroids();

   //Assign points to the closer cluster
   assignCluster();

   //Calculate new centroids.
   calculateCentroids();

   iteration++;

   ArrayList < Point > currentCentroids = getCentroids();

   //Calculates total distance between new and old Centroids
   double distance = 0;
   for (int i = 0; i < lastCentroids.size(); i++) {
    distance += euc_dist(lastCentroids.get(i), currentCentroids.get(i));
   }
   System.out.println("#################");
   System.out.println("Iteration: " + iteration);
   System.out.println("Centroid distances: " + distance);
   plotClusters();

   if (distance < 4) {
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

 private ArrayList < Point > getCentroids() {
  ArrayList < Point > centroids = new ArrayList < Point > (NUM_CLUSTERS);
  for (Cluster cluster: clusters) {
   Point aux = cluster.getCentroid();
   Point point = new Point(aux.get_x(), aux.get_y());
   centroids.add(point);
  }
  return centroids;
 }

 private void assignCluster() {
  double max = Double.MAX_VALUE;
  double min = max;
  int cluster = 0;
  double distance = 0.0;

  for (Point point: points) {
   min = max;
   for (int i = 0; i < NUM_CLUSTERS; i++) {
    Cluster c = clusters.get(i);
    distance = euc_dist(point, c.getCentroid());
    System.out.println("distance= " + distance + " " + i);
    if (distance < min) {
     min = distance;
     cluster = i;
     System.out.println("here" + i);
    }
   }
   point.setClusterId(cluster);
   clusters.get(cluster).add_members(point);
   System.out.println("added" + cluster);

  }
 }

 private void calculateCentroids() {
  for (Cluster cluster: clusters) {
   double sumX = 0;
   double sumY = 0;
   ArrayList < Point > list = cluster.getMembers();
   int n_points = list.size();

   for (Point point: list) {
    sumX += point.get_x();
    sumY += point.get_y();
   }

   Point centroid = cluster.getCentroid();
   if (n_points > 0) {
    double newX = sumX / n_points;
    double newY = sumY / n_points;
    centroid.set_x(newX);
    centroid.set_y(newY);
   }
  }
 }
}
/*
 This is the submission for the graded lab assignment #2, which consists of implementation of k-means algorithm.
 The algorithm has been implemented in Java v1.7.
 This implementation has been successfully tested for up to 1000000 elements. 
 Inputs  : the number of clusters, the dataset as a csv file and the distance metric.
 Outputs : all the data points assigned to a cluster and a graph which shows the cluster heads and the points.

 CONTRIBUTORS
 Atish Majumdar      : 1410110081
 Prasanna Natarajan  : 1410110298
 Vishal Guaba        : 1410110501
*/

package lab2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import com.csvreader.CsvReader;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Lab2 {

 /* Initial variables */
 private static int NUM_CLUSTERS;
 private static int distance_algo = 0;
 private ArrayList < Point > points;
 private ArrayList < Cluster > clusters;


 public Lab2(ArrayList < Point > all_points, int NUM_CLUSTERS) {
  this.NUM_CLUSTERS = NUM_CLUSTERS;
  this.points = all_points;
  this.clusters = new ArrayList < Cluster > ();
  this.initClusters();
 }

 public static void main(String[] args) {

  /* Default values */
  int num_clusters = 5;
  String filename = "C:\\Users\\vishg\\Documents\\NetBeansProjects\\Lab2\\src\\lab2\\input.csv";

  /* Input Arguments */
  for (int i = 0; i < args.length; i++) {
   switch (args[i]) {
    case "-k":
     num_clusters = Integer.parseInt(args[++i]);
     break;
    case "-f":
     filename = args[++i];
     break;
    case "-d":
     distance_algo = Integer.parseInt(args[++i]);
     break;
    default:
     break;
   }
  }
  String dis = "Eucledian";
  if (distance_algo == 1) {
   dis = "Manhattan";
  }
  System.out.println("The inputs are : number of clusters = " + num_clusters + "\n filename = " + filename + "\n distance_algorithm = " + dis);


  /* 
     Read all the spatial points from the dataset into the program
     Add all points & Initialise K random clusters 
     Run KMeans algorithm on the dataset 
  */

  ArrayList < Point > all_points = readDataFile(filename);
  Lab2 kmeans = new Lab2(all_points, num_clusters);
  kmeans.calculate();
  // kmeans.printClusters();     
  new PlotClusters("Clusters").plot(kmeans.createDataset());
 }


 /* 
     Create dataaset to pass to the visualiser
     It is of XYSeriesCollection object in the form of X,Y 
  */

 private XYSeriesCollection createDataset() {
  XYSeriesCollection result = new XYSeriesCollection();

  XYSeries series = new XYSeries("centroids");
  for (int i = 0; i < NUM_CLUSTERS; i++) {
   System.out.println(clusters.get(i).getCentroid().get_x() + ", " + clusters.get(i).getCentroid().get_y());
   series.add(clusters.get(i).getCentroid().get_x(), clusters.get(i).getCentroid().get_y());
  }
  result.addSeries(series);

  for (int i = 0; i < NUM_CLUSTERS; i++) {
   Cluster c = clusters.get(i);
   series = new XYSeries("Cluster_" + i);
   ArrayList < Point > pt = c.getMembers();
   for (int j = 0; j < pt.size(); j++) {
    series.add(pt.get(j).get_x(), pt.get(j).get_y());
   }
   result.addSeries(series);
  }
  return result;
 }


 /*
   Initialise the clusters with random points
 */

 public void initClusters() {
  int flag = 0;
  for (int i = 0; i < this.NUM_CLUSTERS; i++) {
   flag = 0;

   Random rand = new Random();
   int r = rand.nextInt(7);
   Point centroid = this.points.get(r);
   for (int j = 0; j < i; j++) {
    if (this.clusters.get(j).getCentroid().equals(centroid)) {
     flag = 1;
     break;
    }
   }
   if (flag == 1) {
    i--;
    continue;
   }
   Cluster cluster = new Cluster(i);
   cluster.setCentroid(centroid);
   this.clusters.add(cluster);
  }
  System.out.println("the size of clusters = " + this.clusters.size());
 }


 /* 
    Read the dataset from a file into the program
 */

 public static ArrayList < Point > readDataFile(String filePath) {
  ArrayList < Point > all_points = new ArrayList < Point > ();
  try {
   CsvReader data = new CsvReader(filePath);
   data.setSkipEmptyRecords(true);
   data.readHeaders();
   while (data.readRecord()) {
    Point temp = new Point(Double.parseDouble(data.get("lng")), Double.parseDouble(data.get("lat")));
    all_points.add(temp);
   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
  return all_points;
 }


 /* 
   Distance function 
 */

 static double distance_func(Point p1, Point p2) {
  if (distance_algo == 1)
   return Math.abs(p2.get_x() - p1.get_x()) + Math.abs(p2.get_y() - p1.get_y());
  else
   return Math.sqrt(Math.pow(p2.get_x() - p1.get_x(), 2) + Math.pow(p2.get_y() - p1.get_y(), 2));
 }

 /*
    Iteratively run K Means algorithm.
 */
 public void calculate() {
  boolean finish = false;
  int iteration = 0;

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
    distance += distance_func(lastCentroids.get(i), currentCentroids.get(i));
   }

   //    new PlotClusters("Clusters").plot(this.createDataset());

   if (distance < 3) {
    finish = true;
   }
  }
 }


 /*
   Output the cluster and it's points on the console
 */

 private void printClusters() {
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

  for (Cluster c: clusters) {
   c.flag = 0;
  }

  for (Point point: points) {
   min = max;
   for (int i = 0; i < NUM_CLUSTERS; i++) {
    Cluster c = clusters.get(i);
    distance = distance_func(point, c.getCentroid());
    if (distance < min) {
     min = distance;
     cluster = i;
    }
   }
   point.setClusterId(cluster);
   if (clusters.get(cluster).flag == 0) {
    clusters.get(cluster).setMembers(new ArrayList < Point > ());
    clusters.get(cluster).flag = 1;
   }
   clusters.get(cluster).add_members(point);

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
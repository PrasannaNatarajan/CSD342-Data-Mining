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

import java.util.ArrayList;


public class Cluster {

 private ArrayList < Point > members;
 private Point centroid;
 private int id;
 int flag;
 ArrayList < Point > get_members() {
  return this.members;
 }

 public Cluster(int id) {
  this.id = id;
  this.members = new ArrayList < Point > ();
  this.centroid = null;
  flag = 0;
 }

 public ArrayList < Point > getMembers() {
  return this.members;
 }

 void add_members(Point p) {

  this.members.add(p);
 }

 public void setMembers(ArrayList < Point > mem) {
  this.members = mem;
 }

 public Point getCentroid() {
  return this.centroid;
 }

 public void setCentroid(Point centroid) {
  this.centroid = centroid;
 }

 public int getId() {
  return this.id;
 }
 public void printCluster() {
  System.out.println("Cluster: " + id);
  System.out.println("Centroid: " + centroid.printPoint());
  System.out.println("Points: \n");
  for (Point x: members) {
   System.out.println(x.printPoint());
  }
  System.out.println();
 }
}
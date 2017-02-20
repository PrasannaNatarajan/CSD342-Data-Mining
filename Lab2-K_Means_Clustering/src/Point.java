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

public class Point {
 private double x;
 private double y;
 private int cluster_id;

 Point(double x, double y) {
  this.set_x(x);
  this.set_y(y);

 }

 void set_x(double x) {
  this.x = x;
 }

 void set_y(double y) {
  this.y = y;
 }


 double get_x() {
  return this.x;
 }

 double get_y() {
  return this.y;
 }

 public void setClusterId(int id) {
  this.cluster_id = id;
 }

 public int getClusterId() {
  return this.cluster_id;
 }

 public String printPoint() {
  return this.get_x() + ", " + this.get_y();
 }

}
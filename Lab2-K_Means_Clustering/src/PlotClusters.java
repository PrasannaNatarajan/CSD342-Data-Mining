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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import java.awt.Color;
import java.awt.Shape;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.ShapeUtilities;


public class PlotClusters extends ApplicationFrame {

 public PlotClusters(String title) {
  super(title);
 }

 public void plot(XYSeriesCollection dataset) {
  JFreeChart chart = ChartFactory.createScatterPlot(
   "Clusters", // chart title
   "Column1", // x axis label
   "Column2", // y axis label
   dataset, // data  ***-----PROBLEM------***
   PlotOrientation.VERTICAL,
   true, // include legend
   true, // tooltips
   false // urls
  );

  ChartPanel chartPanel = new ChartPanel(chart);
  chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
  Shape cross = ShapeUtilities.createDiagonalCross(5, 5);
  XYPlot plot = (XYPlot) chart.getPlot();
  XYItemRenderer renderer = plot.getRenderer();
  renderer.setSeriesShape(0, cross);
  renderer.setSeriesPaint(0, Color.black);
  renderer.setSeriesVisible(0, true);
  plot.setRenderer(renderer);
  getContentPane().add(chartPanel);

  this.pack();
  this.setVisible(true);
 }
}
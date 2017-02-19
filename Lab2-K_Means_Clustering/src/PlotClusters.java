/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.ScatterRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

/**
 *
 * @author vishg
 */
public class PlotClusters extends ApplicationFrame{

    public PlotClusters(String title) {
        super(title);
    }
    
    public void plot(XYSeriesCollection dataset) {
//      ScatterPlot plot = new ScatterPlot("KMeans Output", 800,
//				plottingDataSet);
//		plot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		plot.pack();
//		plot.setLocationRelativeTo(null);
//		plot.setVisible(true);
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
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//      final XYPlot plot = chart.getXYPlot( );
//        ScatterRenderer renderer = new ScatterRenderer();
//      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        Shape cross = ShapeUtilities.createDiagonalCross(7, 7);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesShape(0, cross);
      renderer.setSeriesPaint( 0 , Color.black );
      renderer.setSeriesVisible(0, true);
      plot.setRenderer(renderer);
      getContentPane().add(chartPanel);
      
      this.pack();
      this.setVisible(true);      
 }   
}
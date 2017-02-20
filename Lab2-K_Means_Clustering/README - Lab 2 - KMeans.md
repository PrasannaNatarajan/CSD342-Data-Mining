# README - Lab 2 - K Means

### **DESCRIPTION**

K Means algorithm for Clustering in Java

### **DATASET**

- The data file can be provided through command line flag `-f <file_path>`.
- It should be a CSV file with X and Y as first two columns.

### **COMPILATION**

`javac –cp jcommon-1.0.23.jar:jfreechart-1.0.19.jar Lab2.java Cluster.java Point.java PlotCluster.java`

### **RUN**

`java Lab2 –k <k clusters> –d <distance algorithm> -f <file_path> `

All parameters are optional, if not specified, default values will be used.

### **OPTIONS**

- k_clusters - Integer input to define number of clusters. 

  - Default=7.
  - Tested Input Range= {0,100}
- distance_algorithm - Integer input to define which distance algorithm to use.

  - Default=0. 
  - Input Range= {0,1}
  - "0" stands for eucledian distance
  - "1" stands for manhattan distance

- file_path

  - Path of the data file.


### **EXAMPLE**

`java Lab2 -k 7 -d 1 -f input.csv`

Any specifiers omitted by the user will use the default values during execution.

### **PERFORMANCE METRICS**

When tested with a dataset of 7000 cities of the world, 

- the total memory usage was **105MB** and 
- the execution time was **0.16 seconds**



### **CONTRIBUTORS**

- [Atish Majumdar](//github.com/atish-maj) 

- [Prasanna Natarajan](//github.com/PrasannaNatarajan)

- [Vishal Gauba](//github.com/FlameFractal)

  ​

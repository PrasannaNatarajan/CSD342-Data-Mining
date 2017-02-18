# README - Lab 2 - KMeans

### **NAME**

K Means - K Means algorithm for Clustering.

### **SYNOPSIS**

This is an implementation of k-means algorithm for clustering in Java.

### **DATASET**

- The data file can be provided through command line flag `-f <file_path>`.
- It should be a CSV file with X and Y as first two columns.

### **COMPILATION**

`javac –cp jcommon-1.0.23.jar:jfreechart-1.0.19.jar Lab2.java`

### **RUN**

`java Lab2 –k <k clusters> –d <distance algorithm> -f <file_path> `

All parameters are optional, if not specified, default values will be used.

### **OPTIONS**

- k_clusters

  - Integer input to define number of unique items. 
    - Default=7.
    - Tested Input Range= {0,100}
- distance_algorithm

  - Integer input to define total number of items.
    - Default=100,000. 
    - Tested Input Range= {0,1000000}

- file_path

  - Path of the data file.


### **Example**

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

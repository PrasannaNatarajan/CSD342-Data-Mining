## K-Means Clustering

Think of this as a design problem. Try to solve a problem with it.

- Distance Algorithm
  - Least Squares (done)
  - Manhattan
- Residual Sum of Squares for "goodness"
  - rss threshold
- Termination condition
  - time based
  - judgement based
  - rss threshold (done)

### To Do
- Write Output to a csv file
- Write some visualization code
- If possible try and use python for visualizations
- Make all inputs, flags and get it from the terminal 
- Give option for manhattan distance

### Github Code

- https://github.com/timpalpant/java-genomics-toolkit/blob/master/src/edu/unc/genomics/visualization/KMeans.java

### Visualisation

- ​Python?

### Number of Clusters

- Number of clusters **should match the data**. 
  An incorrect choice of the number of clusters will invalidate the whole process. 
- Empirical way 
  - try with different number of clusters 
  - measure the **resulting sum of squares**.

Curious? [This paper](http://link.springer.com/article/10.1007%2FBF02294245) benchmarks (monte-carlo) 30 procedures for estimating the number of clusters.

### Initial position of the clusters

[Common methods](http://www.onmyphd.com/?p=k-means.clustering):

- **Forgy:** set the positions of the k clusters to k observations chosen randomly from the dataset.
- **Random partition:** assign a cluster randomly to each observation and compute means as in step 3.

Since the algorithm stops in a local minimum, the initial position of the clusters is very important

### Datasets

- https://edureka.wistia.com/medias/eea6j441fs/download?media_file_id=76016491

- http://archive.ics.uci.edu/ml/

- http://kaggle.com
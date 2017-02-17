# README - Lab 1 - Apriori

### **NAME**

Apriori- Apriori algorithm for Association Rule Mining 

### **SYNOPSIS**

This is an implementation of apriori algorithm forassociation rule mining in Java.

### **COMPILATION**

`javac –cp jcommon-1.0.23.jar:jfreechart-1.0.19.jar Apriori.java`

### **RUN**

`java Apriori –n <number of unique items> –q <totalquantity of items> –i <no. of items per transaction> –zf <zipffactor> -s <support threshold>`

All parameters are optional, if not specified, defaultvalues will be used.

 

### **OPTIONS**

- <num_unique_items>

  - Integer input to define number of unique items. Default=15.Tested Input Range= {0,100}

- <total_quantity>

  - Integer input to define total number of items.Default=100,000. Tested Input Range= {0,1000000}

- <num_items_per_transaction>

  - Integer input to define number of items per transaction.Default=10. Tested Input Range= {5,10}

- <zipf_factor>

  - Double input to define the Zipf Factor for frequencydistribution. Default=0.05 Input Range= {0,1}

- <support>

  - Float input to define the support threshold. Default=100Input Range= {20,600}

  ​

### **Example**

`*java Apriori -n 800 -q500000 -i 6 -zf 0.05 -s 200*`

Any specifiers omitted by the user will use the defaultvalues during execution.

 

### **CONTRIBUTORS**

- [Atish Majumdar](//github.com/atish-maj) 

- [Prasanna Natarajan](//github.com/PrasannaNatarajan)

- [Vishal Gauba](//github.com/FlameFractal)

  ​
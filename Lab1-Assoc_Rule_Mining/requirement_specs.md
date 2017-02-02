### Table of contents

1. [Generate random items (Zipf-ian)](#generate-random-items)
2. [Generate random transactions](#generate-random-transactions)
3. [Apriori Rule](#apriori-rule)
4. [Visualization](#visualization)
5. [README and Documentation](#readme-and-documentation)

<br><br>

## Generate random items

- (Zipf distribution / Pareto principle)

- Frequency of items. Begin with 5 - 10 items but demo with a lot more (10000)

- Steps to generate

  - create n buckets for n items 

  - define a parameter for total frequency of all items

    - Eg. {a,b,c,d}, {b,c,d}, {c,d}, parameter (all freq) = 9

    - | Item | Frequency |
      | ---- | --------- |
      | a    | 1         |
      | b    | 2         |
      | c    | 3         |
      | d    | 3         |

  - zipf factor - 0 < f < 1 , **use 0.5**

    - higher the factor - more skewed the distribution
      lower the factor - well distributed uniform sample

## Generate random transactions

- generate random number for **num_items** in transaction **i**
- generate random number (id) to decide which items to include in a transcation
  - check if any one item's frequency is 0 before picking up
  - check if total frequency is less than num_items (in the end)
- pick up from the item table {decrement frequency by 1}

## Apriori Rule

## Visualization

- Itemset-Support table

  - | Itemset | Support |
    | ------- | ------- |
    | {a,b,c} | 1       |

  - For visualisation, show itemsets with support > 30% of average support

  - Don't show more than 5-10 itemsets

## README and Documentation

- assoc.java -n {num_items=default_val} -s {support_threshold=default_val}
- Well commented code! Along with {name - roll no} as a comment on top of the code

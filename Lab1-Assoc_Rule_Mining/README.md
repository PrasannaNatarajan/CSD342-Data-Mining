# Requirement Specification

### Table of contents

1. [Generate random items (Zipf-ian)](#generate-random-items)

2. [Generate random transactions (Normally distributed)](#generate-random-transactions)

3. [Apriori Rule](#apriori-rule)

4. [Visualization](#visualization)

5. [Documentation](#documentation)

   <br>

## Generate random items

-   Quantities should be in Zipf distribution (Pareto principle)

-   Frequency of items. Begin with 5 - 10 items but demo with a lot more (10,000)

- | Item | Frequency |
    | ---- | --------- |
    | a    | 1         |
    | b    | 2         |
    | c    | 3         |
    | d    | 3         |

-   Zipf factor - 0 < f < 1 , **use 0.5**
    - higher the factor - more skewed the distribution
    - lower the factor - well distributed uniform sample

## Generate random transactions

- Number of items in transaction
  - Generate randomly but normally distributed. Most people will buy 3-7 items in case of 0 to 10 items (max range)
- Generate random number (id) to decide which items to include in that transcation
  - check if any one item's frequency is 0 before picking up
  - check if total frequency is less than num_items (in the end)
- Pick up from the item table {decrement frequency by 1}

## Apriori Rule

- Apply the apriori principle in generating candiate itemsets.

## Visualization

- Itemset-Support table

  - | Itemset | Support |
    | ------- | ------- |
    | {a,b,c} | 1       |

  - For visualisation, show itemsets with support > 30% of average support

  - Don't show more than 5-10 itemsets

## Documentation

- Show flags with default values
- assoc.java -n {num_items=default_val} -s {support_threshold=default_val}
- Well commented code along with {name - roll no} as a comment on top of the code


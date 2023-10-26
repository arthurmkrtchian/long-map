# LongMap Implementations

This repository contains two implementations of the LongMap data structure, implemented in the `LongMapImpl` class. The task was to create a map with keys of type long, using a hash table approach similar to HashMap. The primary focus was on adhering to good coding style and test practices, as well as optimizing memory usage and ensuring adequate performance.

### Implementations

- `array-based-solution`: This implementation uses an array as the underlying data structure.
- `arraylist-based-solution`: This implementation uses an ArrayList as the underlying data structure.

You can switch between branches to explore the different implementations.


## Performance

The performance of various longhmap methods has been tested under different load scenarios. 

The benchmarking was conducted using datasets of varying sizes:

20,000,000 elements
10,000,000 elements
5,000,000 elements
2,500,000 elements

In the table below displays:
1. How fast the methods work in theory (referred to as "Complexity").
2. The actual time they took (ms).

### Performance Analysis of array-based LongMap Methods

| **Method**               | **Avg. Complexity** | **for 20 mln elements** | **10 mln** | **5 mln** | **2.5 mln** |
|--------------------------|---------------------|-------------------------|------------|-----------|-------------|
| `put(long key, V value)` |**O(n)**             | 2048.42                 | 983.99     | 386.28    | 208.69      | 
| `get(long key)`          | **O(1)**            | 1.27 	                 | 0.43       | 0.10      | 0.07        |
| `remove(long key)`       | **O(1)**            | 1.53                    | 0.31       | 0.14      | 0.11        |
| `isEmpty()`              | **O(1)**            | 0.23 	                 | 0.09 	    | 0.05 	    | 0.04        |
| `containsKey(long key)`  | **O(1)**            | 0.19 	                 | 0.18       | 0.06 	    | 0.05        |
| `containsValue(V value)` | **O(1)**            | 0.25 	                 | 0.12 	    | 0.05      | 0.05        |
| `keys()`                 | **O(n)**            | 378.35 	               | 201.72     | 51.89     | 22.39       |
| `values()`               | **O(n)**            | 401.07 	               | 255.61     | 94.49     | 46.05       |
| `size()`                 | **O(1)**            | 0.28                    | 0.58       | 0.06 	    | 0.06        |
| `clear()`                | **O(n)**            | 17.02                   | 17.57      | 12.95     | 3.04        |


### Performance Analysis of arrayList-based LongMap Methods

| **Method**               | **Avg. Complexity** | **for 20 mln elements** | **10 mln** | **5 mln** | **2.5 mln** |
|--------------------------|---------------------|-------------------------|------------|-----------|-------------|
| `put(long key, V value)` | **O(n)**            | 2233.64                 | 1224.53    | 424.92    | 232.37      | 
| `get(long key)`          | **O(1)**            | 1.30 	                 | 1.16       | 0.08      | 0.06        |
| `remove(long key)`       | **O(1)**            | 0.32                    | 0.14       | 0.48      | 0.08        |
| `isEmpty()`              | **O(1)**            | 0.19 	                 | 0.12 	    | 0.06 	    | 0.05        |
| `containsKey(long key)`  | **O(1)**            | 0.35 	                 | 0.13       | 0.06 	    | 0.05        |
| `containsValue(V value)` | **O(1)**            | 0.26 	                 | 0.15 	    | 0.06      | 0.05        |
| `keys()`                 | **O(n)**            | 426.61 	               | 155.40     | 73.76     | 29.30       |
| `values()`               | **O(n)**            | 676.70 	               | 366.33     | 199.70    | 82.86       |
| `size()`                 | **O(1)**            | 0.31                    | 0.10       | 0.08 	    | 0.06        |
| `clear()`                | **O(n)**            | 43.53                   | 35.85      | 12.01     | 3.68        |


Despite the increased runtime of the methods `get(long key)`, `remove(long key)`, `isEmpty()`, `containsKey(long key)`, and `containsValue(V value)` under various loads, their maximum execution time does not exceed 2 ms. For this reason, they are assigned a complexity of **O(1)**. The same situation arises when testing the standard **HashMap**.

When comparing the two implementations using **Array** and **ArrayList**, it's evident that the difference in execution time can reach up to **68%** in favor of the array implementation, especially in the case of the values() method.


### Testing Environment Specifications:

The following tests were conducted on this machine:

- **OS**: Ubuntu Jammy Jellyfish (LTS)
- **Kernel**: 5.15
- **CPU**: Core i5-6200U (2.3 - 2.8 GHz)
- **Core Quantity**: Dual-Core
- **GPU**: Nvidia GeForce 940MX
- **Storage**: 512GB SSD
- **RAM**: 16GB 2133Mhz
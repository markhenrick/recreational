# Day 1
## Part 1

In any case, all elements will need to be scanned, making this O(n) at best. I did wonder if there were any number tricks to pull (possibly involving logarithms), but addition and multiplication aren't exactly slow operations.

* let seen = some implementation of a set
* for number:
  * let complement = 2020 - number
  * if seen.contains(complement):
	* print number * complement
	* halt
  * else:
      * seen.insert(number)
* assertion failed

If the arithmetic, branching, and set operations are O(1), then this solution is O(n).

Possible ways to implement the set:

* Hash set - operations are O(1) amortised
* Tree set - likely to be inferior both asymptotically and in real-world to a hash set
* Sorted dynamic array with binary search - insertion is O(1) amortised, querying is O(log n), but real world performance may well beat the hash set
* **Bit set** [Chosen] - the logical conclusion of the dynamic array with full population. 2020 is a rather small number, requiring just 253 bytes, so this solution is quite feasible even on constrained hardware

## Part 2

Not sure that my algorithm is optimal

* *Compute a map of the sum of each pair of numbers to the product of them:*
    * let sumsToProducts = a map
    * for num1 in input list:
        * for num2 in remainder of list:
            * sumsToProducts.put(num1 + num2, num1 * num2)
* for number:
    * let complement = 2020 - number
    * if there is some mapping "complement -> product" in sumsToProducts:
        * return number * product
* assertion failed

The initial generation of the map is O(n^2), iterating over the numbers again is O(n), with O(1) map lookups, giving an overall total time complexity of O(n^2).

Good thing 2020 isn't divisble by three or I'd have to solve the case where 673.3 appears twice, but my algorithm mistakes it for appearing thrice.

One of my friends pointed out that this is a variant of the [3SUM](https://en.wikipedia.org/wiki/3SUM) problem

# Day 4

In my Java implementation I split the input string into records (i.e. on `\n\n`) beforehand. I wonder if the `split` method uses slices that reference the same memory, or if this ends up allocating double the memory that I really need.

Either way, I'm loading the whole file into memory upfront, rather than reading lines on-demand, so that could be improved.

# Day 5

Each seat is just a binary number under the non-injective mapping `{{F, L} -> 0, {B, R} -> 1}`. But just doing a string replacement would be boring.

The lexicographic ordering on `<F, B, L, R>` is isomorphic to the decoded ordering, i.e. `min(map(decode, input)) = decode(min(input))`. This probably isn't any faster, but it's more interesting.

I found the instructions for part 2 a little hard to understand. My wording of it is that you need to find the gap in the ordered list of seats, which may be padded at both ends by empty seats.

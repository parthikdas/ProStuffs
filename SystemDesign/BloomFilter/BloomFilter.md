Bloom filter is space efficient probabilitic data struture. It says whether a ele is maybe present or for sure not present.

# How it works:
- uses a fixed array and many hash functions, so its very fast
- The ele are hashed by many hash func and the resulted indexes its made as 1
- So if a index is 1 its says maybe present  but if 0 its not present

# Cons:
- It may say that ele is maybe present even if its not so chances of false +ve inc with inc in no of ele.

# Variants:
- Counter Bloom Filter - Uses counter instead of bits to avoid more false +ve
- Scalable Bloom Filter - Auto scales to avoid false +ve
- Compressed Bloom Filter - Optimised for transmission over network

# Implementation:
We use setbit to store it. Its a dynamic array and its like u can imagine 64 bits.
The storage capacity of setbit depends on ram. So u can store billions and millions of numbers.
It basically turns on or off. So <var>.set(1222) means in that index u are putting 1 
2 billion bits ≈ ~250 MB , so it basically depends on RAM. But techically lets take INTEGER.MAX_INT
<BitSet bs = new BitSet(); // u can give size in constructor>
<bs.set(1_000_000); // Sets the 1,000,000th bit>
<System.out.println(bs.get(1_000_000)); // true >


# UseCases:
Placing Bloom filter before db or redis calls for keys that don't exist.

1. DynamoDB Partition Routing

Bloom filters can be used in partition routing logic to quickly check which partition might contain a key before querying.
Reduces I/O and latency.
2. S3 Key Existence Optimization

When checking if a file exists in S3, a Bloom filter can be placed at the edge layer or client side to avoid unnecessary head/object calls.
3. Amazon CloudFront / CDN Caching

In edge locations, Bloom filters help determine whether a cache key is likely to exist to avoid expensive origin lookups.
4. Fraud & Abuse Detection

Used in large-scale event processing systems to flag repeat or known bad tokens, email IDs, IPs, etc., in real-time with minimal memory.
5. Personalized Recommendations / Search

Filters out obviously irrelevant results during query pre-processing phase, especially in query suggestions or autocomplete.
6. Lambda + API Gateway / Step Functions

You might find Bloom filters in serverless flows to:
Skip invoking Lambdas unnecessarily.
Avoid cold-start overhead for obvious "miss" cases.
package CompanyBaseQue;

public class imdb_sde2_amazon {
    /*
     IMDB, a subsidiary of Amazon, is a popular platform to find ratings on movies and TV shows.
Data analysts are exploring user preferences by analyzing sequences 665 of movie ratings. One metric they're interested in is the count of positions where each rating is followed by a higher rating. They want to find the maximum possible value of this metric by rearranging the ratings in the most optimal way.
Given array ratings of n integers, find the maximum possible number of indices i such that ratings[i] < ratings[i + 1] after rearranging the array optimally.
-aa62,
Example n =3
ratings = [2, 1, 3]
Considering all possible arrangements of the array ratings:Thus, the maximum possible number of indices i such that ratings[i] < ratings[i + 1] is 2.
Hence, the answer is 2.


This can be done using following algo, let's say total ans = 0:

Take a hashmap, with key as arr[i] and value as its freq.
ans = ans+(hashmap.size()-1)
go to each elem of hashmap and reduce freq by 1. Remove key, if freq becomes zero.
ans = ans+(hashmap.size()-1)
Do this till hashmap is empty.
ans will be total such values where after rearranging we get arr[i] < arr[i+1].
ex: [1,1,1,3,3,2,2,2]
map: 1->3, 3->2, 2->3
ans = 2
new_map : 1->2, 3->1, 2->2
ans = 4
new_map: 1->1, 2->1 [Note: 3 is gone]
ans = 5
new_map: [] [all freq are zero]
so the ans if 5.
     */
}

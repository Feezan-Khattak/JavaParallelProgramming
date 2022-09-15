# Fork Join Basic
In parallel computing, the fork–join model is a way of setting up and 
executing parallel programs.

# Code Flow
In the code we are doing the search operation on some array. One of the
traditional way is to iterate on the whole array and find the search
element, but it is more time-consuming. Now in order to make it fast, we
use the divide and conger rule. we divide the whole array into parts and
then divide each parts and sub parts and so on. by this we can perform 
parallel operation on each part and gets the result. 

so in the code I put the threshold condition. i.e. divide the array 
till it each to its size 3. So it will divide the array till the size is 3,
then it will perform search operation on it. and submit the result, same 
with the second part and so on.

# Code Understanding
First we made the pool (its better to use the common pool instead 
of separate pool for each thread)

     ForkJoinPool pool = ForkJoinPool.commonPool();

once we have the pool we submit our task to that pool using the 
``invoke method``. The invoke method on the fork join pool submits a 
task to the pool, which then starts running on a separate thread, calls the compute
method on that thread, and then waits for a result.

    pool.invoke(newSearch);

Now in the compute method (override from the RecursiveTask), we simply
divide the array and fork it. and then wait to join it and return the result.
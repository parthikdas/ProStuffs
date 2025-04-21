const fib = (n) => { // Normal recursion
    if(n<=2) return 1;
    return fib(n-1) + fib(n-2);
}

const dpFib = (n, mem = {}) => { // DP memoziation
    if(n<=2) return 1;
    if(!(n in mem)) mem[n] = fib(n-1, mem) + fib(n-2, mem);
    return mem[n];
}
// now mem contains every number in the series

console.log(dpFib(5))
console.log(dpFib(15))
console.log(dpFib(25))
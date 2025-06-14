// We will given a array and a dynamic number via which we need divide the 1d array
// [1,2,3,4] n = 3 => [[1,2,3], [4]]
function defFunc(a, n) { // O(m)
    if (n > a.length) return;
    let ans = [];
    for(let i = 0; i < a.length; i += n) { //
        ans.push(a.slice(i, i + n));
    }
    return ans;
}
function customFunc(a, n) { // O(m)
    if (n > a.length) return;
    let ans = [];
    let i = 0;
    while (i < a.length) {
        if (ans.length === 0 || ans[ans.length - 1].length === n) {
            ans.push([]);
        }
        ans[ans.length - 1].push(a[i]);
        i++;
    }
    return ans;
}
const a = [1,2,3,4,5,6];
const n = 2;
console.log(val(a,n))


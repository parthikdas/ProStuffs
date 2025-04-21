/*
 448. Find All Numbers Disappeared in an Array
 public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] a = new int[100001];
        int max = -1;
        for(int n:nums) {
            if(n>max) max = n;
            a[n]++;
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=1;i<=nums.length;i++) if(a[i]==0) ans.add(i);
        if(ans.isEmpty() && max != nums.length) ans.add(nums.length+1);
        return ans;
    }
 */
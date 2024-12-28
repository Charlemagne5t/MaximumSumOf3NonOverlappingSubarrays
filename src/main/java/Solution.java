class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int sum = 0;
        int[] res = new int[3];

        for(int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int[] sums = new int[nums.length - k + 1];
        sums[0] = sum;
        for(int i = k; i < nums.length; i++){
            sum += nums[i] - nums[i - k];
            sums[i - k + 1] = sum;
        }
       
        int[][] dp = new int[sums.length][3];
        dp[0][0] = sums[0];
        dp[0][1] = sums[0];
        dp[0][2] = sums[0];
        int maxInd = -1;
        int max = 0;
        for(int i = 1; i < dp.length;i++) {
            dp[i][0] = Math.max(sums[i], i - k >= 0 ? dp[i - k][0] : 0);
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][0]);
            dp[i][1] = Math.max(sums[i] + (i - k >= 0 ? dp[i - k][0] : 0), i - k >= 0 ? dp[i - k][1] : 0);
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][1]);
            dp[i][2] = Math.max(sums[i] + (i - k >= 0 ? dp[i - k][1] : 0), i - k >= 0 ? dp[i - k][2] : 0);
            if(dp[i][2] > max) {
                max = dp[i][2];
                maxInd = i;
            }
        }

       
        res[2] = maxInd;
        
        int res1Ind = maxInd - k;
        while(dp[res1Ind][1] == (max - sums[maxInd])) {
            res1Ind--;
        }
        res[1] = res1Ind + 1;
        
        int res0Ind = res[1] - k;
        while(res0Ind >= 0 && dp[res0Ind][0] == (dp[res[1]][1] - sums[res[1]])) {
            res0Ind--;
        }   

        res[0] = res0Ind + 1;
       
       
        return res;
    }
}

public class Solution {

    static public int solution(int[] nums) {
        int answer = -1;
        int sum = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            sum = nums[i] + nums[i + 1] + nums[i + 2];
            int temp = 0;
            for (int j = 2; j < sum; j++) {
                if (sum % j == 0)
                    temp++;
                if (temp > 0)
                    break;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4};
        int result = solution(a);
        System.out.println(result);
    }
}

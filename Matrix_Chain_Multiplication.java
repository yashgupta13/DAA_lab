// Yash Gupta
// 500125397 
class Matrix_Chain_Multiplication {

    
    static int minMultRec(int arr[], int i, int j) {

        // If there is only one matrix
        if (i + 1 == j)
            return 0;

        int res = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; k++) {
            int curr = minMultRec(arr, i, k)
                       + minMultRec(arr, k, j)
                       + arr[i] * arr[k] * arr[j];

            res = Math.min(curr, res);
        }

        // Return minimum count
        return res;
    }

    static int matrixMultiplication(int arr[]) {
        
        int n = arr.length;
        return minMultRec(arr, 0, n - 1);
    }

    public static void main(String[] args) {
      
        int arr[] = {40, 20, 30, 10, 30};// Matrix Array has dimension arr[i-1] x arr[i]
        /* For this case we will have -- 40X20 20X30 30X10  10X30 */
        int res = matrixMultiplication(arr);
        System.out.println("The minimum count of scalar multiplication will be"+res);
    }
}
import java.util.Scanner;

public class Main {
    interface MathOperation {
        int sumOfNumbers(int[] numbers);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        MathOperation op = (int[] numbers) -> {
            int sum = 0;
            for (int i=0; i<numbers.length; i++){
                numbers[i] = scan.nextInt();
                if(numbers[i]%4==0 && numbers[i]%10==6) {
                    sum+=numbers[i];
                }
            }
            return sum;
        };

        System.out.println("Enter numbers: ");

        int[] numbers = new int[5];
        int result = op.sumOfNumbers(numbers);

        System.out.println("Result: " + result);
    }
}

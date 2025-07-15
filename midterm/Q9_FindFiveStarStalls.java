import java.util.*;
public class Q9_FindFiveStarStalls {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] tokens = sc.nextLine().split(" ");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Double.parseDouble(tokens[i]) == 5.0) result.add(i);
        }
        if (result.isEmpty()) System.out.println("None");
        else for (int i : result) System.out.print(i + " ");
    }
}
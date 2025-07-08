public class StudentGradeSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 81, 88};

        int sum = 0;
        int max = scores[0], min = scores[0];
        int maxIndex = 0, minIndex = 0;
        int[] gradeCounts = new int[4];
        char[] grades = new char[scores.length];

        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];
            sum += score;
            if (score > max) {
                max = score;
                maxIndex = i;
            }
            if (score < min) {
                min = score;
                minIndex = i;
            }
            if (score >= 90) {
                grades[i] = 'A';
                gradeCounts[0]++;
            } else if (score >= 80) {
                grades[i] = 'B';
                gradeCounts[1]++;
            } else if (score >= 70) {
                grades[i] = 'C';
                gradeCounts[2]++;
            } else {
                grades[i] = 'D';
                gradeCounts[3]++;
            }
        }

        double avg = (double) sum / scores.length;
        int countAboveAvg = 0;
        for (int score : scores) {
            if (score > avg) {
                countAboveAvg++;
            }
        }
        double aboveAvgRatio = (double) countAboveAvg / scores.length * 100;

        System.out.println("=== 學生成績報告系統 ===");
        System.out.println("----------------------------");
        System.out.printf("總分：%d，平均：%.2f\n", sum, avg);
        System.out.printf("最高分：%d（編號 %d），最低分：%d（編號 %d）\n", max, maxIndex, min, minIndex);
        System.out.println("等級統計：A=" + gradeCounts[0] + " B=" + gradeCounts[1] + " C=" + gradeCounts[2] + " D=" + gradeCounts[3]);
        System.out.printf("高於平均分的學生比例：%.2f%%\n", aboveAvgRatio);
        System.out.println("----------------------------");
        System.out.println("編號\t分數\t等級");
        System.out.println("----------------------------");

        for (int i = 0; i < scores.length; i++) {
            System.out.printf("%d\t%d\t%c\n", i, scores[i], grades[i]);
        }

        System.out.println("============================");
    }
}

import java.util.*;
public class PhyscExamSort {
    static class PhyscData {
        String name;
        int height;
        double vision;

        PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        public String toString() {
            return name + " " + height + " " + vision;
        }

        static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();

        private static class HeightOrderComparator implements Comparator<PhyscData> {
            public int compare(PhyscData d1, PhyscData d2) {
                return (d1.vision < d2.vision) ? 1 : (d1.vision > d2.vision) ? -1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        PhyscData[] x = {
                new PhyscData("Alice", 170, 1.0),
                new PhyscData("Bob", 165, 0.9),
                new PhyscData("Charlie", 180, 0.8),
                new PhyscData("David", 175, 1.2),
                new PhyscData("Eve", 160, 0.7),
                new PhyscData("Frank", 185, 1.5),
                new PhyscData("Grace", 155, 0.6),
                new PhyscData("Henry", 170, 1.1)
        };

        Arrays.sort(x, PhyscData.HEIGHT_ORDER);
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
        }
    }
}

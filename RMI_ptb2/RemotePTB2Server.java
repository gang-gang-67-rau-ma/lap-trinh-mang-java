package RMI_ptb2;

public class RemotePTB2Server implements PTB2 {
    @Override
    public String giaiPTB2(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
                return (c == 0) ? "Phương trình vô số nghiệm." : "Phương trình vô nghiệm.";
            } else {
                double x = -c / b;
                return "Phương trình bậc nhất, nghiệm: x = " + x;
            }
        }

        double delta = Math.pow(b, 2) - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có hai nghiệm phân biệt: x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: x1 = x2 = " + x;
        } else {
            return "Phương trình vô nghiệm.";
        }
    }
}

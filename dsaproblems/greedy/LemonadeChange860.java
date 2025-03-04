package dsaproblems.greedy;

public class LemonadeChange860 {
    public boolean lemonadeChange(int[] bills) {
      int fiveDen = 0, tenDen = 0;

        for (int bill : bills) {
            if (bill == 5) {
                fiveDen++;
            } else if (bill == 10) {
                if (fiveDen == 0) {
                    return false;
                }
                fiveDen--;
                tenDen++;
            } else {
                if (fiveDen >= 1 && tenDen >= 1) {
                    fiveDen--;
                    tenDen--;
                } else if (fiveDen >= 3) {
                    fiveDen -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5,5,5,10,20};
        LemonadeChange860 obj = new LemonadeChange860();
        System.out.println(obj.lemonadeChange(bills));
    }
}

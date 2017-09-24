package mycode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner; // Scanner is in the java.util package


public class QiangHongBao {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
			System.out.print("输入总金额数: ");
			BigDecimal total_money = input.nextBigDecimal();
			System.out.print("输入红包数量: ");
			int num = input.nextInt(); 
            List<BigDecimal> moneys = math(total_money, num);
            if (moneys != null) {
                BigDecimal b = new BigDecimal(0);
                for (BigDecimal bigDecimal : moneys) {
                    System.out.print(String.format("%.2f", bigDecimal) + "元    ");
                    b = b.add(bigDecimal);
                }
                System.out.print("   总额：" + b + "元 ");
                System.out.println();
            }
    }

    /**
     * 计算每人获得红包金额;最小每人0.01元
     * @param total_lucky_money 红包总额
     * @param number 人数
     * @return
     */
    public static List<BigDecimal> math(BigDecimal total_lucky_money, int number) {
        if (total_lucky_money.doubleValue() < number * 0.01) {
            return null;
        }
        Random random = new Random();
        int money = total_lucky_money.multiply(BigDecimal.valueOf(100)).intValue();
        double sum = 0;//record the sum of random number
        double[] arrRandom = new double[number];
        List<BigDecimal> arrMoney = new ArrayList<BigDecimal>(number);// record the money which every one could get
        for (int i = 0; i < arrRandom.length; i++) {
            int r = random.nextInt((number) * 99) + 1;
            sum += r;
            arrRandom[i] = r;
        }
        int c = 0;
        for (int i = 0; i < arrRandom.length; i++) {
            Double x = new Double(arrRandom[i] / sum);//caculate the partion of every one
            int m = (int) Math.floor(x * money);	//caculate the money which every one could get
            if (m == 0) {
                m = 1;
            }
            c+=m;
            if (i < arrRandom.length - 1) {	
                arrMoney.add(new BigDecimal(m).divide(new BigDecimal(100)));
            } else {
                arrMoney.add(new BigDecimal(money - c + m).divide(new BigDecimal(100)));// give all of the rest money to the last one
            }
        }
        
        Collections.shuffle(arrMoney); //shuffle the money to make it more random/
        return arrMoney;
    }
}

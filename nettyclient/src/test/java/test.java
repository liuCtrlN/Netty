/**
 * Created with IntelliJ IDEA.
 * Description:测试
 *
 * @author liuxl
 * @date 2018/1/15
 */
public class test {
    public static void main(String[] args) {
        get(3, 5);
    }

    private static void get(int m, int n) {
        int i = Math.abs(m - n);
        //取最小数
        int c = m > n ? n : m;
        int count = 0;
        for (int j = c; j <=(c+i); j++) {
            count+= j;
        }
        int dd = count;
        System.out.println(dd);
        System.out.println(9%2);
    }
}

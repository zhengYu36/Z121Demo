package cn.design.pattern.lsp;

public class Calc extends Calculator{

    public void calc(int a, int b) {
        // a-b = ?
        System.out.println(a + " - " + b + " = " + (a - b));

    }

}

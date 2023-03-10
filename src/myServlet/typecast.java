package myServlet;


public class typecast {
    public static void main(String[] args) {
        int int_number1=1;
        Integer integer_number1=new Integer(int_number1);
        System.out.println("int转成Integer：interger_number1："+integer_number1+",数据类型："+integer_number1.getClass().toString());

        Integer integer_number2=new Integer(2);
        System.out.println("Integer转成int：int_number2："+integer_number2.intValue());

        String str_number1=String.valueOf(int_number1);
        System.out.println("int转成String：int_number1："+int_number1+",数据类型："+str_number1.getClass().toString());

        int int_number2=Integer.parseInt(str_number1);
        System.out.println("String转成int：int_number2："+int_number2);

        String str_number2=String.valueOf(integer_number2);
        System.out.println("Integer转成String：str_number2："+str_number2+",数据类型："+str_number2.getClass().toString());

        Integer integer_number3=new Integer(Integer.parseInt(str_number2));
        System.out.println("String转成Integer：integer_number3："+integer_number3+",数据类型："+integer_number3.getClass().toString());
    }

}

package DesignPattern;

public class Singledemo {
    public static void main(String[] args) {
        Lazy instance=Lazy.getInstance();
        instance.show();
    }
}
class Lazy{
    private static Lazy instance;
    private Lazy(){}
    public static synchronized Lazy getInstance(){
        if(instance==null){
            instance=new Lazy();
        }
        return instance;
    }
    public void show(){
        System.out.println("这是懒汉单例线程安全,只有一个实例");
    }
}

package DesignPattern;

public class AreaFactory {
    public static void main(String[] args) {
        Circle c=new Circle(3);
        Rect r=new Rect(3,4);
        getV v1=new getV(c,2);
        getV v2=new getV(r,2);
        v1.getV();
        v2.getV();
    }
}
interface Factory{
    public abstract double getS();
}
class Circle implements Factory{
    double r;
    public Circle(double r){
        this.r=r;
    }
    public double getS(){
        return 3.14*r*r;
    }
}
class Rect implements Factory{
    double a;
    double b;
    public Rect(double a,double b){
        this.a=a;
        this.b=b;
    }
    public double getS(){
        return a*b;
    }
}
class getV{
    Factory b;
    double height;
    public getV(Factory b,double h){
        this.b=b;
        this.height=h;
    }
    public void getV(){
        System.out.println("体积是"+b.getS()*height);
    }
}

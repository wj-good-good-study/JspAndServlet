package DesignPattern;

public class Templatedemo {
    public static void main(String[] args) {
        DayPlan wang=new Mr_wang();
        wang.Start();
        System.out.println("--------");
        DayPlan he=new Ms_He();
        he.Start();
    }
}
abstract class DayPlan{
    abstract void onMorning();
    abstract void onAfternoon();
    abstract void onNight();
    public final void Start(){
        onMorning();
        onAfternoon();
        onNight();
    }
}
class Mr_wang extends DayPlan{
    @Override
    void onMorning() {
        System.out.println("我上午睡懒觉");
    }

    @Override
    void onAfternoon() {
        System.out.println("我下午睡午觉");
    }

    @Override
    void onNight() {
        System.out.println("我晚上还睡觉");
    }
}
class Ms_He extends DayPlan{
    @Override
    void onMorning() {
        System.out.println("我上午学习");
    }
    void onAfternoon(){
        System.out.println("我下午学习");
    }

    @Override
    void onNight() {
        System.out.println("我晚上还学习");
    }
}
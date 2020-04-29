import java.util.Random;

abstract public class GachaBasic {
    protected double rate=0.02;//出率
    protected int gachaTimes=0;//已经抽的次数
    protected int gachaNoEliteTimes=0;//蓝天白云的次数
    protected double lastResult=-1;//最近的结果
    protected int hasGetUp1=0;
    protected int hasGetUp2=0;
    protected int hasGetNoUp=0;
    protected boolean getUp1(){return false;};
    protected boolean getUp2(){return false;}
    protected boolean getNotUp(){return false;}

    public int getHasGetUp1() {
        return hasGetUp1;
    }
    public int getHasGetUp2(){
        return hasGetUp2;
    }
    public int getHasGetNoUp(){
        return hasGetNoUp;
    }

    protected boolean get6star(){return getUp1()||getUp2()||getNotUp();}

    protected double getRandom()
    {
        Random r=new Random();
        return r.nextDouble();
    }

    public void reset()
    {
        rate=0.02;
        gachaTimes=0;
        lastResult=-1;
        gachaNoEliteTimes=0;
        hasGetUp1=0;
        hasGetUp2=0;
        hasGetNoUp=0;
    }

    protected void gachaOnce()
    {
        lastResult=getRandom();
        gachaTimes++;
        gachaNoEliteTimes++;
        if(getUp1())
            hasGetUp1++;
        if(getUp2())
            hasGetUp2++;
        if(getNotUp())
            hasGetNoUp++;
        if(get6star())
            gachaNoEliteTimes=0;
        else if(gachaNoEliteTimes>50)
            rate=(gachaTimes-50)*0.02+0.02;
        //99次必出
    }

    //单捞期望 分布
    //双捞期望 分布
    //手上有多少 能出的期望
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class GachaException {
    private GachaBasic waifu;
    public GachaException(){waifu=new GachaNormal();}
    public GachaException(GachaBasic g) { waifu=g; }

    private double avg(Vector<Integer> o)
    {
        double sum=0;
        int n=o.size();
        for(int i=0;i<n;i++)
            sum+=o.get(i);
        return sum/(double)o.size();
    }

    private int getMax(Vector<Integer> o)
    {
        int max=0;
        int n=o.size();
        for (Integer integer : o) {
            if (integer > max)
                max = integer;
        }
        return max;
    }

    private Vector<Integer> transfer(Vector<Integer> origin)
    {
        Vector<Integer> v=new Vector<Integer>();

        int max=getMax(origin)+1;
        for(int i=0;i<max;i++)
            v.add(0);
        //v.setSize(getMax(origin)+1);
        int n=origin.size();
        for(int i=0;i<n;i++)
        {
            v.set(origin.get(i),v.get(origin.get(i))+1);
        }
        return v;
    }

    private void writeFile(Vector<Integer> data,BufferedWriter bw) throws IOException
    {
        bw.write("抽卡次数,概率\n");
        for(int i=1;i<data.size();i++)
            bw.write(i+","+(double)data.get(i)/Config.accuracy+"\n");
        bw.close();
    }

    public void exceptionSingle(String fileName) throws IOException
    {
        //单捞 抽卡次数分布
        BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
        Vector<Integer> data=new Vector<Integer>();
        for(int i=0;i<Config.accuracy;i++)
        {
            int times=0;
            while(waifu.getHasGetUp1()==0) {
                times++;
                waifu.gachaOnce();
            }

            waifu.reset();
            data.add(times);
        }
        System.out.println("单捞期望："+avg(data));
        data=transfer(data);
        writeFile(data,bw);
    }

    public void exceptionDouble(String fileName) throws IOException
    {
        //双捞 抽卡次数分布
        BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
        Vector<Integer> data=new Vector<Integer>();
        for(int i=0;i<Config.accuracy;i++)
        {
            int times=0;
            while(waifu.getHasGetUp1()==0 || waifu.getHasGetUp2()==0) {
                times++;
                waifu.gachaOnce();
                //System.out.println("\t"+times);
            }
            waifu.reset();
            data.add(times);
            //System.out.println(i);
        }
        System.out.println("双捞期望："+avg(data));
        data=transfer(data);
        writeFile(data,bw);
    }

    public void exceptionHold(int holdCell,String fileName) throws IOException
    {
        //单出概率和双出概率
        BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
        bw.write("抽卡次数,单捞出率,双捞出率\n");


        for(int hold=1;hold<=holdCell;hold++) {
            int u1=0,uall=0;
            for (int j = 0; j < Config.accuracy; j++)
            {
                for (int i = 0; i < hold; i++)
                    waifu.gachaOnce();
                if (waifu.getHasGetUp1() > 0)
                    u1++;
                if (waifu.getHasGetUp1() > 0 && waifu.getHasGetUp2() > 0)
                    uall++;
                waifu.reset();
            }

            //System.out.println(hold);
            double rate1, rateall;
            rate1 = (double) u1 / (double) (Config.accuracy);
            rateall = (double) uall / (double) (Config.accuracy);
            bw.write(hold + "," + rate1 + "," + rateall + "\n");
        }
        bw.close();
    }
}

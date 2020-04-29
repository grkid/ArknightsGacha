import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class GachaException {
    private GachaBasic waifu;
    public GachaException(){waifu=new GachaNormal();}
    public GachaException(GachaBasic g) { waifu=g; }

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
        v.setSize(getMax(origin)+1);
        int n=origin.size();
        for(int i=0;i<n;i++)
        {
            v.set(origin.get(i),v.get(i)+1);
        }
        return v;
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
        data=transfer(data);
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
            }
            waifu.reset();
            data.add(times);
        }
        data=transfer(data);
    }

    public void exceptionHold(int hold,String fileName) throws IOException
    {
        //单出概率和双出概率
        BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));

    }
}

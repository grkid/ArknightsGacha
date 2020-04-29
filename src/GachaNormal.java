public class GachaNormal extends GachaBasic {
    @Override
    protected boolean getUp1()
    {
        return (lastResult>=rate*0)&&(lastResult<rate*0.25);
    }

    @Override
    protected boolean getUp2()
    {
        return (lastResult>=rate*0.25)&&(lastResult<rate*0.5);
    }

    @Override
    protected boolean getNotUp()
    {
        return (lastResult>=rate*0.5)&&(lastResult<rate*1.0);
    }
}

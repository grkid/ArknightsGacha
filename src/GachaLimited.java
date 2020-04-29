public class GachaLimited extends GachaBasic {
    @Override
    protected boolean getUp1()
    {
        return (lastResult>=rate*0)&&(lastResult<rate*0.35);
    }

    @Override
    protected boolean getUp2()
    {
        return (lastResult>=rate*0.35)&&(lastResult<rate*0.7);
    }

    @Override
    protected boolean getNotUp()
    {
        return (lastResult>=rate*0.7)&&(lastResult<rate*1.0);
    }
}

public class GachaNewOperator extends GachaBasic {
    @Override
    protected boolean getUp1()
    {
        return (lastResult>=rate*0)&&(lastResult<rate*0.5);
    }

    @Override
    protected boolean getUp2()
    {
        return false;
    }

    @Override
    protected boolean getNotUp()
    {
        return (lastResult>=rate*0.5)&&(lastResult<rate*1.0);
    }
}

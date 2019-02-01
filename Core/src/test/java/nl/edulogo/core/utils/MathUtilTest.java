package nl.edulogo.core.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class MathUtilTest {
    @Test
    public void getRelativePosition() {
        //Multiples of 90
        var pos0 = MathUtil.getRelativePosition(0, 100);
        PositionAssert.assertPosition(0, 100, pos0);

        var pos90 = MathUtil.getRelativePosition(90, 1234.56789);
        PositionAssert.assertPosition(1234.56789, 0, pos90);

        var pos180 = MathUtil.getRelativePosition(180, 404);
        PositionAssert.assertPosition(0, -404, pos180);

        var pos270 = MathUtil.getRelativePosition(270, -123);
        PositionAssert.assertPosition(123, 0, pos270);

        var pos360 = MathUtil.getRelativePosition(360, 987);
        PositionAssert.assertPosition(0, 987, pos360);

        var pos450 = MathUtil.getRelativePosition(450, -Math.PI);
        PositionAssert.assertPosition(-Math.PI, 0, pos450);

        var pos540 = MathUtil.getRelativePosition(540, Math.E);
        PositionAssert.assertPosition(0, -Math.E, pos540);

        var pos630 = MathUtil.getRelativePosition(630, -13.37);
        PositionAssert.assertPosition(13.37, 0, pos630);

        //Other
        double[] test = new double[90];
        for (int i = 0; i < test.length; i++) {
            if (i % 2 == 0) {
                test[i] = 3.5 * (i + 1);
            } else {
                test[i] = i * 13.37;
            }
        }
        double half = Math.PI / 2;
        for (int i = 0; i < test.length; i += 2) {
            double radian = Math.toRadians(test[i]);
            double radianOpposite = half - radian;
            var x = (Math.sin(radian) * test[i + 1]);
            var y = (Math.sin(radianOpposite) * test[i + 1]);
            var pos = MathUtil.getRelativePosition(test[i], test[i + 1]);
            PositionAssert.assertPosition(x, y, pos);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRelativePositionNegative() {
        MathUtil.getRelativePosition(-140, 666);
    }

    @Test
    public void round() {
        Assert.assertEquals(6, MathUtil.round(5.5555555, 0), 0);
        Assert.assertEquals(5.6, MathUtil.round(5.5555555, 1), 0);
        Assert.assertEquals(5.56, MathUtil.round(5.5555555, 2), 0);
        Assert.assertEquals(5.556, MathUtil.round(5.5555555, 3), 0);
        Assert.assertEquals(5.5556, MathUtil.round(5.5555555, 4), 0);
    }
}
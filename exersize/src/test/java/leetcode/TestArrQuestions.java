package leetcode;

import com.study.dataStructure.leetcode.arr.ArrQuestions;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tiny
 * @date 2021/9/16 17:23
 * @Description:
 */
public class TestArrQuestions {

    @Test
    public void testRemoveDuplicates(){
        int[] nums = new int[]{1,1,2};
        Assert.assertEquals(ArrQuestions.removeDuplicates(nums), 20);
    }
}

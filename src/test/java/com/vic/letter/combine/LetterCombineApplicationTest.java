package com.vic.letter.combine;

import com.vic.letter.combine.utils.LetterCombineUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.Instant;

@SpringBootTest
class LetterCombineApplicationTests {

    @Test
    void testLetterCombine() {
        int[] arrFirst = {3,4};
        String arrFirstCorrectResult = "dg dh di eg eh ei fg fh fi";
        testLetterCombine(arrFirst, arrFirstCorrectResult, 100);

        int[] arrSecond = {5,6,7};
        String arrSecendCorrectResult = "jmp jmq jmr jms jnp jnq jnr jns jop joq jor jos kmp kmq kmr kms knp knq knr kns kop koq kor kos lmp lmq lmr lms lnp lnq lnr lns lop loq lor los";
        testLetterCombine(arrSecond, arrSecendCorrectResult, 100);

        int[] arrThird = {1,2};
        String arrThirdCorrectResult = "a b c";
        testLetterCombine(arrThird, arrThirdCorrectResult, 100);

        int[] arrFourth = {3, 3};
        String arrFourthCorrectResult = "dd de df ed ee ef fd fe ff";
        testLetterCombine(arrFourth, arrFourthCorrectResult, 100);

        //error input
        int[] arrFifth = {13, 3};
        String arrFifthCorrectResult = "";
        testLetterCombine(arrFifth, arrFifthCorrectResult, 100);

        //error input
        int[] arrSixth = {};
        String arrSixthCorrectResult = "";
        testLetterCombine(arrSixth, arrSixthCorrectResult, 100);
    }

    /**
     * @param input 输入的数字数组
     * @param correctResult 正确的输出结果，用于比对工具类函数输出的正确性
     * @param loopTime 执行次数
     */
    void testLetterCombine(int[] input, String correctResult, int loopTime) {
        Assert.notNull(correctResult, String.format("correctResult can't be null"));
        Assert.isTrue(loopTime > 0, "loopTime must greater than 0");

        int correctCount = 0;
        int wrongCount = 0;
        int exceptionCount = 0;
        Instant allTaskStart = Instant.now();
        for (int i = 0; i < loopTime; i++) {
            try {
                Instant start = Instant.now();
                String result = LetterCombineUtil.letterCombint(input);
                Instant end = Instant.now();
                Duration time = Duration.between(start, end);
                long millis = time.toMillis();
                System.out.println(String.format("ccombine result: %s", result));
                System.out.println(String.format("cost time: %s millisecend", millis));
                if (result.equals(correctResult)) {
                    correctCount++;
                } else {
                    wrongCount++;
                }

            } catch (Exception e) {
                System.out.println(String.format("error: %s", e.getMessage()));
                exceptionCount++;
            }
        }
        Instant allTaskEnd = Instant.now();
        Duration allTaskTime = Duration.between(allTaskStart, allTaskEnd);
        long allTaskMillis = allTaskTime.toMillis();
        System.out.println(String.format("all Task cost time: %s millisecend", allTaskMillis));
        System.out.println(String.format("correct: %s, wrong: %s, exception: %s,", correctCount, wrongCount, exceptionCount));
        System.out.println("--------------------------------------------");
    }

}

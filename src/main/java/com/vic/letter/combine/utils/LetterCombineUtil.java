package com.vic.letter.combine.utils;

import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class LetterCombineUtil {

    private final static String EMPTY_STRING = "";

    /**
     * 如果需要支持[0-99]的数字，只需要在此HashMap中添加字母的映射关系就可以了。
     */
    private final static Map<Integer, List<String>> NUMBER_LETTER_MAP = new HashMap<Integer, List<String>>(){
        {
            put(0, Arrays.asList(new String[]{EMPTY_STRING}));
            put(1, Arrays.asList(new String[]{EMPTY_STRING}));
            put(2, Arrays.asList(new String[]{"a","b","c"}));
            put(3, Arrays.asList(new String[]{"d","e","f"}));
            put(4, Arrays.asList(new String[]{"g","h","i"}));
            put(5, Arrays.asList(new String[]{"j","k","l"}));
            put(6, Arrays.asList(new String[]{"m","n","o"}));
            put(7, Arrays.asList(new String[]{"p","q","r","s"}));
            put(8, Arrays.asList(new String[]{"t","u","v"}));
            put(9, Arrays.asList(new String[]{"w","x","y","z"}));
        }
    };

    /**
     * @param input
     * @return
     * @throws Exception
     * 根据0-9的数字数组使用数字对应的字母列表，按数字的先后顺序列出所有的可能的字母组合。0和1不包含任何字母。
     * 2 对应 [a, b, c]
     * 3 对应 [d, e, f]
     * ...
     * 例如输入数组 int[] = {2, 3}
     * 输出 ad ae af bd be bf cd ce cf
     */
    public static String letterCombint(int[] input) throws Exception {
        Assert.notNull(input, "input array can't be null");
        int inputLength = input.length;
        Assert.isTrue(inputLength > 0, "input array length must greater than 0");
        try {
            List<String> letterCombineListResult = printLetter(input, inputLength,0, new StringBuilder(), new ArrayList<String>());
            String result = letterCombineListResult.stream().collect(Collectors.joining(" "));
            return result;
        } catch (IllegalArgumentException ie) {
            throw ie;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new Exception("something went wrong. please contact technical support");
        }
    }

    /**
     * @param input
     * @param inputLength
     * @param index
     * @param stringBuilder
     * @param letterCombineResult
     * @return List<String>
     * 使用尾递归，递归遍历每一个数字对应字母列表。并且使用StringBuilder拼接字符串。
     * 遍历字母列表时需要第次都实例化一个新的StringBuilder, 原因为StringBuilder拼接字符串时是修改旧的字符串对象，并不会产生新的对象。
     * 如果不实例化一个新的StringBuilder，会导致拼接字符串会带上前一个遍历的拼接结果。
     */
    public static List<String> printLetter(int[] input, int inputLength, int index,  StringBuilder stringBuilder, List<String> letterCombineResult) {
        int number = input[index];
        List<String> tempLetterList = NUMBER_LETTER_MAP.get(number);
        Assert.notNull(tempLetterList, String.format("number %s out of range", number));
        for (String letter : tempLetterList) {
            StringBuilder tempStringBuilder = new StringBuilder(stringBuilder);
            tempStringBuilder.append(letter);
            int nextIndex = index + 1;
            if (nextIndex < inputLength) {
                printLetter(input, inputLength, nextIndex, tempStringBuilder, letterCombineResult);
            } else {
                letterCombineResult.add(tempStringBuilder.toString());
            }
        }
        return letterCombineResult;
    }

}

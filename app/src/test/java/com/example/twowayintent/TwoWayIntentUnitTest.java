package com.example.twowayintent;

import org.junit.Test;
import static org.junit.Assert.*;

public class TwoWayIntentUnitTest {

    @Test
    public void testCalculate_add() {
        // 교재 예제 (p.38 그림 10-10) 55 + 44 = 99
        assertEquals(99, SecondActivity.calculate("+", 55, 44));
        assertEquals(105, SecondActivity.calculate("+", 100, 5));
        assertEquals(10, SecondActivity.calculate("+", -10, 20));
        assertEquals(0, SecondActivity.calculate("+", 0, 0));
        assertEquals(99, SecondActivity.calculateHap(55, 44));
    }

    @Test
    public void testCalculate_subtract() {
        // 교재 [직접 풀어보기 10-3] (p.42 그림 10-11) 100 - 5 = 95
        assertEquals(95, SecondActivity.calculate("-", 100, 5));
        assertEquals(-95, SecondActivity.calculate("-", 5, 100));
        assertEquals(15, SecondActivity.calculate("-", 10, -5));
        assertEquals(0, SecondActivity.calculate("-", 7, 7));
    }

    @Test
    public void testCalculate_multiply() {
        assertEquals(500, SecondActivity.calculate("*", 100, 5));
        assertEquals(0, SecondActivity.calculate("*", 100, 0));
        assertEquals(-20, SecondActivity.calculate("*", 4, -5));
        assertEquals(25, SecondActivity.calculate("*", -5, -5));
    }

    @Test
    public void testCalculate_divide() {
        assertEquals(20, SecondActivity.calculate("/", 100, 5));
        assertEquals(4, SecondActivity.calculate("/", 9, 2));
        // 0으로 나누기 예외 방지 (0 반환)
        assertEquals(0, SecondActivity.calculate("/", 100, 0));
    }

    @Test
    public void testCalculate_operatorFormats() {
        // 기호, 한글, 영문 연산자 지원 확인
        assertEquals(95, SecondActivity.calculate("-", 100, 5));
        assertEquals(95, SecondActivity.calculate("빼기", 100, 5));
        assertEquals(95, SecondActivity.calculate("SUB", 100, 5));

        assertEquals(105, SecondActivity.calculate("+", 100, 5));
        assertEquals(105, SecondActivity.calculate("더하기", 100, 5));
        assertEquals(105, SecondActivity.calculate("ADD", 100, 5));

        assertEquals(500, SecondActivity.calculate("*", 100, 5));
        assertEquals(500, SecondActivity.calculate("곱하기", 100, 5));
        assertEquals(500, SecondActivity.calculate("MUL", 100, 5));

        assertEquals(20, SecondActivity.calculate("/", 100, 5));
        assertEquals(20, SecondActivity.calculate("나누기", 100, 5));
        assertEquals(20, SecondActivity.calculate("DIV", 100, 5));
    }

    @Test
    public void testFormatResultButtonText() {
        // 두 번째 인텐트(SecondActivity)의 버튼 텍스트 표시 포맷 검증
        assertEquals("결과 : 95", SecondActivity.formatResultButtonText(95));
        assertEquals("결과 : 0", SecondActivity.formatResultButtonText(0));
        assertEquals("결과 : -10", SecondActivity.formatResultButtonText(-10));
    }

    @Test
    public void testParseNumber_validNumbers() {
        assertEquals(Integer.valueOf(100), MainActivity.parseNumber("100"));
        assertEquals(Integer.valueOf(5), MainActivity.parseNumber("5"));
        assertEquals(Integer.valueOf(-10), MainActivity.parseNumber("-10"));
        assertEquals(Integer.valueOf(0), MainActivity.parseNumber("0"));
        assertEquals(Integer.valueOf(95), MainActivity.parseNumber("  95  "));
    }

    @Test
    public void testParseNumber_invalidOrEmptyInput() {
        // 비어있거나 잘못된 입력값 처리 방어 로직 검증
        assertNull(MainActivity.parseNumber(""));
        assertNull(MainActivity.parseNumber("   "));
        assertNull(MainActivity.parseNumber(null));
        assertNull(MainActivity.parseNumber("abc"));
        assertNull(MainActivity.parseNumber("12.34"));
    }

    @Test
    public void testIntentExtraKeyConstants() {
        assertEquals("Num1", MainActivity.EXTRA_NUM1);
        assertEquals("Num2", MainActivity.EXTRA_NUM2);
        assertEquals("Operator", MainActivity.EXTRA_OPERATOR);
        assertEquals("Result", MainActivity.EXTRA_RESULT);
        assertEquals("Hap", MainActivity.EXTRA_HAP);
    }
}

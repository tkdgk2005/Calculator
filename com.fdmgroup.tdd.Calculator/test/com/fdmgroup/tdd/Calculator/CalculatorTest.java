package com.fdmgroup.tdd.Calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	Calculator calculator;
	/**
	 * Test for add()
	 */
	@BeforeEach
	void setUp() {
		calculator = new Calculator();
	}
	
	// addition
	@Test
	void test_addition_2_plus_3_equals_5() {

		double result = calculator.evaluate("3+5");	
		assertEquals(8.0, result);
	}
	
	@Test
	void test_addition_2plus_negative_3_equals_negative_1() {

		double result = calculator.evaluate("2+-3");		
		assertEquals(-1.0, result);
	}
	
	@Test
	void test_addition_negative2_plus_3_equals_1() {

		double result = calculator.evaluate("-2+3");	
		assertEquals(1.0, result);
	}
	
	@Test
	void test_addition_negative2_plus_nagative_3_equals_negative_5() {

		double result = calculator.evaluate("-2+-3");	
		assertEquals(-5.0, result);
	}
	@Test
	void test_addition_with_white_space() {

		double result = calculator.evaluate("2 + -3 ");		
		assertEquals(-1.0, result);
	}
	@Test
	void test_addition_with_big_numbers() {

		double result = calculator.evaluate("12345 + 67891");		
		assertEquals(80236.0, result);
	}
	@Test
	void test_addition_many_numbers() {

		double result = calculator.evaluate("1+2+3+4+5+6+7+8+9+10+11");		
		assertEquals(66.0, result);
	}
	@Test
	void test_addition_decimal() {

		double result = calculator.evaluate("0.5+0.5");		
		assertEquals(1.0, result);
	}
	
	//substract
	@Test
	void test_subtract_2_minus_3_equals_negative_1() {

		double result = calculator.evaluate("2-3");		
		assertEquals(-1.0, result);
	}
	@Test
	void test_subtract_2_minus_negative_3_equals_5() {

		double result = calculator.evaluate("2--3");		
		assertEquals(5.0, result);
	}
	
	@Test
	void test_subtract_negative2_minus_3_equals_negative_5() {

		double result = calculator.evaluate("-7-5");		
		assertEquals(-12.0, result);
	}
	
	@Test
	void test_subtract_negative2_minus_negative_3_equals_1() {

		double result = calculator.evaluate("-2--3");		
		assertEquals(1.0, result);
	}
	@Test
	void test_subtract_with_white_spcae() {

		double result = calculator.evaluate(" -2- -3");		
		assertEquals(1.0, result);
	}
	@Test
	void test_subtract_many_numbers() {

		double result = calculator.evaluate("1-2--4+-8-9-4--5-8");		
		assertEquals(-21.0, result);
	}
	@Test
	void test_subtract_decimal() {

		double result = calculator.evaluate("1.567-0.546");		
		assertEquals(1.021, result);
	}
	
	//multiply
	
	@Test
	void test_mulitiplication_2_multiply_3_equals_6() {

		double result = calculator.evaluate("2*3");	
		assertEquals(6.0, result);
	}
	@Test
	void test_mulitiplication_2_multiply_negative_3_equals_negative_6() {

		double result = calculator.evaluate("2*-3");		
		assertEquals(-6.0, result);
	}
	@Test
	void test_mulitiplication_negative_2_multiply_3_equals_negative_6() {

		double result = calculator.evaluate("-2*3");		
		assertEquals(-6.0, result);
	}
	@Test
	void test_mulitiplication_negative_2_multiply_negative_3_equals_6() {

		double result = calculator.evaluate("-2*-3");		
		assertEquals(6.0, result);
	}
	@Test
	void test_mulitiplication_three_numbers() {

		double result = calculator.evaluate("-2*-3*-9");		
		assertEquals(-54.0, result);
	}
	@Test
	void test_mulitiplication_four_numbers() {

		double result = calculator.evaluate("8*3*-3*-9");		
		assertEquals(648.0, result);
	}
	@Test
	void test_mulitiplication_big_numbers() {

		double result = calculator.evaluate("12345*67891");		
		assertEquals(838114395, result);
	}
	
	//divided
	
	@Test
	void test_division_2_divided_by_3() {

		double result = calculator.evaluate("2/3");		
		assertEquals(0.66666666666, result, 0.001);
	}
	@Test
	void test_division_2_divided_by_negative_3() {

		double result = calculator.evaluate("2/-3");		
		assertEquals(-0.66666666666, result, 0.001);
	}
	@Test
	void test_division_negative_2_divided_by_3() {

		double result = calculator.evaluate("-2/3");		
		assertEquals(-0.66666666666, result, 0.001);
	}
	@Test
	void test_division_negative_2_divided_by_negative_3() {

		double result = calculator.evaluate("-2/-3");		
		assertEquals(0.66666666666, result, 0.001);
	}
	@Test
	void test_division_three_numbers() {

		double result = calculator.evaluate("-2/-3/9");		
		assertEquals(0.07407407407, result, 0.001);
	}
	@Test
	void test_division_four_numbers() {

		double result = calculator.evaluate("2/3/9/2");		
		assertEquals(0.03703703703, result, 0.001);
	}
	@Test
	void test_division_big_numbers() {

		double result = calculator.evaluate("98765/43219");		
		assertEquals(2.28522177746, result, 0.001);
	}
	
	
	//complex expression without brackets
	@Test
	void test_complex_2_plus_3_multiply_5() {

		double result = calculator.evaluate("2+3*5");		
		assertEquals(17, result, 0.001);
	}
	@Test
	void test_complex_negative_8_divided_by_3_plus_5() {

		double result = calculator.evaluate("-8/3+5");		
		assertEquals(2.33333333333, result, 0.001);
	}
	@Test
	void test_complex_with_four_operations() {

		double result = calculator.evaluate("62+38*54-7/3");		
		assertEquals(2111.66666667, result, 0.001);
	}
	@Test
	void test_complex_all_operation() {

		double result = calculator.evaluate("7/-3-5*9-2+3");		
		assertEquals(-46.3333333333, result, 0.001);
	}
	@Test
	void test_complex_with_whitespace() {

		double result = calculator.evaluate("-8 /3+ 5");		
		assertEquals(2.33333333333, result, 0.001);
	}
	
	//brackets
	@Test
	void test_brakets_with_whitespace() {

		double result = calculator.evaluate(" (2+3 ) * 7");		
		assertEquals(35.0, result, 0.001);
	}
	
	@Test
	void test_brakets_2_plus_7_minus_3() {

		double result = calculator.evaluate("(2+7)-3");		
		assertEquals(6.0, result, 0.001);
	}
	
	@Test
	void test_brakets_2_plus_3_multiply_7() {

		double result = calculator.evaluate("(2+3)*7");		
		assertEquals(35.0, result, 0.001);
	}

	@Test
	void test_brakets_8_divided_by_3_minus_4() {

		double result = calculator.evaluate("8/(3-4)");		
		assertEquals(-8.0, result, 0.001);
	}
	@Test
	void test_brakets_plus_bracket() {

		double result = calculator.evaluate("(2*3)+(5-3)");		
		assertEquals(8.0, result, 0.001);
	}
	@Test
	void test_brakets_minus_bracket() {

		double result = calculator.evaluate("(15/3)-(5+12)");		
		assertEquals(-12.0, result, 0.001);
	}
	@Test
	void test_brakets_multiply_bracket() {

		double result = calculator.evaluate("(15-3)*(15+4)");		
		assertEquals(228.0, result, 0.001);
	}
	@Test
	void test_brakets_divided_b7_bracket() {

		double result = calculator.evaluate("(15-3)/(15+4)");		
		assertEquals(0.63157894736, result, 0.001);
	}
	@Test
	void test_brakets_two_brakcets() {

		double result = calculator.evaluate("(2+3)*7-(2-9)/8");		
		assertEquals(35.875, result, 0.001);
	}
	@Test
	void test_brakets_three_brackets() {

		double result = calculator.evaluate("(-8-9)*7-(2*12-2)/(2+3)");		
		assertEquals(-123.4, result, 0.001);
	}
	@Test
	void test_brakets_in_brackets() {

		double result = calculator.evaluate("(8+(2-3)+9*2)-3");		
		assertEquals(22, result, 0.001);
	}
	@Test
	void test_brakets_in_brackets_2() {

		double result = calculator.evaluate("7*5-58+(9/7+(4-9))");		
		assertEquals(-26.7142857143, result, 0.001);
	}
	@Test
	void test_brakets_in_brackets_3() {

		double result = calculator.evaluate("(7+((8-2)*3)-2)/2+10*4");		
		assertEquals(51.5, result, 0.001);
	}

	//exponents
	@Test
	void test_expoenets_2_3() {

		double result = calculator.evaluate("2^3");		
		assertEquals(8.0, result, 0.001);
	}
	@Test
	void test_expoenets_2_10() {

		double result = calculator.evaluate("2^10");		
		assertEquals(1024.0, result, 0.001);
	}
	@Test
	void test_expoenets_5_0() {

		double result = calculator.evaluate("5^0");		
		assertEquals(1.0, result, 0.001);
	}
	@Test
	void test_expoenets_5_1() {

		double result = calculator.evaluate("5^1");		
		assertEquals(5.0, result, 0.001);
	}
	
	@Test
	void test_expoenets_2_negative_3() {

		double result = calculator.evaluate("2^-3");		
		assertEquals(0.125, result, 0.001);
	}
	@Test
	void test_expoenets_negative_2_3() {

		double result = calculator.evaluate("-2^3");		
		assertEquals(-8, result, 0.001);
	}
	@Test
	void test_expoenets_negative_2_negativ_3() {

		double result = calculator.evaluate("-2^-3");		
		assertEquals(-0.125, result, 0.001);
	}
	@Test
	void test_expoenets_negative_2_in_brakcetst_power_of_8() {

		double result = calculator.evaluate("-(2)^8");		
		assertEquals(-256.0, result, 0.001);
	}
	@Test
	void test_expoenets_negative_2_the_power_of_8() {

		double result = calculator.evaluate("-2^8");		
		assertEquals(-256.0, result, 0.001);
	}
	@Test
	void test_expoenets_20_minus_negative_2_power_of_4() {

		double result = calculator.evaluate("20-2^4");		
		assertEquals(4.0, result, 0.001);
	}
	@Test
	void test_expoenets_with_addition() {

		double result = calculator.evaluate("25+3^3");		
		assertEquals(52.0, result, 0.001);
	}
	@Test
	void test_expoenets_two_addition_() {

		double result = calculator.evaluate("7^2+2^5");		
		assertEquals(81.0, result, 0.001);
	}
	@Test
	void test_expoenets_with_subtract() {

		double result = calculator.evaluate("2^-3-3");		
		assertEquals(-2.875, result, 0.001);
	}
	
	@Test
	void test_expoenets_with_multiplication() {

		double result = calculator.evaluate("3^3*9");		
		assertEquals(243.0, result, 0.001);
	}
	@Test
	void test_expoenets_with_division() {

		double result = calculator.evaluate("9/2^2");		
		assertEquals(2.25, result, 0.001);
	}
	
	@Test
	void test_expoenets_with_brackets() {

		double result = calculator.evaluate("3^3+25*(5-4)");		
		assertEquals(52.0, result, 0.001);
	}
	@Test
	void test_expoenets_in_brackets() {

		double result = calculator.evaluate("(3^-3*25)-(9-4)");		
		assertEquals(-4.07407407407, result, 0.001);
	}
	@Test
	void test_expoenets_with_brackets_in_brackets() {

		double result = calculator.evaluate("(8+(3^4-37)-7*7)-4");		
		assertEquals(-1.0, result, 0.001);
	}
	@Test
	void test_expoenets_with_complex_expresion() {

		double result = calculator.evaluate("(2^5-2)-(-7*7)/3-4");		
		assertEquals(42.3333333333, result, 0.001);
	}
	@Test
	void test_expoenets_brackets_power_of_2() {

		double result = calculator.evaluate("(3+5)^2");		
		assertEquals(64.0, result, 0.001);
	}
	@Test
	void test_expoenets_3_power_of_brackets() {

		double result = calculator.evaluate("3^(4*2)");		
		assertEquals(6561.0, result, 0.001);
	}
	@Test
	void test_expoenets_negative_4_power_of_brackets() {

		double result = calculator.evaluate("(-4)^(4*2)");		
		assertEquals(65536.0, result, 0.001);
	}



	



}

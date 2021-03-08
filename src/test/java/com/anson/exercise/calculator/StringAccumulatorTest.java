package com.anson.exercise.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author Anson
 */
public class StringAccumulatorTest {

	@Test
	public void testReq1Case1() {
		try {
			assertEquals(0, StringAccumulator.add(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq1Case2() {
		try {
			assertEquals(1, StringAccumulator.add("1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq1Case3() {
		try {
			assertEquals(3, StringAccumulator.add("1,2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq3Case1() {
		try {
			assertEquals(6, StringAccumulator.add("1\n2,3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq3Case2() {
		try {
			assertEquals(6, StringAccumulator.add("1\n2,3,"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq4Case1() {
		try {
			assertEquals(118, StringAccumulator.add("//***|$$$|##|()|;\n11;32;8;67"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq5Case1() {
		try {
			StringAccumulator.add("//***|$$$|##|()|;\n-11;-32;-8;67");
			fail();
		} catch (Exception e) {
			assertEquals("negatives not allowed [-11, -32, -8].", e.getMessage());
		}
	}

	@Test
	public void testReq6Case1() {
		try {
			assertEquals(286, StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$8##67()1021()90;78"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq7Case1() {
		try {
			assertEquals(6, StringAccumulator.add("//***\n1***2***3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReq8Case1() {
		try {
			assertEquals(286, StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$8##67()90;78"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	@Test
	public void testReq8Case2() {
		try {
			assertEquals(286, StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$$$$8####67()1021()90;78"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

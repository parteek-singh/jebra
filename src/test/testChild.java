package test;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import example.ParserTest;

public class testChild {

	@InjectMocks
	@Spy
	ParserTest mockParser;
	
	@Test
	public void test1() {
		
		mockParser.helloTest();
	}
}

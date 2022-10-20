package com.ko.home.aop.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferTest {

	@Autowired
	private Transeport transeport;
	
	@Autowired
	private Card card;
	
	@Test
	void test() {
		
		transeport.takeBus();
		transeport.takeSubway();
		transeport.airPlane();
		transeport.getTaxi();
		
	}

}

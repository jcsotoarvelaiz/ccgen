package com.jct.test.ccgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.jct.test.ccgen.pojo.Card;

public class JSONGenerator implements Runnable{
	
	private int cardsPerThread;
	private static final Logger LOGGER = LoggerFactory.getLogger(JSONGenerator.class);
	private Faker faker;
	private String bin;
	private String thread;
	
	public JSONGenerator(int cardsPerThread, String bin,String thread,Faker faker){
		this.thread=thread;
		this.faker=faker;
		this.bin=bin;
		this.cardsPerThread=cardsPerThread;
	}
	@Override
	public void run() {
		LOGGER.info("Starting ----------------------------------->");
		LOGGER.info("Faker ----------------------------------->"+faker);
		Card card=new Card();
		for (int i = 0; i < cardsPerThread; i++) {
			card.setName(faker.name().firstName()+" "+faker.name().lastName());
			card.setNumber(bin+thread+String.format("%011d",i));
		    LOGGER.info(card.toString());
		}
		
	}
	
}
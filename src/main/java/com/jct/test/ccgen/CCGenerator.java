package com.jct.test.ccgen;

import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class CCGenerator implements CommandLineRunner {
	
	@Value("${ccgen.cardsPerThread}")
	private int cardsPerThread;
	
	@Value("${ccgen.bin}")
	private String bin;

	@Autowired
	Executor executor;
	@Autowired
	CCGenFaker cCGenFaker;

	public CCGenerator() {

	}

	@Override
	public void run(String... arg0) throws Exception {
		@SuppressWarnings("rawtypes")
		Vector<CompletableFuture> threads=new Vector<CompletableFuture> ();
		for (int i = 0; i < 10; i++) {
			threads.add(CompletableFuture.runAsync(new JSONGenerator(cardsPerThread,bin,Integer.toString(i), cCGenFaker), executor));
			threads.get(i).join();
		}
        System.exit(0);
	}
}

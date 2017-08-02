package com.kandasm.web.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class ItemsController {
	
	@RequestMapping("/demo")
    public String test() {
        return "demo success";
    }
	
	@RequestMapping("/load")
    public String load(int duration, double load, int thread) {
	    int numCore = Runtime.getRuntime().availableProcessors();
        int numThreadsPerCore = thread;
        final long dur = duration * 000;
        for (int thr = 0; thr < numCore * numThreadsPerCore; thr++) {
            new BusyThread("Thread" + thr, load, dur).start();
        }
        
        return "load success";
    }
	
	private static class BusyThread extends Thread {
        private double load;
        private long duration;

        /**
         * Constructor which creates the thread
         * @param name Name of this thread
         * @param load Load % that this thread should generate
         * @param duration Duration that this thread should generate the load for
         */
        public BusyThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        /**
         * Generates the load when run
         */
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                // Loop for the given duration
                while (System.currentTimeMillis() - startTime < duration) {
                    // Every 100ms, sleep for the percentage of unladen time
                    if (System.currentTimeMillis() % 100 == 0) {
                        Thread.sleep((long) Math.floor((1 - load) * 100));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

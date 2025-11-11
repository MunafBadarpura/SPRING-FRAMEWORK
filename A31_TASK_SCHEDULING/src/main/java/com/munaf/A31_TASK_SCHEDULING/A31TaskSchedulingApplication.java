package com.munaf.A31_TASK_SCHEDULING;

import com.munaf.A31_TASK_SCHEDULING.profiles.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.*;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class A31TaskSchedulingApplication implements CommandLineRunner {

	@Autowired
	private DataService dataService;

	public static void main(String[] args) {
		SpringApplication.run(A31TaskSchedulingApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(dataService.getData());
	}

//	 @Override
//	 public void run(String... args) throws Exception {
//		// Homework 1
//		 System.out.println("Application Started with Thread Name : " + Thread.currentThread().getName());
//
//
//		 ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
//
//		 for (int i = 0; i < 20; i++) {
//			 int finalI = i;
//			 threadPoolExecutor.submit(new Runnable() {
//				 @Override
//				 public void run() {
//					 System.out.println("Task " + finalI + " Started by Thread Name : " + Thread.currentThread().getName());
//					 try {
//						 Thread.sleep(2000);
//					 } catch (InterruptedException e) {
//						 throw new RuntimeException(e);
//					 }
//					 System.out.println("Task " + finalI + " Ended by Thread Name : " + Thread.currentThread().getName());
//				 }
//			 });
//		 }




//		 System.out.println("Application Started with Thread Name : " + Thread.currentThread().getName());
//
//		 ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
//				 7, 2, TimeUnit.SECONDS,
//				 new LinkedBlockingQueue<>());
//
//		 // Core Pool Size = 4 => Minimum number of threads always kept alive in the pool, even if they are idle.
//		 // Max Pool Size = 7 => Maximum number of threads that can be created in the pool if more tasks are submitted.
//		 // Keep Alive Time = 2 seconds => This extra 7 threads will be killed after being idle for 2 seconds.
//		 // Unbounded Queue => Tasks are stored in a queue until a thread is available to execute them.
//
//
//		 threadPoolExecutor.submit(new Runnable() {
//			 @Override
//			 public void run() {
//				 System.out.println("Task 1(4Seconds) Started by Thread Name : " + Thread.currentThread().getName());
//				 try {
//					 Thread.sleep(4000);
//				 } catch (InterruptedException e) {
//					 throw new RuntimeException(e);
//				 }
//				 System.out.println("Task 1(4Seconds) Ended by Thread Name : " + Thread.currentThread().getName());
//
//			 }
//		 });
//
//
//		 threadPoolExecutor.submit(new Runnable() {
//			 @Override
//			 public void run() {
//				 System.out.println("Task 2(2Seconds) Started by Thread Name : " + Thread.currentThread().getName());
//				 try {
//					 Thread.sleep(2000);
//				 } catch (InterruptedException e) {
//					 throw new RuntimeException(e);
//				 }
//				 System.out.println("Task 2(2Seconds) Ended by Thread Name : " + Thread.currentThread().getName());
//			 }
//		 });
//
//
//		 // If you want to get data after task done use Callable instead of Runnable
//		 Future<String> task3Data = threadPoolExecutor.submit(new Callable<>() {
//			 @Override
//			 public String call() throws Exception {
//				 System.out.println("Task 3(4Seconds) Started by Thread Name : " + Thread.currentThread().getName());
//				 try {
//					 Thread.sleep(2000);
//				 } catch (InterruptedException e) {
//					 throw new RuntimeException(e);
//				 }
//				 System.out.println("Task 3(4Seconds) Ended by Thread Name : " + Thread.currentThread().getName());
//
//
//				 return "DATA : This is the result after task 3 is done";
//			 }
//		 });
//
//
//		 System.out.println("Result from Future: " + task3Data.get()); // This get() waits for task to finish
//
//		 System.out.println("Application Ended with Thread Name : " + Thread.currentThread().getName());
//	 }
}



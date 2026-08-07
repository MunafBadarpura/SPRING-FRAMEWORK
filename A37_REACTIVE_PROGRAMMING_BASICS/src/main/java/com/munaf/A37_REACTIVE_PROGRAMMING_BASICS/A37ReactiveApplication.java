package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS;

import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A01_FLUX_INTRO.FluxExample1;
import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A01_FLUX_INTRO.FluxExample2;
import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A01_FLUX_INTRO.FluxExample3;
import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A02_OPERATORS.OperatorExample1;
import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A03_ERROR_HANDELING.ErrorHandlingFlux;
import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A03_ERROR_HANDELING.ErrorHandlingMono;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class A37ReactiveApplication implements CommandLineRunner {

	private final FluxExample1 fluxExample1;
	private final FluxExample2 fluxExample2;
	private final FluxExample3 fluxExample3;

	private final OperatorExample1 operatorExample1;

	private final ErrorHandlingFlux errorHandlingFlux;
	private final ErrorHandlingMono errorHandlingMono;

	private final Homework homework;

	public static void main(String[] args) {
		SpringApplication.run(A37ReactiveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		fluxExample1.learnReactor();
//		fluxExample2.learnReactor();
//		fluxExample3.learnReactor();

//		operatorExample1.learnReactor();

//		errorHandlingFlux.learnReactor();
//		errorHandlingMono.learnReactor();

		homework.learnReactor();
	}
}

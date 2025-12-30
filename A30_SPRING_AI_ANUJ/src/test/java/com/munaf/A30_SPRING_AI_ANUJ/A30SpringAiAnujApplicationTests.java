package com.munaf.A30_SPRING_AI_ANUJ;

import com.munaf.A30_SPRING_AI_ANUJ.services.ChatMemoryService;
import com.munaf.A30_SPRING_AI_ANUJ.services.SimpleChatService;
import com.munaf.A30_SPRING_AI_ANUJ.util.ImportPdfInVector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A30SpringAiAnujApplicationTests {

	@Autowired
	private SimpleChatService simpleChatService;

	@Autowired
	private ImportPdfInVector importPdfInVector;

	@Autowired
	private ChatMemoryService chatMemoryService;

	@Test
	void testSimpleChat() {
		String response = simpleChatService.simpleChat("Hello");
		System.out.println(response);
	}

	@Test
	void loadPdf() {
		boolean success = importPdfInVector.loadPdf();
		System.out.println(success);
	}

	@Test
	void testMostAskedDoubts() {
		String response = simpleChatService.mostAskedDoubts(" I am not able to access the doubt forum and other 4.0-related channels");
		System.out.println(response);
	}

	@Test
	void testChatWithMemory() {
		String response = chatMemoryService.chatWithMemory("My name is Munaf", "1");
		System.out.println(response);
	}

}

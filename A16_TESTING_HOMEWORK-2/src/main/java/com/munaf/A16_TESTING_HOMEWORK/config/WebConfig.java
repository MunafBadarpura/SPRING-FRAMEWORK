package com.munaf.A16_TESTING_HOMEWORK.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // <-- key line
//
//        MappingJackson2HttpMessageConverter converter =
//                new MappingJackson2HttpMessageConverter(objectMapper);
//
//        converters.add(0, converter);
//    }
//}



/*

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.modules(new JavaTimeModule());
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}


* */

/*

🚨 What Spring Expects:
Spring is expecting a plain String response (like "Author Deleted"), because the return type was ResponseEntity<String>.

But you're giving it an object of type ApiResponse<String>.

Now Spring tries to cast your ApiResponse<String> into a String (because the controller said the body is String), and that fails.



❓ So Why No Error?
Because Spring uses MappingJackson2HttpMessageConverter to serialize objects to JSON.

When the request is from a browser or client expecting application/json, Spring does NOT cast the object. It just serializes the returned object (whatever it is) to JSON using Jackson.

As long as the response type can be converted to JSON → Spring doesn’t care about the generic type in ResponseEntity<T>.

⚠️ But Why Did It Fail with String?
Because StringHttpMessageConverter is not the same as MappingJackson2HttpMessageConverter.

If controller returns ResponseEntity<String>
➜ Spring uses StringHttpMessageConverter, expecting raw text (not JSON).

You wrapped it in ApiResponse<String> (i.e. an object)
➜ So Jackson tries to convert the object to JSON
➜ But Spring is still thinking it's just a plain string → Mismatch → ClassCastException
* */
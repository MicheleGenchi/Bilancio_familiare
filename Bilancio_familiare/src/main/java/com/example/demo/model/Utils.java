package com.example.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Utils {

	public static void getHead(Class<?> clazz, List<String> head) {
	AtomicInteger count= new AtomicInteger(1);
	head.add("#");
	
	Arrays.stream(clazz.getDeclaredFields())
		.filter(x -> !(x.getName().equals("serialVersionUID")))
		.forEach(s -> {
			if (Arrays.stream(s.getAnnotations()).allMatch(a -> !(a instanceof JsonIgnore))) {
				count.incrementAndGet();
				head.add(s.getName());
			} 
		});
	}
}

package fr.uvsq.generateurEDT;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TypeEven { 
	CM, 
	TD, 
	TP; 
	
	public static List<String> names() {
		return Stream
				.of(values())
				.map(v->v.name())
				.collect(Collectors.toList());
	}
}
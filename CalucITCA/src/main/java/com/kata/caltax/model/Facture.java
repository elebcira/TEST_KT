package com.kata.caltax.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Facture {
	

	private List<InputPanier> inputPaniers = new ArrayList<>();

	public Facture(InputPanier... inputPaniers) {
		this.inputPaniers.addAll(Arrays.asList(inputPaniers));
	}

	public List<InputPanier> getInputPaniers() {
		return inputPaniers;
	}


	@Override
	public String toString() {
		
		String inout = inputPaniers.stream().findFirst().get().getTotal().intValue()> 0 ?  "OUTPUT " : "INPUT ";

		StringBuilder builder = new StringBuilder("\n\n").append("### ").append(inout).append("\n\n");

		IntStream.range(0, inputPaniers.size()).forEach(index -> {
			
			builder.append("#### ").append(inout).append(index + 1).append("\n\n");

			builder.append(inputPaniers.get(index).toString());

		});

		return builder.toString();
	}

}

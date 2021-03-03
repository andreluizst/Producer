package br.com.alst.kafak.vendaIngressos.model;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VendaSerializer implements Serializer<Venda> {

	@Override
	public byte[] serialize(String topic, Venda venda) {
		// TODO Auto-generated method stub
		try {
			return new ObjectMapper().writeValueAsBytes(venda);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

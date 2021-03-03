package br.com.alst.kafak.vendaIngressos.service;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import br.com.alst.kafak.vendaIngressos.model.Venda;
import br.com.alst.kafak.vendaIngressos.model.VendaSerializer;

public class VendaService {
	
	private static Random random = new Random();
	private static long operacao = 0;
	private static BigDecimal valorIngresso = BigDecimal.valueOf(500);
	
	public static void main(String[] args) {
		int count = 0;
		String topic = "topico-venda-ingressos";
		Venda venda = null;
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendaSerializer.class.getName());
		
		try(KafkaProducer<String, Venda> producer = new KafkaProducer<>(properties)){
			while(count < 30) {
				venda = geraVenda();
				ProducerRecord<String, Venda> record = new ProducerRecord<>(topic, venda);
				producer.send(record);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				count++;
			}
		}
		System.out.println("FIM!");
	}

	private static Venda geraVenda() {
		long cliente = random.nextLong();
		int qtde = random.nextInt(10);
		
		return new Venda(operacao++, cliente, qtde, valorIngresso.multiply(BigDecimal.valueOf(qtde)));
	}
}

package com.example.agent

import java.util

import com.google.common.collect.Lists
import org.apache.flume.agent.embedded.EmbeddedAgent
import org.apache.flume.Event

class Main {
	def main (args: Array[String]) {
		val properties2 = Map(
			"channel.type" -> "s",
			"channel.capacity" -> "200",
			"sinks" -> "sink1",
			"sink1.type" -> "avro",
			"sink1.hostname" -> "127.0.0.1",
			"sink1.port" -> "5564",
			"processor.type" -> "load_balance",
			"source.interceptors" -> "i1",
			"source.interceptors.i1.type" -> "static",
			"source.interceptors.i1.key" -> "key1",
			"source.interceptors.i1.value" -> "value1"
		)

		val properties = new util.HashMap[String, String]();
		properties.put("channel.type", "memory");
		properties.put("channel.capacity", "200");
		properties.put("sinks", "sink1 sink2");
		properties.put("sink1.type", "avro");
		properties.put("sink2.type", "avro");
		properties.put("sink1.hostname", "collector1.apache.org");
		properties.put("sink1.port", "5564");
		properties.put("sink2.hostname", "collector2.apache.org");
		properties.put("sink2.port",  "5565");
		properties.put("processor.type", "load_balance");
		properties.put("source.interceptors", "i1");
		properties.put("source.interceptors.i1.type", "static");
		properties.put("source.interceptors.i1.key", "key1");
		properties.put("source.interceptors.i1.value", "value1");

		val agent = new EmbeddedAgent("myagent")

		agent.configure(properties)
		agent.start()

		val events = new util.ArrayList[String]()

		events.add("aaa")
		events.add("aaa")
		events.add("aaa")
		events.add("aaa")

		agent.putAll(events)

		...

		agent.stop();
	}


}

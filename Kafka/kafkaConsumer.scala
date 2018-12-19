import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer 

object KafkaExample {
  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("group.id", "consumer-tutorial")
    properties.put("key.deserializer", classOf[StringDeserializer])
    properties.put("value.deserializer", classOf[StringDeserializer])

    val kafkaConsumer = new KafkaConsumer[String, String](properties)
    kafkaConsumer.subscribe("firstTopic", "secondTopic")

    while (true) {
      val results = kafkaConsumer.poll(2000).asScala
      for ((topic, data) <- results) {
        // Do stuff
      }
    }
}

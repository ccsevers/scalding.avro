package scalding.avro

import org.apache.avro.Schema
import org.apache.avro.specific.SpecificRecord

import java.nio.ByteBuffer



trait AvroSchemaType[T] {
	val schema: Schema
}

object AvroSchemaType {

	// primitive types
	
	implicit def BooleanSchema = new AvroSchemaType[Boolean] {
		val schema = Schema.create(Schema.Type.BOOLEAN)
	}

	implicit def ByteBufferSchema = new AvroSchemaType[ByteBuffer] {
		val schema = Schema.create(Schema.Type.BYTES)
	}

	implicit def DoubleSchema = new AvroSchemaType[Double] {
		val schema = Schema.create(Schema.Type.DOUBLE)
	}

	implicit def FloatSchema = new AvroSchemaType[Float] {
		val schema = Schema.create(Schema.Type.FLOAT)
	}

	implicit def IntSchema = new AvroSchemaType[Int] {
		val schema = Schema.create(Schema.Type.INT)
	}

	implicit def LongSchema = new AvroSchemaType[Long] {
		val schema = Schema.create(Schema.Type.LONG)
	}

	implicit def StringSchema = new AvroSchemaType[String] {
		val schema = Schema.create(Schema.Type.STRING)
	}

	// collections
	implicit def CollectionSchema[CC[x] <: Iterable[x],T](implicit sch: AvroSchemaType[T]) = new AvroSchemaType[CC[T]] {
		val schema = Schema.createArray(sch.schema)
	}

	implicit def ArraySchema[CC[x] <: Array[x],T](implicit sch: AvroSchemaType[T]) = new AvroSchemaType[CC[T]] {
		val schema = Schema.createArray(sch.schema)
	}

	//maps 
	implicit def MapSchema[CC[String,x] <: Map[String,x],T](implicit sch: AvroSchemaType[T]) = new AvroSchemaType[CC[String,T]] {
		val schema = Schema.createMap(sch.schema)
	}

	// Avro SpecificRecord
	implicit def SpecificRecordSchema[T <: SpecificRecord](implicit mf: Manifest[T]) = new AvroSchemaType[T] {
		val schema = mf.erasure.newInstance.asInstanceOf[SpecificRecord].getSchema
	} 

}
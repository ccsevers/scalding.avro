/*  Copyright 2012 eBay, inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package scalding.avro

import cascading.avro.{PackedAvroScheme, AvroScheme}
import com.twitter.scalding._
import org.apache.avro.Schema
import org.apache.avro.specific.SpecificRecord


trait UnpackedAvroFileScheme extends Source {
  val schema: Option[Schema]

  override def hdfsScheme = HadoopSchemeInstance(new AvroScheme(schema.getOrElse(null)))
}

trait PackedAvroFileScheme[AvroType <: SpecificRecord] extends Source {
  val schema = implicitly[Manifest[AvroType]].erasure.newInstance.asInstanceOf[SpecificRecord].getSchema

  override def hdfsScheme = HadoopSchemeInstance(new PackedAvroScheme[AvroType](schema))
}

object TypedUnpackedAvroSource {
  def apply[TupleType : Manifest : TupleConverter](path: String, schema: Option[Schema] = None) =
    new TypedUnpackedAvroSource[TupleType](Seq(path), schema)
}

case class TypedUnpackedAvroSource[TupleType : Manifest : TupleConverter](paths: Seq[String], override val schema: Option[Schema] = None)
                                             (override val converter: TupleConverter[TupleType])
  extends FixedPathSource(paths: _*)
  with UnpackedAvroFileScheme with Mappable[TupleType]

object UnpackedAvroSource {
  def apply(path: String, schema: Option[Schema] = None) = new UnpackedAvroSource(Seq(path), schema)
}

case class UnpackedAvroSource(p: Seq[String], override val schema: Option[Schema] = None)
  extends FixedPathSource(p: _*) with UnpackedAvroFileScheme

object PackedAvroSource {
  def apply[AvroType <: SpecificRecord](path: String) = new PackedAvroSource[AvroType](Seq(path))
}

case class PackedAvroSource[AvroType <: SpecificRecord](paths: Seq[String])
  extends FixedPathSource(paths: _*) with PackedAvroFileScheme[AvroType]




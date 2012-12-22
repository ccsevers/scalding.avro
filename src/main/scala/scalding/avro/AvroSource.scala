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

import com.twitter.scalding.{Source, FixedPathSource, HadoopSchemeInstance}
import org.apache.avro.Schema
import cascading.avro.AvroScheme
import cascading.scheme.Scheme
import org.apache.hadoop.mapred.{JobConf, OutputCollector, RecordReader}





trait AvroFileScheme extends Source {
  val schema : Schema 
  val unpack : Boolean 
  override def hdfsScheme = HadoopSchemeInstance(new AvroScheme(schema, unpack))
}

case class AvroSource(p: String, override val schema: Schema, override val unpack: Boolean = true)
extends FixedPathSource(p) 
with AvroFileScheme


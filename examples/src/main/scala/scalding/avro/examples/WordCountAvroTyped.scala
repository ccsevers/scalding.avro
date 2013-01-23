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

 package scalding.avro.examples

 import com.twitter.scalding._
 import scalding.avro.PackedAvroSource
 import org.apache.avro.Schema
 import org.apache.avro.generic.GenericData.Record
 import org.apache.hadoop.util.ToolRunner
 import org.apache.hadoop.conf.Configuration

 import edu.berkeley.cs.avro.marker._
 import edu.berkeley.cs.avro.runtime._



 object WordCountAvroTypedJob {
  def main(args: Array[String]) {
    ToolRunner.run(new Configuration, new Tool, args);
  }
}

case class WordCount(var token: String, var count: Long) extends AvroRecord


class WordCountAvroTypedJob(args : Args) extends Job(args) {



  // val text = TextLine( args("input") )
  val typedText : TypedPipe[String] = TypedPipe.from(TextLine( args("input") ))
  val records = typedText
  .flatMap{ line : String => tokenize(line) }
  .map{ token : String => (token, 1L)}
  .group[String, Long]
  .sum
  .map{ case(token, count) => WordCount(token, count) }
  
  records.write(PackedAvroSource[WordCount]( args("output")))

  // Split a piece of text into individual words.
  def tokenize(text : String) : Array[String] = {
    // Lowercase each word and remove punctuation.
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")
  }
}
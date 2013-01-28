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



 object ReadFromAvroJob {
  def main(args: Array[String]) {
    ToolRunner.run(new Configuration, new Tool, args);
  }
}



class ReadFromAvroJob(args : Args) extends Job(args) {




  val typedAvros  = TypedPipe.from[WordCount](PackedAvroSource[WordCount]( args("input") ))
  val records = typedAvros
  .map{ rec : WordCount => (rec.token, rec.count) }
 
  
  records.write(Tsv( args("output")))

}
case class estab(lat:Double, long:Double, name:String, address:String, city:String, state:String)

object mcdo {
  def main(args:Array[String]) = {
    val mcdos = scala.io.Source.fromURL(getClass.getResource("/mcdonalds.csv")).getLines.toList.map {
      line =>
	val strings = line.split(",").take(7).map(s => s.trim().replaceAll("^\"|\"$", ""))
      // if (strings(6).length>2) println("!!!"+line) else print( "");
// -122.171259,47.16815,"McDonalds-Bonney Lake,WA","19205 State Route 410 E [WM], Bonney Lake,WA 98391 , (253) 862-4232"

      estab(strings(0).toDouble, strings(1).toDouble, strings(2)+strings(3), strings(4), strings(5), strings(6))
    }
    println ("Nb of mcdo: " +mcdos.size)
    println ("Nb of cities: " +mcdos.map(_.city).toSet.size)
    println ("City with the most McDo: " +mcdos.groupBy(_.city).map(kv => (kv._1, kv._2.size)).toList.sortBy(_._2).last)
  }
}

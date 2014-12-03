object scrabble {
  val scrabbleENScore:Array[Int] = Array(
    // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y,  z
    1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10)
  
  val scrabbleENDistribution:Array[Int] = Array(
    // a, b, c, d,  e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
    9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1)
  
  
  val scrabbleFRScore:Array[Int] = Array(
    // a,  b, c, d, e, f, g, h, i, j,  k, l, m, n, o, p, q, r, s, t, u, v,  w,  x,  y,  z
    1,  3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10, 10, 10)
  
  val scrabbleFRDistribution:Array[Int] = Array(
    // a, b, c, d,  e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
    9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1)

  def main(args: Array[String]) = {
    val scrabbleWords:Set[String] = scala.io.Source.fromURL(getClass.getResource("/ospd.txt")).getLines.map(_.toLowerCase).toSet
    val shakespeareWords:Set[String] = scala.io.Source.fromURL(getClass.getResource("/words.shakespeare.txt")).getLines.map(_.toLowerCase).toSet

    println("# of words in the Scrabble dictionnary : " + scrabbleWords.size)
    //  number of words used by Shakespeare
    println("# of words used by Shakespeare  : " + shakespeareWords.size)

    // number of words used by Shakespeare and allowed at Scrabble
    println("# number of words used by Shakespeare and allowed at Scrabble = " + (shakespeareWords intersect scrabbleWords).size);

    // words of Shakespeare grouped by their length
    println("Words of Shakespeare grouped by their length = " + wordsByLength(shakespeareWords))


    // words of Shakespeare of 16 letters and more
    System.out.println("Words of Shakespeare of 16 letters and more = " + wordsWithMoreThan(16, shakespeareWords)) ;

    // # of words of Shakespeare grouped by their Scrabble score
    // in descending order
    println("# of words of Shakespeare grouped by their Scrabble score = " + (shakespeareWords intersect scrabbleWords).groupBy(scoreEN(_)).map { kv => (kv._1, kv._2.size) }.toSeq.sortBy(_._1).reverse )

    // words of Shakespeare grouped by their Scrabble score, with a score greater than 28
    // in ascending order
    println("# of words of Shakespeare grouped by their Scrabble score = " + (shakespeareWords intersect scrabbleWords).groupBy(scoreEN(_)).filter(_._1>28).toSeq.sortBy(_._1) )


    println("Can we write buzzards without blanks? " + noBlank("buzzards"))
    println("Can we write whizzings without blanks? " + noBlank("whizzings"))
  }

  def wordsByLength(words:Set[String]):Seq[(Int, Int)]=
    words.groupBy(_.length).map{kv => (kv._1, kv._2.size)}.toSeq.sortBy(_._1)

  def wordsWithMoreThan(n:Int, words:Set[String])=
    words.filter(_.length>=n).groupBy(_.length)

  def scrabbleLetterValueEN (letter:Char):Int =
    scrabbleENScore(letter - 'a')

  def scoreEN (word:String):Int =
    word.toList.map{c:Char=>scrabbleLetterValueEN(c)}.sum 

  def lettersHisto(word:String):Map[Char,Int]=
    word.toList.groupBy(letter=>letter).map(kv=>(kv._1, kv._2.size))

  def noBlank(word:String)=
    lettersHisto(word).forall(kv =>kv._2<=scrabbleENDistribution(kv._1 - 'a') )
	 
}

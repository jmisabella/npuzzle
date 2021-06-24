package models.classes

import scala.annotation.tailrec

case class Board(grid: Seq[Seq[Option[Int]]]) {
  require { // require that board is a perfect square such that width equals height
    grid == Nil || (grid.length == grid.head.length && grid.length == grid.tail.head.length)
  }
  require { // require that board tiles are sequence of 1 until length squared
    val expectedTotal: Int = (1 until grid.length * grid.length).foldLeft(0)(_ + _)
    val actualTotal: Int = grid.flatten.foldLeft(0) { (acc, x) => acc + x.getOrElse(0) }
    actualTotal == expectedTotal
  }
  require { // require that board is "missing" exactly one tile, so that we can slide to play the game
    grid == Nil || grid.flatten.count(_ == None) == 1
  }
  val length: Int = grid.length
  val solution: Seq[Option[Int]] = (1 until length * length).map(Some(_)) ++ Seq(None)

  override def toString(): String = {
    def padLeft(value: String, maxLength: Int, digit: Char): String = {
      value.length match {
        case i if i > maxLength => value // cannot pad because length exceeds max length
        case _ => { // value length is less than or equal to max length, so pad
          val padLength = maxLength - value.length
          (for (_ <- 0 until padLength) yield digit.toString).mkString("") + value
        }
      }
    }
    @tailrec
    def len(x: Int, i: Int = 1): Int = {
      if (x < 10) i
      else len(x / 10, i + 1)
    }
    val output = new StringBuilder()
    val newline = "\r\n"
    val max = grid.length * grid.length - 1 // highest tile value
    for(row <- grid) row match {
      case Nil => output.append(newline)
      case _ => output.append(row.map(x => padLeft(x.getOrElse("*").toString, len(max), ' ' )  ).mkString("(", ", ", ")") + newline)
    }
    output.toString()
  }
}

object Board {
  def apply(): Board = Board(Seq())

  def apply(values: Seq[Option[Int]], size: Int): Board = {
    var row: Seq[Option[Int]] = Seq()
    var remaining: Seq[Option[Int]] = values
    var rows: Seq[Seq[Option[Int]]] = Seq()
    values.zipWithIndex.foreach {
      case (tile, i) =>
        row = row ++ Seq(tile)
        if ((i + 1) % size == 0) {
          rows = rows.appended(row)
          remaining = remaining.filter(r => !row.contains(r))
          row = Seq()
        }
    }
    Board(rows.appended(remaining).filter(_ != Nil))
  }

  def apply(size: Int): Board = {
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    Board(solution, size)
  }

}

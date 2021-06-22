package models.behaviors

import models.classes.npuzzle.Board

trait Position {
  def checkPosition(board: Board, value: Option[Int]): Either[String, (Int, Int)] = {
    var x: Option[Int] = None 
    var y: Option[Int] = None
    board.grid.zipWithIndex.foreach {
      case (rowData, rowNumber) => {
        if (rowData.contains(value)) {
          x = Some(rowNumber)
          y = Some(rowData.indexOf(value))
        }
      }
    }
    (x, y) match {
      case (None, _) => Left(s"Value [$value] does not exist in the board $board")
      case (_, None) => Left(s"Value [$value] does not exist in the board $board") 
      case (_, _) => Right((x.get, y.get))
    }
  }
  def position(board: Board, value: Option[Int]): (Int, Int) = checkPosition(board, value).getOrElse((0,0)) 
}

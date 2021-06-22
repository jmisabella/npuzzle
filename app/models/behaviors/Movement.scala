package models.behaviors

import models.classes.npuzzle.{ Board, Move, Direction, Down, Left, Right, Up }

trait Movement {
  type _Position <: Position
  val position: _Position

  def move(board: Board, direction: Direction): Either[String, Move] = {
    position.checkPosition(board, None) match {
      case scala.util.Left(e) => scala.util.Left(e)
      case scala.util.Right((curr_x, curr_y)) => {
        val (curr_x, curr_y) = (position.position(board, None))
        val (next_x, next_y) = direction match {
          case Up => (curr_x, curr_y - 1)
          case Down => (curr_x, curr_y + 1)
          case Left => (curr_x - 1, curr_y)
          case Right => (curr_x + 1, curr_y)
        }
        (next_x, next_y) match {
          case (x, _) if (x < 0 || x >= board.grid.length) => scala.util.Left(s"Illegal move [$direction] caused X coordinate [$x] that was out-of-range. Board: $board")
          case (_, y) if (y < 0 || y >= board.grid.length) => scala.util.Left(s"Illegal move [$direction] caused Y coordinate [$y] that was out-of-range. Board: $board")
          case (x, y) => {
            val replacedTile: Option[Int] = board.grid(x)(y)
            scala.util.Right(Move(Some(direction), Board(board.grid.map {
              case row if (row.contains(replacedTile) || row.contains(None)) => {
                for (x <- row) yield x match {
                  case x1 if (x1 == replacedTile) => None
                  case None => replacedTile
                  case _ => x
                }
              }
              case row => row
            })))
          }
        }
      }
    } 
  }

  def getAllMoves(board: Board): Seq[Either[String, Move]] = {
    (for (d <- Seq(Up, Down, Left, Right)) yield move(board, d))
  }

  def getMoves(board: Board): Seq[Move] = {
    (for (d <- Seq(Up, Down, Left, Right)) yield move(board, d))
      .filter(_.isRight)
      .map(x => x.getOrElse(Move()))
  }

  def availableMoves(board: Board): Seq[Direction] = {
    // position.position(board, None) match {
    //   case (x, y) if (x == 0 && y == 0) => Seq(Down, Right)
    //   case (x, y) if (x == 0 && y == board.grid.length - 1) => Seq(Down, Left)
    //   case (x, y) if (x == board.grid.length - 1 && y == 0) => Seq(Up, Right)
    //   case (x, y) if (x == board.grid.length - 1 && y == board.grid.length - 1) => Seq(Up, Left)
    //   case (x, _) if (x == 0) => Seq(Down, Left, Right)
    //   case (x, _) if (x == board.grid.length - 1) => Seq(Up, Left, Right)
    //   case (_, y) if (y == 0) => Seq(Up, Down, Right)
    //   case (_, y) if (y == board.grid.length - 1) => Seq(Up, Down, Left)
    //   case (_, _) => Seq(Up, Down, Left, Right)
    // }
    position.position(board, None) match {
      // x indicates the row number, y indicates the column number
      case (x, y) if (x == 0 && y == 0) => Seq(Down, Right)
      case (x, y) if (x == 0 && y == board.grid.length - 1) => Seq(Down, Left)
      case (x, y) if (x == board.grid.length - 1 && y == 0) => Seq(Up, Right)
      case (x, y) if (x == board.grid.length - 1 && y == board.grid.length - 1) => Seq(Up, Left)
      case (x, _) if (x == 0) => Seq(Down, Left, Right)
      case (x, _) if (x == board.grid.length - 1) => Seq(Up, Left, Right)
      case (_, y) if (y == 0) => Seq(Up, Down, Right)
      case (_, y) if (y == board.grid.length - 1) => Seq(Up, Down, Left)
      case (_, _) => Seq(Up, Down, Left, Right)
    }
  }

}

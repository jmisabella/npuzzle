package models.behaviors

import models.classes.npuzzle.Board

trait Distance {
  type _Position <: Position
  val position: _Position

  private def absolute(value: Int): Int = if (value < 0) -value else value

  private def manhattanDistance(coords1: (Int, Int), coords2: (Int, Int)): Int = {
    absolute(coords1._1 - coords2._1) + absolute(coords1._2 - coords2._2)
  }

  def distance(board: Board, goal: Board): Int = {
    (for (i <- 1 until board.grid.length * board.grid.length) yield {
      manhattanDistance(position.position(board, Some(i)), position.position(goal, Some(i)))
    }).foldLeft(0)(_ + _)
  }
}

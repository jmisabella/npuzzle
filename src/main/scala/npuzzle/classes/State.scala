package npuzzle.classes

import npuzzle.utilities.RNG

import scala.util.Random

case class State(board: Board,
                 currentDistance: Int,  // current distance from goal
                 moveCount: Int,        // accumulated number of moves taken
                 remainingStepsToExitLocalOptima: Int, // to prevent infinite loop of repeated moves inside a local optima
                 moves: Seq[Move],      // history of moves
                 initialBoard: Option[Board], // initial state
                 seed: RNG) {

  require { // require that moveCount cannot be negative
    moveCount >= 0
  }

  val goalReached: Boolean = board.grid.flatten == board.solution

  def direction(): Option[Direction] = {
    val lastMove: Option[Move] = moves.reverse.headOption
    if (lastMove.isDefined) {
      lastMove.get.direction
    } else {
      None
    }
  }
}

object State {
  def apply(board: Board): State = {
    State(board, board.length * board.length, 0, 0, Nil, None, RNG.RandomSeed(Random.nextInt()))
  }

  def apply(size: Int): State = {
    State(Board(size))
  }
}
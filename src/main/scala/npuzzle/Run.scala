package npuzzle

import npuzzle.modules.TextNPuzzleService._
import npuzzle.classes.{ Board, State }

object Run extends App {

  def playGameOnItsOwn(): Unit = {
    val size = 4
    val state: State = newGame(size)
    printMoves(state, 250)
    println()
    Thread.sleep(1000)
    println("Goal reached!")
    println()
    Thread.sleep(1000)
    println(s"initial state")
    printBoard(state.initialBoard.getOrElse(Board()))
  }


  def playGameOnItsOwn_v1(): Unit = {
    val size = 4
    val maxMoves = size * size * size * size * size * size
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    var state: State = State(Board(solution, size))
    for (_ <- 1 until size * size * size) {
      state = random(state, false)
    }
    state = state.copy(initialBoard = Some(state.board))
    println("----------------")
    println("initial board")
    printBoard(state.board)
    for (_ <- 0 to maxMoves) {
      if (!state.goalReached) {
        state = nextMove(state)
        printBoard(state)
      }
    }
  }


//  playGameOnItsOwn_v1()

   playGameOnItsOwn()

}
package models.behaviors

import models.classes.npuzzle.{ Board, State }

trait TextOutput {

  def printBoard(board: Board): Unit = {
    println(board)
  }

  def printBoard(state: State): Unit = {
    println("--------------")
    println("Step " + state.moves.length + ": " + state.direction().getOrElse(""))
    println(state.board)
    println()
  }

  def printBoard(board: Either[String, models.classes.npuzzle.Move]): Unit = {
    board match {
      case Left(e) => println(e)
      case Right(b) => {
        println("----------------")
        println(b.direction);
        println();
        printBoard(b.board)
      };
    }
  }

  def printMoves(state: State, pauseMilliseconds: Long = 0L): Unit = {
    println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n")
    println("initial board")
    printBoard(state.initialBoard.getOrElse(Board()))
    println()
    var i = 0
    for (move <- state.moves) {
      Thread.sleep(pauseMilliseconds)
      i += 1
      println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n")
      println(s"Step $i: " + move)
    }
  }

}

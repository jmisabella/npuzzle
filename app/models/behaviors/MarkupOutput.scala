package models.behaviors

import models.classes.npuzzle.State

trait MarkupOutput {
  def movesMarkup(state: State): Seq[String] = {
    for (move <- state.moves) yield {
      move
        .board
        .toString()
        .replace("(", "<ui><li>")
        .replace(")", "</li></ui>")
        .replace(",", "</li><li>")
        .replace(" ", "")
    }
  }
}

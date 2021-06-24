package models.classes

case class Move(direction: Option[Direction], board: Board) {
  require {
    (board.length == 0 && direction.isEmpty) || direction.isDefined
  }
  override def toString(): String = direction.getOrElse("").toString() + "\r\n" + board
}

object Move {
  def apply(): Move = Move(None, Board())
}

package models.web

import models.modules.NPuzzleService
import models.classes.{ State, Board }
import akka.actor._
import play.api.libs.json._
import play.api.libs.json.Json

object NPuzzleWebSocketActor {
    // DOCS: Props is a ActorRef configuration object, that is immutable, so it is 
    // thread-safe and fully sharable. Used when creating new actors through 
    // ActorSystem.actorOf and ActorContext.actorOf.
    def props(clientActorRef: ActorRef) = Props(new NPuzzleWebSocketActor(clientActorRef))
}

class NPuzzleWebSocketActor(clientActorRef: ActorRef) extends Actor {
    val logger = play.api.Logger(getClass)
    logger.info(s"SimpleWebSocketActor class started")

    // incoming messages should really only ever contain numbers, for requesting new games
    private def isInt(value: String): Boolean = {
      try { 
        value.toInt 
        return true 
      } catch { 
        case _: NumberFormatException => false 
        case _: Exception => false
      }
    }

    // this is where we receive json messages sent by the client
    // and send them a json reply
    def receive = {
        case jsValue: JsValue =>
            logger.info(s"JS-VALUE: $jsValue")
            val clientMessage = getMessage(jsValue)
            val state: Either[String, State] = clientMessage match {
              // restrict games to only 3x3 and 4x4, reject everything else
              case n if (n.length() == 1 && isInt(n) && Seq(2, 3, 4).contains(n.toInt)) => {
                Right(NPuzzleService.newGame(n.toInt)) 
              }
              case n if (n.length() == 1 && isInt(n)) => Left(s"Invalid board size [$n]")
              case _ => Left("Unexpected error")
            }
            val response = state match {
              case Left(e) => e
              case Right(s) => {
                (Seq(s.initialBoard.getOrElse(Board())) ++ // initial board + the sequence of moves to the goal 
                s.moves
                  .map(_.board))
                  .mkString("|")
                  .replace(" ", "")
                  .replace("\r\n", "")
                  .replace("\n", "")
              }
            }
            val json: JsValue = Json.parse(s"""{"body": "$response"}""")
            clientActorRef ! (json)
    }

    // parse the "message" field from the json the client sends us
    def getMessage(json: JsValue): String = (json \ "message").as[String]

}

package controllers

import play.api.mvc._
import play.api.libs.streams.ActorFlow
import javax.inject.Inject
import akka.actor.ActorSystem
import akka.stream.Materializer
import models.web.NPuzzleWebSocketActor
import play.api.libs.json._

// the ‘implicit’ here is needed for the `ActorFlow.actorRef` below.
// i thought a Materializer was needed, but it is not.
// class WebSocketsController @Inject() (cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer)
class NPuzzleWebSocketsController @Inject() (cc: ControllerComponents)(implicit system: ActorSystem)
extends AbstractController(cc)
{
    val logger = play.api.Logger(getClass)

    // this method displays the index.scala.html page/template
    def index = Action { implicit request: Request[AnyContent] =>
        logger.info("index page was called")
        // val key = "JAVA_HOME"
        val key = "PORT"
        val myport: String = 
          try { 
            if (sys.env(key) != "") 
              sys.env(key) 
            else 
              "9000" 
          } catch { 
            case _: Exception => "9000" 
          } 
        Ok(views.html.index(myport))
    }

    // our WebSocket. DOCS on WebSocket.accept:
    // def accept[In, Out](f: RequestHeader => Flow[In, Out, _])(implicit transformer: WebSocket.MessageFlowTransformer[In,Out]): WebSocket
    // Accepts a WebSocket using the given flow.
    def ws = WebSocket.accept[JsValue, JsValue] { requestHeader =>
        logger.info("'ws' function is called")
        ActorFlow.actorRef { actorRef =>
            logger.info(s"ws: calling My Actor")
            NPuzzleWebSocketActor.props(actorRef)
        }
    }

}




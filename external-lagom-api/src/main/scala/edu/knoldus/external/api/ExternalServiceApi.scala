package edu.knoldus.external.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

import scala.collection.mutable.ListBuffer

trait ExternalServiceApi extends Service {

  def insertEmpDetails(): ServiceCall[EmpDetails,ListBuffer[EmpDetails]]

  override final def descriptor = {
    import Service._
    // @formatter:off

    named("hello-lagom")
      .withCalls(
        restCall(Method.PUT,"/api/hello", insertEmpDetails _)
      )

  }
}

case class EmpDetails(id: Int, empName: String, organization: String)

object EmpDetails {

  implicit val format: Format[EmpDetails] = Json.format[EmpDetails]
}

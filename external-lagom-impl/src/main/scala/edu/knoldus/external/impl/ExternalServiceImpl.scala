package edu.knoldus.external.impl

import akka.NotUsed
import com.example.hello.api.{HellolagomService, UserData}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import edu.knoldus.external.api.ExternalServiceApi
import com.example.hello.api.EmpDetails

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

class ExternalServiceImpl(externalService: HellolagomService)(implicit ec: ExecutionContext) extends ExternalServiceApi {

  override def insertEmpDetails(): ServiceCall[EmpDetails,ListBuffer[EmpDetails]] = ServiceCall { request =>
    val obj = EmpDetails(request.id,request.empName,request.organization)
    val result: Future[ListBuffer[EmpDetails]] = externalService.insertEmpDetails().invoke(obj)
    result.map(response => response)
  }


}

package domain.containerTerminalvisit.entity.input

import com.avlino.entity.InputEntity

case class ContainerTerminalVisitActiveInput(
                                              containerTerminalVisitKey:String,
                                              portOfDischargeKey:Option[String],
                                              visitState:String,
                                              transitState:String,
                                              containerId:Option[String],
                                              containerCategory:Option[String],
                                              containerFreightType:Option[String],
                                              containerLengthFt:Option[Int],
                                              isoSizeType:String,
                                              isoGroup:String,
                                              equipmentTosKey:Option[String],
                                              arrivalCarrierTerminalVisitType:Option[String],
                                              arrivalCarrierTerminalVisitId:Option[String],
                                              timeIn:Option[Long],
                                              goodsAndContainerWeightKg:Option[Double],
                                            ) extends InputEntity

package domain.joborder.entity.input

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
                                              arrivalCarrierTerminalVisitType:String,
                                              arrivalCarrierTerminalVisitId:String,
                                              timeIn:Option[Long],
                                              goodsAndContainerWeightKg:Option[Double],
                                            )

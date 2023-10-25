package domain.containerTerminalvisit.entity.output

case class ContainerTerminalVisitOutputValue(
                                          containerVisitId: String,
                                          containerId:String,
                                          containerClass: String,
                                          containerWeight: Double,
                                          containerIsoSizeType: String,
                                          containerIsoGroup: String,
                                          containerType: Option[String],
                                          specialType: Option[String],
                                          containerCommodity: Option[String],
                                          actualContainerSize: Option[String],
                                          containerSize: Option[String],
                                          portOfDischargeKey: Option[String],
                                        )
case class ContainerTerminalVisitOutputKey(containerVisitId: String)
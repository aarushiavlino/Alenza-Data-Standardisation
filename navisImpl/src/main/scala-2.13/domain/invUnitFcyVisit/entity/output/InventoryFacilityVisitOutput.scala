package domain.invUnitFcyVisit.entity.output

case class InventoryFacilityVisitOutputValue(
                                         visitId: Long,
                                        visitState: String,
                                        locationType: String,
                                        locationId: Option[String],
                                        locationGkey: Option[Long],
                                        inboundCV: Option[Long],
                                        outboundCV: Option[Long]
                                       )


case class InventoryFacilityVisitOutputKey(visitId: Long)

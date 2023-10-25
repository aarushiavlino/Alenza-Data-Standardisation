package domain.vesselVisitDetails.entity.output

case class VesselVisitDetailsOutputValue(tosKey: String,
                                         vesselName: String,
                                         vesselArrivalDate: Option[String],
                                         vesselArrivalTime: Option[String],
                                         vesselDepartureDate: Option[String],
                                         vesselDepartureTime: Option[String],
                                         vesselBerthNumbers: List[String],
)

case class VesselVisitDetailsOutputKey(tosKey: String)

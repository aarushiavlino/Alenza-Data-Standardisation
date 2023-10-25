package domain.vesselVisit.entity.output

case class VesselVisitOutputValue(
                              tosKey:String,
                              carrierMode: String,
                              id: String,
                              tosOperatorKey: String,
                              tosVisitDetailKey: String
                            )

case class VesselVisitOutputKey(tosKey:String,id:String)
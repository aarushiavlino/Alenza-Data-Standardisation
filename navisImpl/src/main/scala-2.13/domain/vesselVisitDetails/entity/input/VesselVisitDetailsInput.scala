package domain.vesselVisitDetails.entity.input

case class VesselVisitDetailsInput (tosKey:String,
                                    vesselKey:Option[String],
                                    publishedEta:Option[Long],
                                    publishedEtd:Option[Long],
                                    berthingPlans:List[BerthingPlansInput]
                                   )

case class BerthingPlansInput(berthingKey:String,
                              seq:Long,
                              eta:Option[Long],
                              etd:Option[Long],
                              berthName:Option[String]
                             )